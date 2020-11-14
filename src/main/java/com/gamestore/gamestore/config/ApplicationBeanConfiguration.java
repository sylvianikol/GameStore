package com.gamestore.gamestore.config;

import com.gamestore.gamestore.domain.services.Cart;
import com.gamestore.gamestore.domain.services.impls.CartImpl;
import com.gamestore.gamestore.domain.utils.ValidationUtil;
import com.gamestore.gamestore.domain.utils.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }

    @Bean
    public BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Cart cart() {
        return new CartImpl();
    }

}
