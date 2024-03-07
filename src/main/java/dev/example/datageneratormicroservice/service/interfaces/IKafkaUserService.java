package dev.example.datageneratormicroservice.service.interfaces;

import dev.example.datageneratormicroservice.entity.User;

public interface IKafkaUserService {

    void send(User user);
}
