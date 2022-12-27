package ru.netology.springbootdemo4.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.springbootdemo4.enums.Authorities;
import ru.netology.springbootdemo4.exceptions.InvalidCredentials;
import ru.netology.springbootdemo4.exceptions.UnauthorizedUser;
import ru.netology.springbootdemo4.service.AuthorizationService;

import java.util.List;

@RestController
public class AuthorizationController {
    private final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    private final List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(
                user, password);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    private final ResponseEntity<String> unauthorizedUser(UnauthorizedUser unauthorizedUser) {
        System.out.println(unauthorizedUser.getMessage());
        return new ResponseEntity<>(unauthorizedUser.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(InvalidCredentials.class)
    private final ResponseEntity<String> unauthorizedUser(InvalidCredentials unauthorizedUser) {
        System.out.println(unauthorizedUser.getMessage());
        return new ResponseEntity<>(unauthorizedUser.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
