package ru.dinar.cassandraexmapleproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dinar.cassandraexmapleproject.dto.user.SignUpRequestDto;
import ru.dinar.cassandraexmapleproject.model.user.User;
import ru.dinar.cassandraexmapleproject.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

}
