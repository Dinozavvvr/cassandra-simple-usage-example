package ru.dinar.cassandraexmapleproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.dinar.cassandraexmapleproject.dto.user.SignUpRequestDto;
import ru.dinar.cassandraexmapleproject.model.user.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SignUpRequestDtoUserMapper {

    SignUpRequestDtoUserMapper INSTANCE = Mappers.getMapper( SignUpRequestDtoUserMapper.class );

    User from(SignUpRequestDto requestDto);

}
