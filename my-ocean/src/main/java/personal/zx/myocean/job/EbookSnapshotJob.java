package personal.zx.myocean.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import personal.zx.myocean.service.IEbookSnapshotService;
import personal.zx.myocean.utils.SnowFlake;

import javax.annotation.Resource;

/**
 * 电子书快照-定时任务类
 *
 * @author 小z
 * @date 2024年10月30日 上午9:42
 */

@Component
public class EbookSnapshotJob {
    private static final Logger LOG = LoggerFactory.getLogger(EbookSnapshotJob.class);

    @Resource
    private IEbookSnapshotService ebookSnapshotService;

    @Resource
    private SnowFlake snowFlake;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void doSnapshot() {
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        LOG.info("EbookSnapshotJob start");
        long start = System.currentTimeMillis();
        ebookSnapshotService.genSnapshot();
        LOG.info("EbookSnapshotJob end, cost: {} ms", System.currentTimeMillis() - start);
    }
}
