package org.restaurant.shared.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.fasterxml.jackson.databind.util.ClassUtil.name;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customerAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Restaurant POS System API")
                        .version("0.1.0")
                        .description("REST API for Restaurant Point of Sale Management System")
                        .contact(new Contact()
                                .name("Shokhrukh Ernazarov")
                                .url("https://github.com/Shokhrukh2004"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
