package personal.zx.myocean.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import personal.zx.myocean.utils.CommonResp;

import java.util.HashMap;
import java.util.Map;

/**
 * 我们很多业务 添加、修改、都需要进行校验,为了方便异常处理,之前我们添加过一个异常处理我们自定义的`BusinessException`。我们在给他添加一个校验处理的方法即可
 * @author 小z
 * @date 2024年10月21日 上午10:46
 */


@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)

    //处理数据校验异常
    public CommonResp handleVaildException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题{},异常类型{}",e.getMessage(),e.getClass());
        BindingResult result = e.getBindingResult();
        Map<String,String> errorMap=new HashMap<>();
        result.getFieldErrors().forEach((item)->{
            String field = item.getField();
            String message = item.getDefaultMessage();
            errorMap.put(field,message);
        });
        return new CommonResp(false,"数据校验异常",errorMap);
    }


    //处理自定义异常
    @ExceptionHandler(BusinessException.class)
    public CommonResp handlerBusinessException(BusinessException e){
        log.error(e.getCode().getMessage(),e);
        return new CommonResp(false,e.getCode().getMessage(),null);
    }

    //处理 系统异常
    @ExceptionHandler(Exception.class)
    public CommonResp handlerException(Exception e){
        log.error(e.getMessage(),e);
        return new CommonResp(false,"未知异常,请联系管理员",null);
    }

}
