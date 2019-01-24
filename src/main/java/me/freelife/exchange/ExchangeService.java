package me.freelife.exchange;

import me.freelife.exchange.domain.Exchange;
import me.freelife.exchange.domain.ExchangeRate;

public interface ExchangeService {

     ExchangeRate getExchangeRate(Exchange exchange);
}
