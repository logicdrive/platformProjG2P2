package bookGenerator.comment.domain;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import bookGenerator.BootApplication;
import bookGenerator._global.customExceptionControl.CustomException;

@Service
@RequiredArgsConstructor
public class CommentManageService {
    public static CommentManageService getInstance() {
        return BootApplication.applicationContext.getBean(
            CommentManageService.class
        );
    }

    
    public Comment findByCommentId(Long commentId) {
        return Comment.repository().findByCommentId(commentId)
            .orElseThrow(CommentNotFoundException::new);
    }
}

@Getter
class CommentNotFoundException extends CustomException {       
    public CommentNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "CommentNotFoundException",
            "해당하는 Comment(을)를 찾을 수 없습니다.\n" +
            "입력된 정보가 정확한지 확인해주세요."
        );
    }
}