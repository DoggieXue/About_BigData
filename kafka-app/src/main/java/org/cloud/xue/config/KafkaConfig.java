package org.cloud.xue.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.cloud.xue.dto.PvAndUvDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName KafkaConfig
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月01日 15:04:47
 * @Version 1.0
 **/
@Configuration
public class KafkaConfig {
    public static final String TOPIC_NAME = "event_pvuvmau";

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String DEFAULT_SERVER;

    @Bean
    public ProducerFactory<String, PvAndUvDto> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, DEFAULT_SERVER);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, PvAndUvDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic defaultTopic() {
        return TopicBuilder.name(TOPIC_NAME).build();
    }
}
