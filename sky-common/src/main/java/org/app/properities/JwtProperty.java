package org.app.properities;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 作者:疏狂难除
 * 时间:2024-02-18
 */
@Component
@ConfigurationProperties(prefix="sky.jwt")
@Data
public class JwtProperty {
    /**
     * jwt密钥
     *
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

}
