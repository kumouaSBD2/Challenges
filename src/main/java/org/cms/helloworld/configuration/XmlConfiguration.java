package org.cms.helloworld.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
public class XmlConfiguration {

    @Bean
    ApplicationContext applicationContext (){ return new ClassPathXmlApplicationContext("beans.xml");}

}
