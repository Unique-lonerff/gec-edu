package personal.zx.myocean.req;

import lombok.Data;
import lombok.ToString;

/**
 * 分类查询参数
 *
 * @author 小z
 * @date 2024年10月24日 上午9:11
 */


@Data
@ToString
public class CategoryQueryReq extends PageReq  {
    //名称
    private String name;
}
