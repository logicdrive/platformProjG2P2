package bookGenerator.user.service;

import org.springframework.stereotype.Service;

import bookGenerator._global.exceptions.UserNotFoundException;

import bookGenerator.domain.User;
import bookGenerator.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserManageService {
    private final UserRepository userRepository;

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException());
    }
}
