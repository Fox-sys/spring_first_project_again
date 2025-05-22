package org.example.first_pr.application.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "jwt")
public class AuthProperties {
    private String secretKey;
    private int accessExpireTime; // В минутах
    private int refreshExpireTime;  // В днях

    public long getAccessExpireTimeMs() {
        return ((long) accessExpireTime) * 60L * 1000L;
    }

    public long getRefreshExpireTimeMs() {
        return ((long) refreshExpireTime) * 24L * 60L * 60L * 1000L;
    }
}
