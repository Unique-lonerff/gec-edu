//package personal.zx.myocean.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import personal.zx.myocean.interceptor.Durationinterceptor;
//
///**
// * @author 小z
// * @date 2024年10月21日 下午4:49
// */
//
//@Configuration
//public class SpringMvcConfig implements WebMvcConfigurer {
//
//    @Autowired
//    Durationinterceptor durationinterceptor;
//
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(durationinterceptor)
//                .addPathPatterns("/**");
//    }
//}
