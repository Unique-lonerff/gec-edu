package personal.zx.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小z
 * @date 2024年10月14日 下午2:27
 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public List<String> hello() {
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");

        return list;
    }

}
