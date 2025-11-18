package br.com.movieflix.controller;

import br.com.movieflix.controller.request.UserRequest;
import br.com.movieflix.controller.response.UserResponse;
import br.com.movieflix.entity.User;
import br.com.movieflix.mapper.UserMapper;
import br.com.movieflix.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }


  @PostMapping("/register")
  public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
    User savedUser = userService.saveUser(UserMapper.toUser(request));

    return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(savedUser));
  }
}
