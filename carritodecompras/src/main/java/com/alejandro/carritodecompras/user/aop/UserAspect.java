package com.alejandro.carritodecompras.user.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alejandro.carritodecompras.user.models.entities.User;


@Aspect
@Component
public class UserAspect {

    private static final Logger logger = LoggerFactory.getLogger(UserAspect.class);

    // To create advice that intercepts the save method of the user service file
    @Before("execution(public com.alejandro.carritodecompras.user.models.entities.User com.alejandro.carritodecompras.user.services.UserService.save(com.alejandro.carritodecompras.user.models.entities.User))")
    public void trimBefore(JoinPoint joinPoint) {

        logger.info("Executing aspect before save() method ------------------------");

        Object[] args = joinPoint.getArgs(); // Get the argument of the intercepted method
        User userBefore = (User) args[0]; // Cast the argument to the User type

        userBefore.setName(userBefore.getName().trim());
        userBefore.setLastname(userBefore.getLastname().trim());
    }

}