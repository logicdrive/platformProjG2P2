package bookGenerator._global.externalSystemProxy.openai.generateProblems;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyResDto;

@Getter
@ToString
public class GenerateProblemsResDto implements ExternalSystemProxyResDto {
    @JsonProperty
    private List<String> contents;

    @JsonProperty
    private List<String> answers;
}