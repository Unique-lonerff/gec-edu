package personal.zx.myocean.req;

import lombok.Data;
import lombok.ToString;

/**
 * @author 小z
 * @date 2024年10月26日 下午1:50
 */

@Data
@ToString
public class UserQueryReq extends PageReq{
    //主键
    private Long id;
    //登录名
    private String loginName;
    //昵称
    private String  name;
}
