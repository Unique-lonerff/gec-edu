package personal.zx.myocean.service;

import personal.zx.myocean.entity.EbookSnapshot;
import com.baomidou.mybatisplus.extension.service.IService;
import personal.zx.myocean.resp.StatisticResp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Unique_zx
 * @since 2024-10-21
 */
public interface IEbookSnapshotService extends IService<EbookSnapshot> {

    public void genSnapshot();

    List<StatisticResp> getStatistic();

    List<StatisticResp> get30Statistic();
}
