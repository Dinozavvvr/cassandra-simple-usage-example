package ru.dinar.cassandraexmapleproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dinar.cassandraexmapleproject.dto.user.SignUpRequestDto;
import ru.dinar.cassandraexmapleproject.service.PlaneSignUpService;
import ru.dinar.cassandraexmapleproject.service.SignUpService;

@RestController
@RequestMapping("/api/v1/user/sign-up")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    /**
     * Регистрация новых пользователей в системе
     * */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody SignUpRequestDto signUpRequestDto) {
        signUpService.signUp(signUpRequestDto);
        return ResponseEntity.ok().build();
    }

}
