package com.ning.home_admin.commons.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class UnauthorizedExceptionHandler {

    @ExceptionHandler(value = UnauthorizedException.class)
    public String handleUnauthorizedException(UnauthorizedException e) {
        log.debug("UnauthorizedException", e);
        return "error/403";
    }
}
