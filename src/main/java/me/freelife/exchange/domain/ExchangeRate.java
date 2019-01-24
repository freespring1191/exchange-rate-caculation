package me.freelife.exchange.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExchangeRate {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("source")
    private String source;
    @JsonProperty("quotes")
    private Quotes quotes;

}
