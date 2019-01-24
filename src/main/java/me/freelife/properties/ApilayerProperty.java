package me.freelife.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "apilayer")
public class ApilayerProperty {
    private String accessKey;
    private String currencies;
    private String format;
}
