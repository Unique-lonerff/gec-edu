package personal.zx.myocean.service;

import personal.zx.myocean.entity.Doc;
import com.baomidou.mybatisplus.extension.service.IService;
import personal.zx.myocean.req.DocQueryReq;
import personal.zx.myocean.req.DocSaveReq;
import personal.zx.myocean.resp.DocQueryResp;
import personal.zx.myocean.resp.PageResp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Unique_zx
 * @since 2024-10-21
 */
public interface IDocService extends IService<Doc> {

    List<DocQueryResp> all();

    PageResp<DocQueryResp> listByname(DocQueryReq req);

    void save(DocSaveReq req);

    void delete(Long id);

    void delete(List<Long> ids);

    List<DocQueryResp> allbyEbookId(Long ebookId);

    void vote(Long id);

    void updateEbookInfo();
}
