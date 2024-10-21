package personal.zx.myocean.service;

import personal.zx.myocean.entity.Ebook;
import com.baomidou.mybatisplus.extension.service.IService;
import personal.zx.myocean.req.EbookQueryReq;
import personal.zx.myocean.resp.EbookQueryResp;

import java.util.List;

/**
 * <p>
 * 电子书 服务类
 * </p>
 *
 * @author Unique_zx
 * @since 2024-10-21
 */
public interface IEbookService extends IService<Ebook> {

    List<EbookQueryResp> listByname(EbookQueryReq req);
}
