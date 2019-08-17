package com.example.demo.config;

import org.beanio.StreamFactory;
import org.beanio.spring.BeanIOStreamFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanIoConfig {
	@Bean
    public StreamFactory streamFactory()  throws Exception {
        BeanIOStreamFactory beanIOStreamFactory = new BeanIOStreamFactory();
        return beanIOStreamFactory.getObject();
    }
}
