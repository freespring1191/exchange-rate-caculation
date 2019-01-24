package me.freelife.exchange.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Quotes {

    @JsonProperty("USDKRW")
    private Double USDKRW;
    @JsonProperty("USDJPY")
    private Double USDJPY;
    @JsonProperty("USDPHP")
    private Double USDPHP;

    public Double getCurrency(String receipt){

        if(receipt.isEmpty())
            return null;

        switch (receipt){
            case "KRW":
                return this.USDKRW;
            case "JPY":
                return this.USDJPY;
            case "PHP":
                return this.USDPHP;
        }
        return null;
    }
}
