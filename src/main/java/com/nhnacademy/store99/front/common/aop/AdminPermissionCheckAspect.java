package com.nhnacademy.store99.front.common.aop;

import com.nhnacademy.store99.front.admin.exception.AdminPermissionDeniedException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AdminPermissionCheckAspect {
    @Around("@annotation(AdminPermissionCheck)")
    public Object checkPermission(final ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (FeignException.Forbidden ex) {
            throw new AdminPermissionDeniedException();
        }
    }
}
