package com.test.cvprocessor;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
class ResourceHandler extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        // Load Static file path from temp directory to show converted PDF
        // document generated programatically
        registry.addResourceHandler("/**")
                .addResourceLocations(new File(System.getProperty("java.io.tmpdir")).toURI().toString());
        registry.addResourceHandler("/webjars/**", "/css/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/", "classpath:/static/css/");
    }
}
