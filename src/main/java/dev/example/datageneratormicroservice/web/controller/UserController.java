package dev.example.datageneratormicroservice.web.controller;

import dev.example.datageneratormicroservice.entity.User;
import dev.example.datageneratormicroservice.service.interfaces.IKafkaUserService;
import dev.example.datageneratormicroservice.web.dto.UserDto;
import dev.example.datageneratormicroservice.web.mapper.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final IKafkaUserService kafkaUserService;

    private final IUserMapper userMapper;

    @PostMapping("/send")
    public void send(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        kafkaUserService.send(user);
    }
}
