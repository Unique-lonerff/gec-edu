package personal.zx.myocean.req;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author 小z
 * @date 2024年10月22日 下午3:03
 */

@Data
@ToString
public class EbookSaveReq {
    private Long id;

    @NotNull(message = "【名称】不能为空")
    private String name;

    private Long category1Id;

    private Long category2Id;

    private String description;

    private String cover;

    private Integer docCount;

    private Integer viewCount;

    private Integer voteCount;
}
