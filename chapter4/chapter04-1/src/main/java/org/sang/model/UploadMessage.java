package org.sang.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 是否上传成功返回的消息
 */
@Data
@Component
public class UploadMessage {
    //0代表成功 ; -1 代表失败
    private int status;
    private String message;
    private Map<String,String> files = new HashMap<>();
}
