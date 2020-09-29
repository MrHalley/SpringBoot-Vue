package org.sang;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties("my")
public class User {
    private String name;
    private String address;
    private List<String> favorites;
}
