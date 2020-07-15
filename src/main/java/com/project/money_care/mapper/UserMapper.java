package com.project.money_care.mapper;

import com.project.money_care.dto.UserDto;
import com.project.money_care.model.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
   UserDto userToDto(Users user);
   Users toUser(UserDto userDto);
}
