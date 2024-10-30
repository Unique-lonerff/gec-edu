package personal.zx.myocean.req;

import lombok.Data;
import lombok.ToString;

/**
 * 分类修改新增参数类
 *
 * @author 小z
 * @date 2024年10月24日 上午9:12
 */

@Data
@ToString
public class CategorySaveReq {
    private Long id;

    //父id
    private Long parent;

    //名称
    private String name;

    //顺序
    private Integer sort;
}
