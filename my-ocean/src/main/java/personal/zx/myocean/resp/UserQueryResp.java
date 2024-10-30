package personal.zx.myocean.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

/**
 * 用户查询返回参数
 * @author 小z
 * @date 2024年10月26日 下午1:50
 */

@Data
@ToString
public class UserQueryResp {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    //登录名
    private String loginName;
    //昵称
    private String name;

    //密码
    private String password;
}
