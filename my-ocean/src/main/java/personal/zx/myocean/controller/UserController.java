package personal.zx.myocean.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import personal.zx.myocean.req.UserLoginReq;
import personal.zx.myocean.req.UserQueryReq;
import personal.zx.myocean.req.UserResetPasswordReq;
import personal.zx.myocean.req.UserSaveReq;
import personal.zx.myocean.resp.PageResp;
import personal.zx.myocean.resp.UserLoginResp;
import personal.zx.myocean.resp.UserQueryResp;
import personal.zx.myocean.service.IUserService;
import personal.zx.myocean.utils.CommonResp;
import personal.zx.myocean.utils.SnowFlake;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Unique_zx
 * @since 2024-10-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private SnowFlake snowFlake;
    @Autowired
    private RedisTemplate redisTemplate;

    //登录
    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody UserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp<UserLoginResp> resp = new CommonResp<>();
        UserLoginResp userLoginResp = userService.login(req);

        Long token = snowFlake.nextId();
        LOG.info("生成单点登录token：{}，并放入redis中", token);
        userLoginResp.setToken(token.toString());
        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginResp), 3600 * 24, TimeUnit.SECONDS);

        resp.setContent(userLoginResp);
        return resp;
    }


    @GetMapping("/list")
    public CommonResp list(@Valid UserQueryReq req) {
        CommonResp<PageResp<UserQueryResp>> resp = new CommonResp<>(true,"查询成功",null);

        PageResp<UserQueryResp> pageResp = userService.listByname(req);
        resp.setContent(pageResp);

        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody UserSaveReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>(true,"成功",null);
        userService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>(true,"删除成功",null);
        userService.delete(id);
        return resp;
    }

    @PostMapping("resetPassword")
    public CommonResp resetPassword(@Valid @RequestBody UserResetPasswordReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>(true,"成功",null);
        userService.resetPassword(req);
        return resp;
    }

    @GetMapping("/logout/{token}")
    public CommonResp logout(@PathVariable String token) {
        CommonResp resp = new CommonResp<>();
        redisTemplate.delete(token);
        LOG.info("从redis中删除token: {}", token);
        return resp;
    }

}
