package ru.test.app.aop;

import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import ru.test.app.entity.TestObject;
import ru.test.app.repository.TestObjectRepository;

import java.util.Arrays;

@Aspect
@Component
public class TestObjectUpdateInterceptorAspect {
    private static final Logger logger = LoggerFactory.getLogger(TestObjectUpdateInterceptorAspect.class);
    @Autowired
    private TestObjectRepository repository;

    @Around(value = "execution(* ru.test.app.repository.TestObjectRepository.save(..)) && args(toSave)")
    public Object aroundTestObjectSave(ProceedingJoinPoint joinPoint, TestObject toSave) throws Throwable {
        logger.info("Object argument: {}", toSave.toString());
        logger.info("Object before: {}",
                (toSave != null && toSave.getId() != null) ?
                repository.findById(toSave.getId()).orElse(null) :
                null);
        Object[] args = joinPoint.getArgs(); //Аргументы можно достать отсюда
        Object object = joinPoint.proceed(args);
        logger.info("Object after: {}", repository.findById(((TestObject) object).getId()).orElse(null));
        return object;
    }

    @Around(value = "@annotation(ru.test.app.annotation.GenerateEvents)")
    public Object aroundTrampampam(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Annotation processing...");
        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).forEach(a -> logger.info("Object argument: {}", a));
        Object result = joinPoint.proceed(args);
        logger.info("Object after: {}", result);
        return result;
    }
}
