package com.summit.base.exception;

import com.summit.base.CommonResult;
import com.summit.common.uesrLogin.model.User;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * @ControllerAdvice注解内部可使用到@ExceptionHandler、@InitBinder、@ModelAttribute
 */
@ControllerAdvice
public class CustomControllerAdvice {

    @ModelAttribute
    public User newUser() {
        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model");
        return new User();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器");
    }

    /**
     * CustomException返回JSON
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public CommonResult handleCustomException(CustomException e) {
        System.out.println("===========应用到所有@RequestMapping注解的方法，在其抛出CustomException异常时执行");
        CommonResult result = new CommonResult();
        System.out.println(e.getMessage());
        e.printStackTrace();
        result.setSuccess(false);
        result.setResultType(e.getExceptionCode() + "");
        result.setResultMessage(e.getMessage());
        return result;
    }

    /**
     * 除CustomException外，其它异常跳转到错误页面
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleException2(Exception e) {
        System.out.println("===========应用到所有@RequestMapping注解的方法，在其抛出异常时执行");
        return "common/error";
    }
}
