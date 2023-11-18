package ru.dinar.cassandraexmapleproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dinar.cassandraexmapleproject.dto.user.SignUpRequestDto;
import ru.dinar.cassandraexmapleproject.model.user.User;
import ru.dinar.cassandraexmapleproject.model.user.UserDto;
import ru.dinar.cassandraexmapleproject.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("{surname}/{login}")
    public ResponseEntity<UserDto> find(@PathVariable String login, @PathVariable String surname) {
        return ResponseEntity.ok(userService.find(surname, login));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserDto userDto) {
        userService.update(userDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> findAll(@RequestBody UserDto userDto) {
        userService.delete(userDto);
        return ResponseEntity.ok().build();
    }

}
