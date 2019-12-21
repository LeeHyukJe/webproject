package com.leehyukje.webproject;

import com.leehyukje.webproject.validator.ValidationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@SpringBootApplication
@PropertySource("config.properties")
public class WebprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebprojectApplication.class, args);
    }

    @Bean
    public ErrorAttributes errorAttributes(){
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest,
                                                          boolean includedStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includedStackTrace);
                Throwable error = getError(webRequest);

                if (error instanceof ValidationException) {
                    errorAttributes.put("errors", ((ValidationException) error).getErrors());
                }

                return errorAttributes;
            }
        };
    }

}
