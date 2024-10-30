package personal.zx.myocean.resp;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

/**
 * 分类查询响应类
 *
 * @author 小z
 * @date 2024年10月24日 上午9:16
 */


@Data
@ToString
public class CategoryQueryResp {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    //父id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parent;
    //名称
    private String name;
    //顺序
    private Integer sort;
}