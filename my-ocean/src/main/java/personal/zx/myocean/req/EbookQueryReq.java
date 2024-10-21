package personal.zx.myocean.req;

import lombok.Data;
import lombok.ToString;

/**
 * @author 小z
 * @date 2024年10月21日 下午3:52
 */

@Data
@ToString
public class EbookQueryReq {
    //主键
    private Long id;
    //海洋生物电子书名
    private String name;
    //分类2
    private Long categoryId2;
}
