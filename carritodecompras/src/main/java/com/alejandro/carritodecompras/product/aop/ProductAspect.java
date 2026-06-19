package com.alejandro.carritodecompras.product.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alejandro.carritodecompras.product.models.entities.Product;


@Aspect
@Component
public class ProductAspect {

    private static final Logger logger = LoggerFactory.getLogger(ProductAspect.class);

    // To create advice that intercepts the save method of the product service file
    @Before("execution(public com.alejandro.carritodecompras.product.models.entities.Product com.alejandro.carritodecompras.product.services.ProductService.save(com.alejandro.carritodecompras.product.models.entities.Product))")
    public void trimBefore(JoinPoint joinPoint) {

        logger.info("Execution intercepted before save() method ------------------------");

        Object[] args = joinPoint.getArgs(); // Get the argument of the intercepted method
        Product productBefore = (Product) args[0]; // Cast the argument to the Product type

        productBefore.setName(productBefore.getName().trim());
        productBefore.setDescription(productBefore.getDescription().trim());
        productBefore.setBrand(productBefore.getBrand().trim());
        productBefore.setCategory(productBefore.getCategory().trim());
    }

}