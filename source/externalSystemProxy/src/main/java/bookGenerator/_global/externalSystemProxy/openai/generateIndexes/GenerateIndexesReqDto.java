package bookGenerator._global.externalSystemProxy.openai.generateIndexes;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyReqDto;

@Getter
@ToString
public class GenerateIndexesReqDto implements ExternalSystemProxyReqDto {
    private final String query;

    public GenerateIndexesReqDto(String query) {
        this.query = query;
    }

    public Map<String, Object> hashMap() {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("query", this.query);
        return hashMap;
    }
}