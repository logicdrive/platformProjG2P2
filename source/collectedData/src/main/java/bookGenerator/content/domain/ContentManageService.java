package bookGenerator.content.domain;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import bookGenerator.BootApplication;
import bookGenerator._global.customExceptionControl.CustomException;

@Service
@RequiredArgsConstructor
public class ContentManageService {
    public static ContentManageService getInstance() {
        return BootApplication.applicationContext.getBean(
            ContentManageService.class
        );
    }

    
    public Content findByContentId(Long contentId) {
        return Content.repository().findByContentId(contentId)
            .orElseThrow(ContentNotFoundException::new);
    }
}

@Getter
class ContentNotFoundException extends CustomException {       
    public ContentNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "ContentNotFoundException",
            "해당하는 Content(을)를 찾을 수 없습니다.\n" +
            "입력된 정보가 정확한지 확인해주세요."
        );
    }
}