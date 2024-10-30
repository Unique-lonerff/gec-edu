package personal.zx.myocean.service;

import personal.zx.myocean.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import personal.zx.myocean.req.UserLoginReq;
import personal.zx.myocean.req.UserQueryReq;
import personal.zx.myocean.req.UserResetPasswordReq;
import personal.zx.myocean.req.UserSaveReq;
import personal.zx.myocean.resp.PageResp;
import personal.zx.myocean.resp.UserLoginResp;
import personal.zx.myocean.resp.UserQueryResp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Unique_zx
 * @since 2024-10-21
 */
public interface IUserService extends IService<User> {

    PageResp<UserQueryResp> listByname(UserQueryReq req);

    void save(UserSaveReq req);

    void delete(Long id);

    void resetPassword(UserResetPasswordReq req);

    UserLoginResp login(UserLoginReq req);
}
