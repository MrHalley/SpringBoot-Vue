package org.sang.model;

import lombok.Data;

@Data
public class Chat {
    private String to;
    private String from;
    private String content;
}
