package dev.fbiopereira.eventsdemo.entrypoint;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exchanges")
@Tag(name = "Exchange Fanout", description = "Operações para gerenciar as Exchanges do tipo Fanout")
public class ExchangeFanoutController {


}
