package cn.enaium.misc.config;

import cn.enaium.misc.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Enaium
 */
@ControllerAdvice
public class ExceptionConfig {
    @ResponseBody
    @ExceptionHandler
    public Result<Exception> handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new Result<>(400, e.getMessage(), e);
    }
}
