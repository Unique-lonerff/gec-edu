package personal.zx.mybatispuls.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.zx.mybatispuls.entity.dos.Users;
import personal.zx.mybatispuls.entity.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 * @author 小z
 * @date 2024年10月15日 下午3:49
 */

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    //    @GetMapping("/user")
//    public String getUser(@RequestParam("id") Long id, Model model) {
//        Users user = userService.getById(id); // 获取用户数据
//        if (user != null) {
//            System.out.println(user);
//            model.addAttribute("user", user); // 将用户数据传递到视图中
//        }
//        return "user"; // 返回视图名称
//    }
//
//    @RequestMapping("/all")
//    public String all(Model model) {
//        model.addAttribute("users", userService.list());
//        return "all";
//    }
//    @GetMapping("/getAll")
//    public List<Users> getAll() {
//        List<Users> list = userService.list();
//        return list;
//    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Optional<Users> user = Optional.ofNullable(userService.getById(id));
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public boolean createUser(@RequestBody Users user) {
        return userService.save(user);
    }

    @PutMapping()
    public ResponseEntity<Users> updateUser( @RequestBody Users userDetails) {
        boolean updatedUser = userService.updateById(userDetails);
        if (updatedUser == true) {
            return ResponseEntity.ok(userDetails);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.removeById(id);
        return ResponseEntity.noContent().build();
    }


}
