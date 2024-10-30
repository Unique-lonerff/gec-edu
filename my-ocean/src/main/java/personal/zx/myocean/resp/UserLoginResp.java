package personal.zx.myocean.resp;

import lombok.Data;
import lombok.ToString;

/**
 * @author 小z
 * @date 2024年10月26日 下午3:14
 */

@Data
@ToString
public class UserLoginResp {
    private Long id;

    private String loginName;

    private String name;

    private String token;
}
