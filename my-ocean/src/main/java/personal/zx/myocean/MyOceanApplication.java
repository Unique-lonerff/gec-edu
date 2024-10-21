package personal.zx.myocean;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@MapperScan("personal.zx.myocean.mapper")
@ServletComponentScan
public class MyOceanApplication {

    //使用SLF4j框架创建日志记录器  获取一个与ObwikiApplication类相关联的日志记录器实例
    private static final Logger LOG = LoggerFactory.getLogger(MyOceanApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MyOceanApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        LOG.info("项目启动成功!");
        LOG.info("地址:\thttp://127.0.0.1:{}",env.getProperty("server.port"));
    }

}
