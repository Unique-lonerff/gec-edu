package personal.zx.myocean.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.zx.myocean.req.DemoReq;
import personal.zx.myocean.utils.CommonResp;

import javax.validation.Valid;

/**
 * @author 小z
 * @date 2024年10月21日 上午10:29
 */

@RestController
@RequestMapping("/test")
@Api("测试接口")
public class TestController {


    @PostMapping("/sayHello")
    @ApiOperation("最简单的测试方法") //Swagger声明方法添加@ApiOperation注解,并说明
    public CommonResp sayHello(@Valid @RequestBody DemoReq demoReq) {
//        Map<String, String> map = new HashMap<>();
//        if (result.hasErrors()) {
//            result.getFieldErrors().forEach((item)->{
//                String message = item.getDefaultMessage();
//                String field = item.getField();
//                map.put(field, message);
//            });
//            return new CommonResp(false, "参数异常", map);
//        }
        return new CommonResp(true, "查询成功", demoReq);
    }

}
