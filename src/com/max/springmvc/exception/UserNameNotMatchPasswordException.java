package com.max.springmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用戶名和密碼不匹配")
public class UserNameNotMatchPasswordException extends RuntimeException{

    /*


     */
}
