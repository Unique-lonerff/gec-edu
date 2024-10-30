package personal.zx.myocean.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author 小z
 * @date 2024年10月30日 上午10:11
 */

@Data
@ToString
public class StatisticResp {
    @JsonFormat(pattern = "MM-dd", timezone = "GMT+8")
    private Date date;

    private int viewCount;

    private int voteCount;

    private int viewIncrease;

    private int voteIncrease;
}
