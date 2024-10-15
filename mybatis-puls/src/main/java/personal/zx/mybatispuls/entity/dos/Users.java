package personal.zx.mybatispuls.entity.dos;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author 小z
 * @date 2024年10月14日 下午3:34
 */

@Data
public class Users {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)  //在插入操作时填充（Insert）
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)  //在插入或修改时填充
    private Date updateTime;

    @Version
    @TableField(fill = FieldFill.INSERT)//添加这个注解是为了在后面设置初始值，不加也可以
    private Integer version;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
