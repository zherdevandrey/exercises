package com.example.observability;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ObservabilityApplication implements CommandLineRunner {

    private final ObservationRegistry observationRegistry;
    private final JsonPlaceHolderService jsonPlaceHolderService;

    public ObservabilityApplication(ObservationRegistry observationRegistry, JsonPlaceHolderService jsonPlaceHolderService) {
        this.observationRegistry = observationRegistry;
        this.jsonPlaceHolderService = jsonPlaceHolderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ObservabilityApplication.class, args);
    }

    @Override
	@Observed(name = "posts.load-all-posts", contextualName = "post-service.find-all")
    public void run(String... args) {

		jsonPlaceHolderService.findAll();

//        Observation
//				.createNotStarted("", observationRegistry)
//				.lowCardinalityKeyValue("author", "zherdev")
//				.contextualName("post-service.findAll")
//				.observe(jsonPlaceHolderService::findAll);


    }
}
