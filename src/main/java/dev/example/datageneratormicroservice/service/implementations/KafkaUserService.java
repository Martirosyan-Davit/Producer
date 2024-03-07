package dev.example.datageneratormicroservice.service.implementations;

import dev.example.datageneratormicroservice.entity.User;
import dev.example.datageneratormicroservice.service.interfaces.IKafkaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Service
@RequiredArgsConstructor
public class KafkaUserService implements IKafkaUserService {

    private final KafkaSender<String, Object> sender;

    @Override
    public void send(User user) {
        String topic = "users";

        sender.send(
                Mono.just(
                        SenderRecord.create(
                                topic,
                                0,
                                System.currentTimeMillis(),
                                String.valueOf(user.hashCode()),
                                user,
                                null
                        )
                )
        )
        .subscribe();
    }

}
