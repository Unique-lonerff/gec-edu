package personal.zx.myocean.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

/**
 * @author 小z
 * @date 2024年10月21日 下午3:54
 */


@Data
@ToString
public class EbookQueryResp {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;

    private Long category1Id;

    private Long category2Id;

    private String description;

    private String cover;

    private Integer docCount;

    private Integer viewCount;

    private Integer voteCount;
}
