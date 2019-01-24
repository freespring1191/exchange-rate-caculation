package me.freelife.exchange.domain;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @EqualsAndHashCode
@ToString
public class Exchange {

    private String receipt; //수취국가
    private Double exchangeRate; //환율
    private String source;  //통화국가

    @NotNull
    @Min(0)
    @Max(10000)
    private Integer amount; //송금액
}
