package org.nihongo_deb.DeadLinkSearcher;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.nihongo_deb.DeadLinkSearcher.DTO.RegistrationUserDto;
import org.nihongo_deb.DeadLinkSearcher.Entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeadLinkSearcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeadLinkSearcherApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.addMappings(new PropertyMap<RegistrationUserDto, User>() {
			@Override
			protected void configure() {
				skip(destination.getId());
			}
		});

		return modelMapper;
	}
}
