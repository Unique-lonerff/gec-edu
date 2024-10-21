package personal.zx.mybatispuls.controller;

import org.springframework.web.bind.annotation.*;
import personal.zx.mybatispuls.entity.dos.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 小z
 * @date 2024年10月17日 下午8:48
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("addUserByForm")
    public Map<String,Object> addUserByForm(@RequestParam("name")String name,
                                            @RequestParam("age") Integer age){

        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("age",age);
        map.put("code",200);
        map.put("msg","添加成功");

        return map;
    }

    @PostMapping("addUserByJson")
    public Map<String,Object> addUser(@RequestBody User user){

        Map<String,Object> map = new HashMap<>();
        map.put("name",user.getName());
        map.put("age",user.getAge());
        map.put("code",200);
        map.put("msg","添加成功");

        return map;
    }
}