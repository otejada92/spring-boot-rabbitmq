package org.dev.ordersevices.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderMapper {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
