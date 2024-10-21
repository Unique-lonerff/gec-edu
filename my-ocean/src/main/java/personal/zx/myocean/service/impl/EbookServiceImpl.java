package personal.zx.myocean.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import personal.zx.myocean.entity.Ebook;
import personal.zx.myocean.mapper.EbookMapper;
import personal.zx.myocean.req.EbookQueryReq;
import personal.zx.myocean.resp.EbookQueryResp;
import personal.zx.myocean.service.IEbookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import personal.zx.myocean.utils.CopyUtil;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 电子书 服务实现类
 * </p>
 *
 * @author Unique_zx
 * @since 2024-10-21
 */
@Service
public class EbookServiceImpl extends ServiceImpl<EbookMapper, Ebook> implements IEbookService {



    @Override
    public List<EbookQueryResp> listByname(EbookQueryReq req) {
        QueryWrapper<Ebook> queryWrapper = new QueryWrapper<Ebook>();
        //第一个参数：该参数是一个布尔类型，只有该参数是true时，才将like条件拼接到sql中；本例中，如果name字段不为空，则拼接name字段的like查询条件；
        queryWrapper.like(StringUtils.isNotBlank(req.getName()),"name",req.getName());
        queryWrapper.eq(ObjectUtils.isNotEmpty(req.getCategoryId2()),"category2_id",req.getCategoryId2());
        List<Ebook> ebooks = this.baseMapper.selectList(queryWrapper);

        List<EbookQueryResp> resps = CopyUtil.copyList(ebooks, EbookQueryResp.class);

        return resps;
    }
}
