package io.spldeolin.bestpractice.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import io.spldeolin.bestpractice.exception.RequestParamValidateException;

@Aspect
public class RequestParamValidateAspect {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private final ExecutableValidator validator = factory.getValidator().forExecutables();

    private ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    @Pointcut("execution(* io.spldeolin.bestpractice.controller..*.*(..))")
    public void performance() {}

    @Before("performance()")
    public void Before(JoinPoint point) throws NoSuchMethodException, RequestParamValidateException {
        Object target = point.getThis();
        Object[] args = point.getArgs();
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        Set<ConstraintViolation<Object>> validResult = validator.validateParameters(target, method, args);

        if (!validResult.isEmpty()) {
            // 获得方法的参数名称数组
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            Iterator<ConstraintViolation<Object>> iterator = validResult.iterator();
            List<Map<String, String>> fieldValidErrors = new ArrayList<>();
            while (iterator.hasNext()) {
                ConstraintViolation<Object> constraintViolation = iterator.next();
                // 获得校验的参数路径信息
                PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
                // 获得校验的参数位置
                int paramIndex = pathImpl.getLeafNode().getParameterIndex();
                // 获得校验的参数名称
                String paramName = parameterNames[paramIndex];
                Map<String, String> fieldValidError = new HashMap<>();
                // 将需要的信息包装成简单的对象，方便后面处理
                fieldValidError.put("param", paramName);
                fieldValidError.put("message", constraintViolation.getMessage());
                fieldValidErrors.add(fieldValidError);
            }
            //抛到上层由GlobalExceptionHanler统一处理
            throw new RequestParamValidateException(fieldValidErrors);
        }
    }

}
