package bookGenerator.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;

import lombok.Data;
import lombok.ToString;

import bookGenerator._global.event.UserNameUpdated;
import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import bookGenerator.domain.User;
import bookGenerator.domain.UserManageService;


@Data
@ToString
class UpdateNameReqDto {
    String name;
}



@RestController
@Transactional
@RequestMapping("/users")
public class UpdateNameEndPoints {

    @PutMapping("/updateName")
    public ResponseEntity<Void> updateName(@RequestHeader("User-Id") Long userId, @RequestBody UpdateNameReqDto updateNameReqDto) {
        try {

            CustomLogger.debugObject(CustomLoggerType.ENTER, updateNameReqDto);


            User userToUpdate = UserManageService.getInstance().findByIdOrThrow(userId);
            userToUpdate.setName(updateNameReqDto.getName());
            User savedUser = User.repository().save(userToUpdate);
            
            (new UserNameUpdated(savedUser)).publish();


            return ResponseEntity.status(HttpStatus.OK).build();

        } catch(Exception e) {
            CustomLogger.errorObject(e, updateNameReqDto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
