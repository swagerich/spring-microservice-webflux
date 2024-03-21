package com.erich.dev.userservice.services.impl;

import com.erich.dev.userservice.dto.UserDto;
import com.erich.dev.userservice.repository.UserRepository;
import com.erich.dev.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service @RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Override
    public Mono<UserDto> createUser(Mono<UserDto> userDto) {
        return userDto.map(UserDto::toEntity).flatMap(userRepository::save).map(UserDto::fromEntity);
    }

    @Override
    public Mono<UserDto> fetchUser(Long id) {
        return userRepository.findById(id).switchIfEmpty(Mono.error(new RuntimeException("User not found " + id))).map(UserDto::fromEntity);
    }

    @Override
    public Flux<UserDto> fetchAllUser() {
        return userRepository.findAll().map(UserDto::fromEntity);
    }

    @Override
    public Mono<UserDto> updateUser(Mono<UserDto> userDto, Long id) {
        return   userRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")))
                        .flatMap(user -> userDto.map(UserDto::toEntity)
                        .doOnNext(u -> {
                         u.setId(user.getId());
                        })).flatMap(userRepository::save).map(UserDto::fromEntity);

    }


    @Override
    public Mono<Void> deleteUser(Long id) {
        return userRepository.findById(id).switchIfEmpty(Mono.error(new RuntimeException("User not found")))
                .flatMap(userRepository::delete);
    }
}
