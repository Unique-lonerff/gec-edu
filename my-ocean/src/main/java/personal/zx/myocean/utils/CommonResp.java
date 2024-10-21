package personal.zx.myocean.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 小z
 * @date 2024年10月21日 上午10:24
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommonResp<T> {
    //业务上的成功或失败
    private boolean success = true;
    //返回信息
    private String message;

    //返回泛型数据，自定义类型
    private T content;
}
