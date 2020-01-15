package com.ning.home_admin.commons.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author NKW
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface FebsEndPoint {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
