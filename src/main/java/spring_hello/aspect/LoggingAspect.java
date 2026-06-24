package spring_hello.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* spring_hello..*(..))")
    private void publicMethods() {}

    @Before("publicMethods()")
    public void helloAspect (){
        System.out.println("Before Aspect!");

    }
}
