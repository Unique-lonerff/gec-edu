package personal.zx.myocean.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.ObjectUtils;
import personal.zx.myocean.entity.Content;
import personal.zx.myocean.entity.Doc;
import personal.zx.myocean.exception.BusinessException;
import personal.zx.myocean.exception.BusinessExceptionCode;
import personal.zx.myocean.mapper.DocMapper;
import personal.zx.myocean.mapper.EbookMapper;
import personal.zx.myocean.req.DocQueryReq;
import personal.zx.myocean.req.DocSaveReq;
import personal.zx.myocean.resp.DocQueryResp;
import personal.zx.myocean.resp.PageResp;
import personal.zx.myocean.service.IContentService;
import personal.zx.myocean.service.IDocService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import personal.zx.myocean.utils.CopyUtil;
import personal.zx.myocean.utils.RedisUtil;
import personal.zx.myocean.utils.RequestContext;
import personal.zx.myocean.utils.SnowFlake;
import personal.zx.myocean.websocket.WebSocketServer;
import personal.zx.myocean.websocket.WsServiceAsync;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Unique_zx
 * @since 2024-10-21
 */
@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements IDocService {


    @Autowired
    private SnowFlake snowFlake;
    @Autowired
    IContentService contentService;
    @Autowired
    private EbookMapper ebookMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public PageResp<DocQueryResp> listByname(DocQueryReq req) {

        QueryWrapper<Doc> queryWrapper = new QueryWrapper<Doc>();
        //第一个参数：该参数是一个布尔类型，只有该参数是true时，才将like条件拼接到sql中；本例中，如果name字段不为空，则拼接name字段的like查询条件；
        queryWrapper.like(StringUtils.isNotBlank(req.getName()),"name",req.getName());
        //创建分页对象
        Page<Doc> page = new Page<>(req.getPage(),req.getSize());
        page = this.baseMapper.selectPage(page, queryWrapper);

        List<DocQueryResp> resps = CopyUtil.copyList(page.getRecords(), DocQueryResp.class);
        //创建返回对象
        PageResp<DocQueryResp> pageResp = new PageResp<>();
        pageResp.setList(resps);
        pageResp.setTotal(page.getTotal());
        return pageResp;
    }

    @Override
    public void save(DocSaveReq req) {
        Doc doc = CopyUtil.copy(req, Doc.class);
        Content content = CopyUtil.copy(req, Content.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增文档
            long id = snowFlake.nextId();
            doc.setId(id);
            doc.setViewCount(0);//默认查看点赞为0
            doc.setVoteCount(0);
            this.baseMapper.insert(doc);

            //新增内容
            content.setId(id);
            contentService.save(content);
        } else {
            // 更新文档
            this.baseMapper.updateById(doc);
            boolean b = contentService.updateById(content);//更新内容
            if(!b){//根据id更新失败,执行新增功能
                contentService.save(content);
            }
        }
    }

    @Override
    public void delete(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public List<DocQueryResp> all() {
        List<Doc> categories = this.baseMapper.selectList(new QueryWrapper<Doc>().orderByAsc("sort"));
        List<DocQueryResp> list = CopyUtil.copyList(categories, DocQueryResp.class);
        return list;
    }

    @Override
    public void delete(List<Long> ids) {
        this.baseMapper.deleteBatchIds(ids);
    }

    @Override
    public List<DocQueryResp> allbyEbookId(Long ebookId) {
        //该电子书阅读数+1
        ebookMapper.increaseViewCount(ebookId);
        QueryWrapper<Doc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ebook_id",ebookId).orderByAsc("sort");
        List<Doc> doclist = this.baseMapper.selectList(queryWrapper);

        // 列表复制
        List<DocQueryResp> list = CopyUtil.copyList(doclist, DocQueryResp.class);
        return list;
    }


    @Override
    public void vote(Long id) {

        //key为  DOC_VOTE_123123123_192.168.0.1
        String key ="DOC_VOTE_"+id+"_"+RequestContext.getRemoteAddr();
        if(redisUtil.validateRepeat(key,3600*24)){
            this.baseMapper.increaseVoteCount(id);
        }else{
            throw new BusinessException(BusinessExceptionCode.VOTE_REPEAT);
        }
        // sendInfo(id);//发送点赞通知
        Doc doc = this.baseMapper.selectById(id);
        String logId = MDC.get("LOG_ID");
        //wsServiceAsync.sendInfo("【您的文档 " + doc.getName() + "】被点赞！",logId);
        //参数1  发送队列 参数2消息内容
        rocketMQTemplate.convertAndSend("VOTE_TOPIC", "【" + doc.getName() + "】被点赞！");
    }


    @Override
    public void updateEbookInfo() {
        this.baseMapper.updateEbookInfo();
    }
}
