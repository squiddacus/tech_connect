package com.techelevator.tenmo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Objects;

@SpringBootApplication
public class Tenmo_Application_Server {

    public static void main(String[] args) {
        SpringApplication.run(Tenmo_Application_Server.class, args);
    }


    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Logger logger(InjectionPoint injectionPoint){
        return LoggerFactory.getLogger(Objects.requireNonNull(injectionPoint.getMethodParameter()).getContainingClass());
    }

}
