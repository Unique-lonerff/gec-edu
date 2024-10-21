package personal.zx.mybatispuls.entity.dos;

import lombok.Data;

/**
 * @author 小z
 * @date 2024年10月17日 下午8:47
 */

@Data
public class User {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}