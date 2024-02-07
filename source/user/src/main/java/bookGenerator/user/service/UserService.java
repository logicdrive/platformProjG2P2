package bookGenerator.user.service;

import org.springframework.stereotype.Service;

import bookGenerator._global.event.UserNameUpdated;
import bookGenerator._global.security.JwtTokenService;

import bookGenerator.domain.User;
import bookGenerator.domain.UserManageService;

import bookGenerator.user.reqDtos.SignInReqDto;
import bookGenerator.user.reqDtos.UpdateNameReqDto;

import lombok.NonNull;

@Service
public class UserService {
    public String tokenBySignIn(SignInReqDto signInReqDto) {
        return JwtTokenService.getInstance().tokenValue(signInReqDto);
    }

    public void updateName(@NonNull Long userId, UpdateNameReqDto updateNameReqDto) {
        User userToUpdate = UserManageService.getInstance().findByIdOrThrow(userId);
        userToUpdate.setName(updateNameReqDto.getName());
        User savedUser = User.repository().save(userToUpdate);
        
        (new UserNameUpdated(savedUser)).publishAfterCommit();
    }
}
