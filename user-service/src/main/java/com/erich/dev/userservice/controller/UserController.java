package com.erich.dev.userservice.controller;

import com.erich.dev.userservice.dto.UserDto;
import com.erich.dev.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<UserDto>>> findAll(){
        return Mono.just(ResponseEntity.ok().body(userService.fetchAllUser()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserDto>> findById(@PathVariable Long id){
        return userService.fetchUser(id).map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<UserDto>> save(@RequestBody Mono<UserDto> userDtoMono){
        return userService.createUser(userDtoMono).map(body ->  new ResponseEntity<>(body, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserDto>> update(@RequestBody Mono<UserDto> userDtoMono, @PathVariable Long id){
        return userService.updateUser(userDtoMono, id).map(body -> new ResponseEntity<>(body, HttpStatus.CREATED));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long id){
        return userService.deleteUser(id).map(body -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
}
