package com.rating.RatingServices.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class TopicConfig {

    @Bean
    public NewTopic createTopic(){
        return TopicBuilder.name("Chandra")
//                .partitions()
//                .replicas()
                .build();
    }
}
