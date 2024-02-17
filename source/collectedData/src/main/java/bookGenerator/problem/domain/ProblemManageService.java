package bookGenerator.problem.domain;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import bookGenerator.BootApplication;
import bookGenerator._global.customExceptionControl.CustomException;

@Service
@RequiredArgsConstructor
public class ProblemManageService {
    public static ProblemManageService getInstance() {
        return BootApplication.applicationContext.getBean(
            ProblemManageService.class
        );
    }

    
    public Problem findByProblemId(Long problemId) {
        return Problem.repository().findByProblemId(problemId)
            .orElseThrow(ProblemNotFoundException::new);
    }
}

@Getter
class ProblemNotFoundException extends CustomException {       
    public ProblemNotFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "ProblemNotFoundException",
            "해당하는 Problem(을)를 찾을 수 없습니다.\n" +
            "입력된 정보가 정확한지 확인해주세요."
        );
    }
}