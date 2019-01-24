package me.freelife.exchange;

import me.freelife.exchange.domain.Exchange;
import me.freelife.exchange.domain.ExchangeRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

@Controller
public class ExchangeController {

    Logger log = LoggerFactory.getLogger(ExchangeController.class);

    private ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/")
    public String getHome(@Valid Exchange exchange, BindingResult bindingResult, Model model){

        log.info("receipt = "+exchange.getReceipt());

        //페이지 최초 로딩시 환율정보 컨트롤러 호출
        if(exchange.getReceipt() == null){
            return "redirect:/exchangeRate";
        }

        if(bindingResult.hasErrors()){
            FieldError fieldError = new FieldError("exchange", "amount", "송금액이 바르지 않습니다");
            bindingResult.addError(fieldError);
            return "exchange";
        }

        try {
            // 환율 계산
            Double receiptAmount =  (Math.round(exchange.getExchangeRate()*100)/100.0) * exchange.getAmount();
            model.addAttribute("receiptAmount", receiptAmount);
        } catch (Exception e) {
            FieldError fieldError = new FieldError("exchange", "amount", "환율 계산중 오류가 발생했습니다 관리자에게 문의하세요");
            bindingResult.addError(fieldError);
        }
        return "exchange";
    }

    @GetMapping("/exchangeRate")
    public String exchangeRate(Exchange exchange, BindingResult bindingResult, Model model) {
        //최초 로딩시 수취국가 없으면 KRW로 지정
        if(exchange.getReceipt() == null){
            exchange.setReceipt("KRW");
        }

        //최초 페이지 로딩시 송금국가 없으면 USD로 지정
        if(exchange.getSource() == null){
            exchange.setSource("USD");
        }

        try {
            ExchangeRate exchangeRate = exchangeService.getExchangeRate(exchange);
            Double currency = exchangeRate.getQuotes().getCurrency(exchange.getReceipt());
            exchange.setExchangeRate(currency);
        } catch (Exception e) {
            FieldError fieldError = new FieldError("exchange", "exchangeRate", "환율 정보를 가져오는데 문제가 있습니다 잠시 후 다시 시도하세요");
            bindingResult.addError(fieldError);
        }
        return "exchange";
    }

}
