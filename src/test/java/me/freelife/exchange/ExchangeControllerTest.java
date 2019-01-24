package me.freelife.exchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.freelife.exchange.domain.Exchange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ExchangeService exchangeService;

    @Test
    public void 컨트롤러_요청_응답테스트() throws Exception {

        Exchange exchange = Exchange.builder()
                    .source("USD")
                    .receipt("KRW")
                    .exchangeRate(1119.93)
                    .amount(100)
                    .build();

        mockMvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("source",exchange.getSource())
                .param("receipt",exchange.getReceipt())
                .param("exchangeRate",String.valueOf(exchange.getExchangeRate()))
                .param("amount", String.valueOf(exchange.getAmount()))
                .accept(MediaType.TEXT_HTML)
                ) //요청타입
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("receiptAmount",is(111993.0)))
                // .andExpect(model().attribute("exchange",hasProperty("receipt", is("KRW"))));
        ;
    }

    @Test
    public void 최초_로딩시_리다이렉트_테스트() throws Exception {

        Exchange exchange = Exchange.builder()
                .source(null)
                .receipt(null)
                .build();

        mockMvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("source",exchange.getSource())
                .param("receipt",exchange.getReceipt())
                .param("exchangeRate",String.valueOf(exchange.getExchangeRate()))
                .param("amount", String.valueOf(exchange.getAmount()))
                .accept(MediaType.TEXT_HTML)
        ) //요청타입
                .andDo(print())
                .andExpect(status().is3xxRedirection())
        ;
    }

}