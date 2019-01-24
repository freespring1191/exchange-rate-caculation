package me.freelife.exchange;

import me.freelife.exchange.domain.Exchange;
import me.freelife.exchange.domain.ExchangeRate;
import me.freelife.properties.ApilayerProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Duration;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private RestTemplate restTemplate;
    private ApilayerProperty apilayerProperty;

    public ExchangeServiceImpl(RestTemplateBuilder builder, ApilayerProperty apilayerProperty) {
        this.restTemplate = builder
                .setConnectTimeout(Duration.ofSeconds(3))
                .build();
        this.apilayerProperty = apilayerProperty;
    }

    @Override
    public ExchangeRate getExchangeRate(Exchange exchange) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("access_key", apilayerProperty.getAccessKey());
        map.add("currencies",apilayerProperty.getCurrencies());
        map.add("source",exchange.getSource());
        map.add("format",apilayerProperty.getFormat());

        URI uri= UriComponentsBuilder.fromHttpUrl("http://apilayer.net/api/live")
                .queryParams(map)
                .encode()
                .build()
                .toUri();

        return this.restTemplate.getForObject(uri, ExchangeRate.class);
    }
}
