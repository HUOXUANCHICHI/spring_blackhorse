package com.ablaze.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {

    @Pointcut("execution(void com.ablaze.dao.BookDao.update())")
    private void pt() {}
    @Pointcut("execution(int com.ablaze.dao.BookDao.select())")
    private void pt2() {}

    //@Before：前置通知，在原始方法运行之前执行
//    @Before("pt()")
    public void before() {
        System.out.println("before advice ...");
    }

    //@After：后置通知，在原始方法运行之后执行
//    @After("pt()")
    public void after() {
        System.out.println("after advice ...");
    }

    //@Around：环绕通知，在原始方法运行的前后执行  重点
    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice ...");
        //表示对原始操作的调用
        Object ret = pjp.proceed();
        System.out.println("around after advice ...");
        return ret;
    }
    @Around("pt2()")
    public Object aroundSelect(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice ...");
        //表示对原始操作的调用
        Integer ret = (Integer) pjp.proceed();
        System.out.println("around after advice ...");
        return ret+566;
    }


    //@AfterReturning：返回后通知，在原始方法执行完毕后运行，且原始方法执行过程中未出现异常现象
//    @AfterReturning("pt2()")
    public void afterReturning() {
        System.out.println("afterReturning advice ...");
    }

    //@AfterThrowing：抛出异常后通知，在原始方法执行过程中出现异常后运行
    @AfterThrowing("pt2()")
    public void afterThrowing() {
        System.out.println("afterThrowing advice ...");
    }
}
