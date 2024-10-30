package personal.zx.myocean.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import personal.zx.myocean.entity.User;
import personal.zx.myocean.exception.BusinessException;
import personal.zx.myocean.exception.BusinessExceptionCode;
import personal.zx.myocean.mapper.UserMapper;
import personal.zx.myocean.req.UserLoginReq;
import personal.zx.myocean.req.UserQueryReq;
import personal.zx.myocean.req.UserResetPasswordReq;
import personal.zx.myocean.req.UserSaveReq;
import personal.zx.myocean.resp.PageResp;
import personal.zx.myocean.resp.UserLoginResp;
import personal.zx.myocean.resp.UserQueryResp;
import personal.zx.myocean.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import personal.zx.myocean.utils.CopyUtil;
import personal.zx.myocean.utils.SnowFlake;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Unique_zx
 * @since 2024-10-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    SnowFlake snowFlake;

    @Override
    public PageResp<UserQueryResp> listByname(UserQueryReq req) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(req.getName()), "name", req.getName());

        queryWrapper.or().like(StringUtils.isNotBlank(req.getLoginName()), "login_name", req.getLoginName());
        //创建分页对象
        Page<User> page = new Page<>(req.getPage(), req.getSize());
        page = this.baseMapper.selectPage(page, queryWrapper);


        List<UserQueryResp> resps = CopyUtil.copyList(page.getRecords(), UserQueryResp.class);
        //创建返回对象
        PageResp<UserQueryResp> pageResp = new PageResp<>();
        pageResp.setList(resps);
        pageResp.setTotal(page.getTotal());
        return pageResp;
    }

    @Override
    public void save(UserSaveReq req) {
        User user = CopyUtil.copy(req, User.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            //判断用户名是否已经存在
            User one = this.baseMapper.selectOne(new QueryWrapper<User>().eq("login_name", req.getLoginName()));
            if (ObjectUtils.isEmpty(one)) {
                // 新增
                long id = snowFlake.nextId();
                user.setId(id);
                this.baseMapper.insert(user);
            } else {
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
        } else {
            // 更新
            user.setLoginName(null);//避免绕过前端 修改登录名
            this.baseMapper.updateById(user);
        }
    }

    @Override
    public void delete(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void resetPassword(UserResetPasswordReq req) {
        User user = CopyUtil.copy(req, User.class);
        //updateById 方法在更新时会根据传入的对象中的非空字段进行更新。
        this.baseMapper.updateById(user);
    }


    @Override
    public UserLoginResp login(UserLoginReq req) {
        //1.根据用户名查询用户信息
        User user = this.baseMapper.selectOne(new QueryWrapper<User>().eq("login_name", req.getLoginName()));
        if (ObjectUtils.isEmpty(user)) {
            // 用户名不存在
            LOG.info("用户名不存在, {}", req.getLoginName());
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        } else {
            if (user.getPassword().equals(req.getPassword())) {
                // 登录成功
                UserLoginResp userLoginResp = CopyUtil.copy(user, UserLoginResp.class);
                return userLoginResp;
            } else {
                // 密码不对
                LOG.info("密码不对, 输入密码：{}, 数据库密码：{}", req.getPassword(), user.getPassword());
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }
    }
}
