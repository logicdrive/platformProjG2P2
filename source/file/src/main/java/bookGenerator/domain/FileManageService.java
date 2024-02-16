package bookGenerator.domain;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import bookGenerator.BootApplication;
import bookGenerator._global.customExceptionControl.CustomException;

@Service
@RequiredArgsConstructor
public class FileManageService {
    public static FileManageService getInstance() {
        return BootApplication.applicationContext.getBean(
            FileManageService.class
        );
    }

    
    public File findByIdOrThrow(Long id) {
        return File.repository().findById(id)
            .orElseThrow(() -> new FileNotFoundException());
    }


    public void delete(File file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}

@Getter
class FileNotFoundException extends CustomException {       
    public FileNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "FileNotFoundException",
            "해당하는 File(을)를 찾을 수 없습니다.\n" +
            "입력된 정보가 정확한지 확인해주세요."
        );
    }
}