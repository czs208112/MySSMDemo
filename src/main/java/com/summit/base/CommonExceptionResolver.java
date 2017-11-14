package com.summit.base;

import org.apache.commons.fileupload.FileUploadBase;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CommonExceptionResolver extends SimpleMappingExceptionResolver {

//    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
//
//        ModelAndView modelAndView = new ModelAndView();
//
//        Map<String, Object> modal = new HashMap<>();
//        modal.put("errorType", e.getClass().getName());
//        modal.put("errorMsg", e.getMessage());
//        modelAndView.getModel();
//        return modelAndView;
//    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String viewName = determineViewName(ex, request);
        response.setCharacterEncoding("UTF-8");
        if (ex instanceof MaxUploadSizeExceededException) {
            //  if (viewName != null) {// JSP格式返回
            if (!(request.getHeader("accept").contains("application/json") || (request.getHeader("X-Requested-With") != null && request
                    .getHeader("X-Requested-With").contains("XMLHttpRequest")))) {
                // 如果不是异步请求
                // Apply HTTP status code for error views, if specified.
                // Only apply it if we're processing a top-level request.
                Integer statusCode = determineStatusCode(request, viewName);
                if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode);
                }
                System.out.println("JSP格式返回" + viewName);
                return getModelAndView(viewName, ex, request);
            } else {// JSON格式返回
                try {
                    PrintWriter writer = response.getWriter();
                    writer.write(ex.getMessage());
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("JSON格式返回" + viewName);
                return null;
            }
//        } else {
//            return null;
//        }
        } else {
            return null;
        }
    }
}
