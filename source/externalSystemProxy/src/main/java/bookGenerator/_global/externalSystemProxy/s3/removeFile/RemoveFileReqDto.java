package bookGenerator._global.externalSystemProxy.s3.removeFile;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

import bookGenerator._global.externalSystemProxy.infra.ExternalSystemProxyReqDto;

@Getter
@ToString
public class RemoveFileReqDto implements ExternalSystemProxyReqDto {
    private final String fileUrl;

    public RemoveFileReqDto(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Map<String, Object> hashMap() {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("fileUrl", this.fileUrl);
        return hashMap;
    }
}