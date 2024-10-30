package personal.zx.myocean.mapper;

import org.apache.ibatis.annotations.Mapper;
import personal.zx.myocean.entity.Doc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Unique_zx
 * @since 2024-10-21
 */
@Mapper
public interface DocMapper extends BaseMapper<Doc> {

    void increaseViewCount(Long id);

    void increaseVoteCount(Long id);

    void updateEbookInfo();
}
