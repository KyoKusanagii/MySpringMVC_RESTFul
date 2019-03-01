package com.max.springmvc.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondtInterceptor implements HandlerInterceptor {


     /*
        該方法在目標方法之前被調用
        若返回值為true，則繼續呼叫後續的攔截器及目標方法
        若返回為false，則不再呼叫後續的攔截器及目標方法

        可以考慮做權限、日誌事務等
     */
     @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         System.out.println("SecondtInterceptor 的 preHandle");
         return true;
     }

     /*
        調用目標方法之後，但渲染視圖之前
        可以對request中的屬性或視圖做修改
      */

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("SecondtInterceptor 的 postHandle");
    }

    /*
        渲染視圖之後被呼叫，釋放資源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("SecondtInterceptor 的 afterCompletion");
    }
}
