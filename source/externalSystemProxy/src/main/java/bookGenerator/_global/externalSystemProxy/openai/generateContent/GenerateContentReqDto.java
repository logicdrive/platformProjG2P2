package bookGenerator._global.externalSystemProxy.openai.generateContent;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyReqDto;

@Getter
@ToString
public class GenerateContentReqDto implements ExternalSystemProxyReqDto {
    private final String query;

    public GenerateContentReqDto(String query) {
        this.query = query;
    }

    public Map<String, Object> hashMap() {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("query", this.query);
        return hashMap;
    }
}