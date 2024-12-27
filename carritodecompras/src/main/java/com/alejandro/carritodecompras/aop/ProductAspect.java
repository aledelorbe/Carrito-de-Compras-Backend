package com.alejandro.carritodecompras.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alejandro.carritodecompras.entities.Product;

@Aspect
@Component
public class ProductAspect {

    private static final Logger logger = LoggerFactory.getLogger(ProductAspect.class);

    // To create advice that intercepts the save method of the product service file
    @Before("execution(public com.alejandro.carritodecompras.entities.Product com.alejandro.carritodecompras.services.ProductService.save(com.alejandro.carritodecompras.entities.Product))")
    public void trimBefore(JoinPoint joinPoint) {

        logger.info("Aspecto ejecutado antes del método save() ------------------------");

        Object[] args = joinPoint.getArgs(); // Obtiene el argumento del método interceptado
        Product productBefore = (Product) args[0]; // Cast del argumento al tipo Product

        productBefore.setName(productBefore.getName().trim());
        productBefore.setDescription(productBefore.getDescription().trim());
        productBefore.setBrand(productBefore.getBrand().trim());
        productBefore.setCategory(productBefore.getCategory().trim());
    }
}