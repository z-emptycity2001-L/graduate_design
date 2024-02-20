package com.study.springDesign.aop;

import com.study.springDesign.annotation.UpdateCommon;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class updateAop {

    @Pointcut("@annotation(com.study.springDesign.annotation.UpdateCommon)")
    private void Pointcut(){

    }
    @Around("Pointcut()")
    public Object afterReturn (ProceedingJoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if(method!=null){
            UpdateCommon annotation = method.getAnnotation(UpdateCommon.class);
            String token = annotation.token();
            System.out.println(token);
        }
        return null;
    }
}
