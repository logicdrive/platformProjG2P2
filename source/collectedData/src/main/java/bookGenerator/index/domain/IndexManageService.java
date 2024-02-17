package bookGenerator.index.domain;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import bookGenerator.BootApplication;
import bookGenerator._global.customExceptionControl.CustomException;

@Service
@RequiredArgsConstructor
public class IndexManageService {
    public static IndexManageService getInstance() {
        return BootApplication.applicationContext.getBean(
            IndexManageService.class
        );
    }

    
    public Index findByIndexId(Long indexId) {
        return Index.repository().findByIndexId(indexId)
            .orElseThrow(IndexNotFoundException::new);
    }
}

@Getter
class IndexNotFoundException extends CustomException {       
    public IndexNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "IndexNotFoundException",
            "해당하는 Index(을)를 찾을 수 없습니다.\n" +
            "입력된 정보가 정확한지 확인해주세요."
        );
    }
}