package com.training;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrainingTaskForDynamicCategoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingTaskForDynamicCategoryApplication.class, args);
	}
	@Bean
	public ModelMapper mapper(){
		ModelMapper mapper=new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		mapper.getConfiguration().setFullTypeMatchingRequired(true);
		return mapper;
	}

}
