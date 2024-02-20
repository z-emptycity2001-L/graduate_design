package com.study.springDesign.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)//表示此注解可以作用在方法上
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface UpdateCommon {
    String token() default " ";
}
