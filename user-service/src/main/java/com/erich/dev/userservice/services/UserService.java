package com.erich.dev.userservice.services;

import com.erich.dev.userservice.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {


     Mono<UserDto> createUser(Mono<UserDto> userDto);

     Mono<UserDto> fetchUser(Long id);

     Flux<UserDto> fetchAllUser();

     Mono<UserDto> updateUser(Mono<UserDto> userDto , Long id);

     Mono<Void> deleteUser(Long id);
}
