package dev.example.datageneratormicroservice.web.mapper;

import dev.example.datageneratormicroservice.entity.User;
import dev.example.datageneratormicroservice.web.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper extends IMappable<User, UserDto> {
}
