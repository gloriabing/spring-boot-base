package org.gloria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Create on 2016/12/8 16:53.
 *
 * @author : gloria.
 */
@EnableWebMvc
@Configuration
@ComponentScan({"org.gloria"})
public class ServletWebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
//        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
//        registry.addResourceHandler("/global/**").addResourceLocations("/assets/global");
//        registry.addResourceHandler("/views/**").addResourceLocations("/assets/admin/angularjs/views/");
//        registry.addResourceHandler("/js/**").addResourceLocations("/assets/admin/angularjs/js/");
//        registry.addResourceHandler("/tpl/**").addResourceLocations("/assets/admin/angularjs/tpl/");
//        registry.addResourceHandler("/layout/**").addResourceLocations("/assets/admin/layout/");

    }

    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/resources/templates");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
