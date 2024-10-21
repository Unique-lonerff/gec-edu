import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import personal.zx.myocean.MyOceanApplication;
import personal.zx.myocean.entity.User;
import personal.zx.myocean.mapper.UserMapper;

import java.util.Collections;
import java.util.List;

/**
 * @author 小z
 * @date 2024年10月21日 上午9:55
 */

@SpringBootTest(classes = MyOceanApplication.class)
public class MyOceanApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        String url = "jdbc:mysql://localhost:3306/my_ocean?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";

        FastAutoGenerator.create(url, "root", "Zx123456")
                .globalConfig(builder -> {
                    builder.author("Unique_zx") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("./generation/Code/"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("personal.zx") // 设置父包名
                            .moduleName("myocean") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "./generation/Code/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("category,content,doc,ebook,ebook_snapshot,user"); // 设置需要生成的表名
                    //.addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }



    @Test
    public void queryUser(){
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        System.out.println(users);
    }
}

