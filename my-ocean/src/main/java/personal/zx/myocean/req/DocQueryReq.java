package personal.zx.myocean.req;

import lombok.Data;
import lombok.ToString;

/**
 * @author 小z 
 * @date 2024年10月25日 上午10:17 
 */

@Data
@ToString
public class DocQueryReq  extends PageReq{
    //名称
    private String name;
}
