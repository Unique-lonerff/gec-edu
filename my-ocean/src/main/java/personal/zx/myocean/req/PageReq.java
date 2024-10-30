package personal.zx.myocean.req;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author 小z
 * @date 2024年10月22日 下午2:48
 */

@Data
@ToString
public class PageReq {
    @NotNull(message = "[页码]不能为空")
    private Integer page;
    @NotNull(message = "[页大小]不能为空")
    @Max(value = 1000, message = "【每页条数】不能超过1000")
    private Integer size;
}
