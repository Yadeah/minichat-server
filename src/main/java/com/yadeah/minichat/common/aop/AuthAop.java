package com.yadeah.minichat.common.aop;

import com.yadeah.minichat.common.annotation.Auth;
import com.yadeah.minichat.common.component.SessionManager;
import com.yadeah.minichat.common.constant.system.HttpResponseStatus;
import com.yadeah.minichat.common.exception.BusinessException;
import com.yadeah.minichat.common.exception.ExceptionController;
import com.yadeah.minichat.common.request.BaseRequest;
import com.yadeah.minichat.common.response.HttpResponse;
import com.yadeah.minichat.common.utils.log.LogUtils;
import io.swagger.annotations.ApiModelProperty;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.UUID;

/**
 * 认证拦截
 */
@Aspect
@Component
public class AuthAop implements Ordered {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private ExceptionController exceptionController;

    @Pointcut("@annotation(com.yadeah.minichat.common.annotation.Auth)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Long startTime = System.currentTimeMillis();
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            // 请求标识
            String requestId = UUID.randomUUID().toString();
            // sessionId
            String sessionId = request.getHeader("session-id");
            // accountId
            String accountId = request.getHeader("account-id");
            // ip
            String ip = request.getRemoteAddr();
            // uri
            String uri = request.getRequestURI();
            // httpMethod
            String httpMethod = request.getMethod();
            // classMethod
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            String classMethod = String.format("%s.%s", signature.getDeclaringTypeName(), signature.getName());
            // 参数
            Object[] params = pjp.getArgs();

            // 身份认证
            Auth auth = signature.getMethod().getAnnotation(Auth.class);
            if (auth != null && auth.auth()) {
                // 需要登录认证，判断sessionId和accountId
                if (StringUtils.isEmpty(sessionId) || StringUtils.isEmpty(accountId) || !sessionId.equals(sessionManager.getSession(accountId))) {
                    LogUtils.request(requestId, sessionId, accountId, ip, uri, httpMethod, classMethod, params);
                    LogUtils.response(requestId, HttpResponseStatus.AUTH_FAIL);
                    return exceptionController.catchBusinessException(new BusinessException(requestId, HttpResponseStatus.AUTH_FAIL));
                }
            }

            // 校验params
            for (Object param : params) {
                if (param instanceof BaseRequest) {
                    // 校验必传值
                    for (Class<?> item = param.getClass(); item != BaseRequest.class; item = item.getSuperclass()) {
                        for (Field field : item.getDeclaredFields()) {
                            ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                            if (apiModelProperty != null && apiModelProperty.required() && ObjectUtils.isEmpty(item.getDeclaredField(field.getName()))) {
                                LogUtils.request(requestId, sessionId, accountId, ip, uri, httpMethod, classMethod, params);
                                LogUtils.response(requestId, HttpResponseStatus.PARAMS_NULL);
                                return exceptionController.catchBusinessException(new BusinessException(requestId, HttpResponseStatus.PARAMS_NULL, apiModelProperty.name()));
                            }
                        }
                    }
                    // 设置基类属性
                    ((BaseRequest) param).setRequestId(requestId);
                    ((BaseRequest) param).setRequestAccountId(accountId);
                } else {
                    RequestParam requestParam = param.getClass().getAnnotation(RequestParam.class);
                    if (requestParam != null && requestParam.required() && ObjectUtils.isEmpty(requestParam)) {
                        LogUtils.request(requestId, sessionId, accountId, ip, uri, httpMethod, classMethod, params);
                        LogUtils.response(requestId, HttpResponseStatus.PARAMS_NULL);
                        return exceptionController.catchBusinessException(new BusinessException(requestId, HttpResponseStatus.PARAMS_NULL, requestParam.name()));
                    }
                }
            }

            // 打印日志
            LogUtils.request(requestId, sessionId, accountId, ip, uri, httpMethod, classMethod, params);

            Object response = pjp.proceed(params);

            // 打印响应日志
            LogUtils.response(requestId, System.currentTimeMillis()-startTime, response);

            // 返回
            return response;
        } catch (Exception e) {
            return exceptionController.catchException(e);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
