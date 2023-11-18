package ru.dinar.cassandraexmapleproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.dinar.cassandraexmapleproject.model.user.User;
import ru.dinar.cassandraexmapleproject.model.user.UserDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoUserMapper {

    UserDtoUserMapper INSTANCE = Mappers.getMapper( UserDtoUserMapper.class );

    UserDto from(User user);
    User to(UserDto userDto);

    List<UserDto> from(List<User> all);
    List<User> to(List<UserDto> all);

}
