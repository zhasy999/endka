package kz.iitu.library.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "kz.iitu.library")
@PropertySource("application.properties")
public class LibraryConfiguration {
}