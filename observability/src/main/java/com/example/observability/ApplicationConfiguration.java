package com.example.observability;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.concurrent.Executors;

@Slf4j
@Configuration
public class ApplicationConfiguration {

    @Bean
    public JsonPlaceHolderService jsonPlaceHolderService() {
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build();
        return factory.createClient(JsonPlaceHolderService.class);
    }

//    @Bean
//    public TomcatProtocolHandlerCustomizer<?> protocolHandlerCustomizer() {
//        return protocolHandler -> {
//            log.info("Configuring " + protocolHandler + " to use VirtualThreadPerTaskExecutor");
//            protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
//        };
//    }

}
