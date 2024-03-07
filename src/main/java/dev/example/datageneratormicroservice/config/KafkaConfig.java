package dev.example.datageneratormicroservice.config;

import com.jcabi.xml.XML;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String services;

    private final XML settings;

    @Bean
    public NewTopic userTopic() {
        return TopicBuilder.name("users")
                .partitions(5)
                .replicas(1)
                .config(
                        TopicConfig.RETENTION_MS_CONFIG,
                        String.valueOf(Duration.ofDays(3).toMillis())
                )
                .build();
    }

    @Bean
    public SenderOptions<String, Object> senderOptions() {
        Map<String, Object> props = new HashMap<>(3);
        props.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                services
        );
        props.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                new TextXPath(this.settings, "//keySerializer")
                        .toString()
        );

        props.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                new TextXPath(this.settings, "//valueSerializer")
                        .toString()
        );

        return SenderOptions.create(props);
    }

    @Bean
    public KafkaSender<String, Object> sender() {
        return  KafkaSender.create(senderOptions());
    }

}
