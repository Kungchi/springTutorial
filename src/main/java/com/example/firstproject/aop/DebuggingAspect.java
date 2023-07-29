package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DebuggingAspect {

    // 대상 메소드 선택 api 패키지의 모든 메소드
    @Pointcut("execution(* com.example.firstproject.api.*.*(..))")
    private void cut() {}

    // 실행 시점 설정 : cut()의 대상이 수행되기 이전
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint) { // cut의 대상 메소드
        // 입력값 가져오기
        Object[] args = joinPoint.getArgs();

        // 클래스명
        String className = joinPoint.getTarget().getClass().getSimpleName();
        // 메소드 명
        String methodName = joinPoint.getSignature().getName();

        // 입력값 로깅
        for(Object obj : args) { // 향상된 for문 -> foreach문
            log.info("{} # {} 의 입력값 => {}", className, methodName, obj);
        }
    }

    // cut에 지정 대상 호출 성공 후!
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint, Object returnObj) { // cut의 대상 메소드, return값

        // 클래스명
        String className = joinPoint.getTarget().getClass().getSimpleName();
        // 메소드 명
        String methodName = joinPoint.getSignature().getName();

        log.info("{} # {} 의 반환값 => {}", className, methodName, returnObj);
    }
}
