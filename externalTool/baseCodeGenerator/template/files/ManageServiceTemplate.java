package [[SERVICE_INFO.PACKAGE_NAME]].domain;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import [[SERVICE_INFO.PACKAGE_NAME]].BootApplication;
import [[SERVICE_INFO.PACKAGE_NAME]]._global.customExceptionControl.CustomException;

@Service
@RequiredArgsConstructor
public class [[TEMPLATE.NAME]]ManageService {
    public static [[TEMPLATE.NAME]]ManageService getInstance() {
        return BootApplication.applicationContext.getBean(
            [[TEMPLATE.NAME]]ManageService.class
        );
    }

    
    public [[TEMPLATE.NAME]] findByIdOrThrow(Long id) {
        return [[TEMPLATE.NAME]].repository().findById(id)
            .orElseThrow(() -> new [[TEMPLATE.NAME]]NotFoundException());
    }
}

@Getter
class [[TEMPLATE.NAME]]NotFoundException extends CustomException {       
    public [[TEMPLATE.NAME]]NotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "[[TEMPLATE.NAME]]NotFoundException",
            "해당하는 [[TEMPLATE.NAME]](을)를 찾을 수 없습니다.\n" +
            "입력된 정보가 정확한지 확인해주세요."
        );
    }
}