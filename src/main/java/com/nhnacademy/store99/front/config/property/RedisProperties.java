package com.nhnacademy.store99.front.config.property;

import com.nhnacademy.store99.front.common.util.SecureKeyManagerUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 레디스 설정을 위한 properties
 *
 * @author seunggyu-kim
 */
@Setter
@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {
    private final SecureKeyManagerUtil secureKeyManagerUtil;

    private String host;
    private int port;
    private String password;
    private int database;

    /**
     * host가 secureKey로 암호화 되어있는 경우 복호화하여 설정
     *
     * @param host
     */
    public void setHost(final String host) {
        if (secureKeyManagerUtil.isEncrypted(host)) {
            this.host = secureKeyManagerUtil.loadConfidentialData(host);
        } else {
            this.host = host;
        }
    }

    /**
     * port가 secureKey로 암호화 되어있는 경우 복호화하여 설정
     *
     * @param port
     */
    public void setPort(final String port) {
        if (secureKeyManagerUtil.isEncrypted(port)) {
            this.port = Integer.parseInt(secureKeyManagerUtil.loadConfidentialData(port));
        } else {
            this.port = Integer.parseInt(port);
        }
    }

    /**
     * password가 secureKey로 암호화 되어있는 경우 복호화하여 설정
     *
     * @param password
     */
    public void setPassword(final String password) {
        if (secureKeyManagerUtil.isEncrypted(password)) {
            this.password = secureKeyManagerUtil.loadConfidentialData(password);
        } else {
            this.password = password;
        }
    }
}
