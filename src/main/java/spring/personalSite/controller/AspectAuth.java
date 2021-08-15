package spring.personalSite.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import spring.personalSite.model.User;
import spring.personalSite.model.UserRole;
import spring.personalSite.util.Utils;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AspectAuth {

    ControllerHelper controllerHelper;

    AspectAuth(ControllerHelper controllerHelper) {
        this.controllerHelper = controllerHelper;
    }

    @Around("execution(* spring.personalSite.controller.ControllerWeibo.*(..))")
    public ModelAndView loginRequired(ProceedingJoinPoint joinPoint) {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(
                RequestAttributes.REFERENCE_REQUEST
        );
        User currentUser = this.controllerHelper.currentUser(request);
        if (currentUser.role != UserRole.guest) {
            Utils.log("currentUser %s", currentUser);
            try {
                return (ModelAndView) joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        } else {
            return new ModelAndView("redirect:/login/view");
        }

    }

    @Around("execution(* spring.personalSite.controller.ControllerTodo.*(..))")
    public ModelAndView todoDeleteRequired(ProceedingJoinPoint joinPoint) {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(
                RequestAttributes.REFERENCE_REQUEST
        );
        User currentUser = this.controllerHelper.currentUser(request);
        if (currentUser.role != UserRole.guest) {
            Utils.log("currentUser %s", currentUser);
            try {
                return (ModelAndView) joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        } else {
            return new ModelAndView("redirect:/login/view");
        }
    }
}
