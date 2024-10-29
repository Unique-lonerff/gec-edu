package personal.zx.myocean.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

/**
 * @author 小z
 * @date 2024年10月25日 上午10:17
 */

@Data
@ToString
public class DocQueryResp {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    //电子书id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long ebookId;
    //父id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parent;
    //名称
    private String name;
    //顺序
    private Integer sort;
    //阅读数
    private Integer viewCount;
    //点赞数
    private Integer voteCount;
}