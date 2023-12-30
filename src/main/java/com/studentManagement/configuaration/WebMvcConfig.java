package com.studentManagement.configuaration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/stuProfileImages/**")
                .addResourceLocations("file:/C:/Users/Vini/Documents/workspace-spring-tool-suite-4-4.17.2.RELEASE.Project/StudentManagement/src/main/resources/static/stuProfileImages/");
    }
}
