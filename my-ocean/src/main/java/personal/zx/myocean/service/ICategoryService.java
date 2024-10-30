package personal.zx.myocean.service;

import personal.zx.myocean.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import personal.zx.myocean.req.CategoryQueryReq;
import personal.zx.myocean.req.CategorySaveReq;
import personal.zx.myocean.resp.CategoryQueryResp;
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
public interface ICategoryService extends IService<Category> {

    PageResp<CategoryQueryResp> listByname(CategoryQueryReq req);

    void save(CategorySaveReq req);

    void delete(Long id);

    List<CategoryQueryResp> all();
}
