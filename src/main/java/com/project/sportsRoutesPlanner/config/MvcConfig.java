package com.project.sportsRoutesPlanner.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path backgroundUploadDir = Paths.get("./route-background/");
        String backgroundUploadPath = backgroundUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/route-background/").addResourceLocations("file:/" + backgroundUploadPath + "/");
    }
}
