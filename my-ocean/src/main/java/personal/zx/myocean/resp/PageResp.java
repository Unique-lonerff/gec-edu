package personal.zx.myocean.resp;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author 小z
 * @date 2024年10月22日 下午2:52
 */

@Data
@ToString
public class PageResp<T> {
    //总条数
    private long total;
    //分页数据
    private List<T> list;
}
