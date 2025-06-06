package com.alejandro.carritodecompras.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alejandro.carritodecompras.entities.User;

@Aspect
@Component
public class UserAspect {

    private static final Logger logger = LoggerFactory.getLogger(UserAspect.class);

    // To create advice that intercepts the save method of the user service file
    @Before("execution(public com.alejandro.carritodecompras.entities.User com.alejandro.carritodecompras.services.UserService.save(com.alejandro.carritodecompras.entities.User))")
    public void trimBefore(JoinPoint joinPoint) {

        logger.info("Aspecto ejecutado antes del método save() ------------------------");

        Object[] args = joinPoint.getArgs(); // Obtiene el argumento del método interceptado
        User userBefore = (User) args[0]; // Cast del argumento al tipo User

        userBefore.setName(userBefore.getName().trim());
        userBefore.setLastname(userBefore.getLastname().trim());
    }
}