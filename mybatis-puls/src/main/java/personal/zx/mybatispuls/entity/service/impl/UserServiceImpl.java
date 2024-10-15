package personal.zx.mybatispuls.entity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import personal.zx.mybatispuls.entity.dos.Users;
import personal.zx.mybatispuls.entity.mapper.UsersMapper;
import personal.zx.mybatispuls.entity.service.UserService;

/**
 * @author 小z
 * @date 2024年10月15日 下午3:47
 */

@Service
public class UserServiceImpl extends ServiceImpl<UsersMapper, Users> implements UserService {

}


