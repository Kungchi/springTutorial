package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class PerfomanceAspect {

    @Pointcut("@annotation(com.example.firstproject.annotation.RunningTime)") // 특정 어노테이션 대상 지정
    private void enableRunningTime() {}

    @Pointcut("execution(* com.example.firstproject..*.*(..))") // 기본 패키지의 모든 메소드
    private void cut() {}

    @Around("cut() && enableRunningTime()") // 실행 시점 두 조건을 모두 만족하는 대상을 전후로 부가 기능을 삽입
    public void logging(ProceedingJoinPoint joinPoint) throws Throwable {

        // 메소드 수행전
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 메소드 수행
        Object returningObj = joinPoint.proceed();

        // 메소드 수행후, 로깅
        stopWatch.stop();
        String methodName = joinPoint.getSignature().getName();
        log.info("{} dML 총 수행 시간 => {} sec", methodName, stopWatch.getTotalTimeSeconds());

    }
}
