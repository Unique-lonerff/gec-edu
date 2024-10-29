package personal.zx.myocean.service.impl;

import personal.zx.myocean.entity.Content;
import personal.zx.myocean.mapper.ContentMapper;
import personal.zx.myocean.service.IContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Unique_zx
 * @since 2024-10-21
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {

    @Override
    public String findContent(Long id) {
        Content content = this.baseMapper.selectById(id);
        if(content !=null){
            return content.getContent();
        }
        return null;
    }
}
