package br.com.movieflix.mapper;


import br.com.movieflix.controller.request.UserRequest;
import br.com.movieflix.controller.response.UserResponse;
import br.com.movieflix.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {


  public static User toUser(UserRequest request) {
    return User
        .builder()
        .name(request.name())
        .email(request.email())
        .password(request.password())
        .build();
  }

  public static UserResponse toUserResponse(User user) {
    return UserResponse
        .builder()
        .id(user.getId())
        .email(user.getEmail())
        .name(user.getName())
        .build();
  }


}
