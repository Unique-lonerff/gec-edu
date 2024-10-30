package personal.zx.myocean.mapper;

import org.apache.ibatis.annotations.Mapper;
import personal.zx.myocean.entity.EbookSnapshot;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import personal.zx.myocean.resp.StatisticResp;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Unique_zx
 * @since 2024-10-21
 */
@Mapper
public interface EbookSnapshotMapper extends BaseMapper<EbookSnapshot> {

    void genSnapshot();

    List<StatisticResp> getStatistic();

    List<StatisticResp> get30Statistic();
}
