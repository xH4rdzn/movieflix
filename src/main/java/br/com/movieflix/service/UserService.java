package br.com.movieflix.service;

import br.com.movieflix.controller.request.UserRequest;
import br.com.movieflix.entity.User;
import br.com.movieflix.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User saveUser(User user) {
    return userRepository.save(user);
  }
}
