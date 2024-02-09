package bookGenerator.domain;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import bookGenerator.BootApplication;
import bookGenerator._global.customExceptionControl.CustomException;

@Service
@RequiredArgsConstructor
public class TagManageService {
    public static TagManageService getInstance() {
        return BootApplication.applicationContext.getBean(
            TagManageService.class
        );
    }

    
    public Tag findByIdOrThrow(Long id) {
        return Tag.repository().findById(id)
            .orElseThrow(() -> new TagNotFoundException());
    }
}

@Getter
class TagNotFoundException extends CustomException {       
    public TagNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "TagNotFoundException",
            "해당하는 Tag(을)를 찾을 수 없습니다.\n" +
            "입력된 정보가 정확한지 확인해주세요."
        );
    }
}