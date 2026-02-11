package com.gabriel.slot.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class RestControllerAspect {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper mapper;

    @Autowired(required = false)
    private HttpServletRequest request;

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {}

    @Around("restController()")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        Map<String, Object> parameters = getParameters(joinPoint);

        LOGGER.info("==> path(s): [{}], method(s): [{}], arguments: {} ",
                request.getRequestURI(), request.getMethod(), mapper.writeValueAsString(parameters));


        // Measure method execution time
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();

        LOGGER.info("<== path(s): [{}], method(s): [{}], executionTime: executed in {} ms, retuning: {}",
                request.getRequestURI(), request.getMethod(), stopWatch.getTotalTimeMillis(),mapper.writeValueAsString(result));

        return result;
    }




    private Map<String, Object> getParameters(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();

        HashMap<String, Object> map = new HashMap<>();

        String[] parameterNames = signature.getParameterNames();

        for (int i = 0; i < parameterNames.length; i++) {
            map.put(parameterNames[i], joinPoint.getArgs()[i]);
        }

        return map;
    }

}
