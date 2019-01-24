package me.freelife.exchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.freelife.exchange.domain.Exchange;
import me.freelife.exchange.domain.ExchangeRate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangeServiceImplTest {

    @Autowired
    ExchangeService exchangeService;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void 환율정보_API_요청테스트() throws IOException {

        Exchange exchange = Exchange.builder()
                .source("USD")
                .receipt("KRW")
                .exchangeRate(1119.93)
                .amount(100)
                .build();

        ExchangeRate test = exchangeService.getExchangeRate(exchange);
        assertThat(test.isSuccess()).isTrue();
        assertThat(test.getSource()).isEqualTo("USD");
        assertThat(test.getQuotes().getUSDKRW()).isNotNull();
        assertThat(test.getQuotes().getUSDJPY()).isNotNull();
        assertThat(test.getQuotes().getUSDPHP()).isNotNull();
        /*
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> jsonMap = jsonMap = jsonParser.parseMap(test);
        assertThat(jsonMap.get("success")).isEqualTo(true);
        assertThat(jsonMap.get("source")).isEqualTo("USD");
        System.out.println(((LinkedHashMap<String,Object>)jsonMap.get("quotes")).get("USDKRW") );
        */
    }
}