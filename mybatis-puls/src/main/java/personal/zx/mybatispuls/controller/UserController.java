package personal.zx.mybatispuls.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.zx.mybatispuls.entity.dos.Users;
import personal.zx.mybatispuls.entity.service.UserService;

/**
 * @author 小z
 * @date 2024年10月15日 下午3:49
 */

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUser(@RequestParam("id") Long id, Model model) {
        Users user = userService.getById(id); // 获取用户数据
        if (user != null) {
            System.out.println(user);
            model.addAttribute("user", user); // 将用户数据传递到视图中
        }
        return "user"; // 返回视图名称
    }
}
