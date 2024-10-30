package personal.zx.myocean.service.impl;

import personal.zx.myocean.entity.EbookSnapshot;
import personal.zx.myocean.mapper.EbookSnapshotMapper;
import personal.zx.myocean.resp.StatisticResp;
import personal.zx.myocean.service.IEbookSnapshotService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class EbookSnapshotServiceImpl extends ServiceImpl<EbookSnapshotMapper, EbookSnapshot> implements IEbookSnapshotService {

    @Override
    public void genSnapshot() {
        this.baseMapper.genSnapshot();
    }

    @Override
    public List<StatisticResp> getStatistic() {
        return  this.baseMapper.getStatistic();
    }
    @Override
    public List<StatisticResp> get30Statistic() {
        return this.baseMapper.get30Statistic();
    }


}
