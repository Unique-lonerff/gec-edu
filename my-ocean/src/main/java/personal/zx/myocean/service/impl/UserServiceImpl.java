package personal.zx.myocean.service.impl;

import personal.zx.myocean.entity.User;
import personal.zx.myocean.mapper.UserMapper;
import personal.zx.myocean.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Unique_zx
 * @since 2024-10-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
