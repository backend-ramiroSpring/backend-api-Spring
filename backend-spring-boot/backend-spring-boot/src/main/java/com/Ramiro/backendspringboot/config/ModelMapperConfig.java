package com.Ramiro.backendspringboot.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration      /* Para que reconosca que es una clase de configuracion */
public class ModelMapperConfig {
    @Bean       /* Para que pueda gestionar ese objeto, este objeto se genera con ello */
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
