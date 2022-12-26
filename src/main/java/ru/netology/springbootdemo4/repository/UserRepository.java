package ru.netology.springbootdemo4.repository;

import org.springframework.stereotype.Repository;
import ru.netology.springbootdemo4.domain.User;
import ru.netology.springbootdemo4.enums.Authorities;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private ConcurrentHashMap<ConcurrentHashMap<String, String>, List<Authorities>> userRepo;

    public UserRepository() {
        this.userRepo = new ConcurrentHashMap<>();

        this.userRepo.put(addUsers("admin", "admin"), List.of(Authorities.WRITE));
        this.userRepo.put(addUsers("Sanya", "qwerty"), List.of(Authorities.values()));
      }

    private ConcurrentHashMap<String, String> addUsers(String user, String password){
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put(user, password);
        return map;

    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        if (!user.isEmpty() && !password.isEmpty()) {
            return userRepo.getOrDefault(addUsers(user, password), Collections.emptyList());
        }
        return Collections.emptyList();
    }
}