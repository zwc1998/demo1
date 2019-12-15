package zwc.majiang.community.advice;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import zwc.majiang.community.Eception.CustomizeException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle( Throwable e, Model model){

        if (e instanceof CustomizeException){
            model.addAttribute("message","服务跑丢啦，哈哈哈哈");
        }else {
            model.addAttribute("message", "服务跑丢啦，哈哈哈哈");
        }
        return new ModelAndView("error");

    }

    }
