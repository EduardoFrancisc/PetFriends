package br.com.infnet.petFriends_almoxarifado.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_PEDIDOS = "exchange.pedidos";
    public static final String FILA_ALMOXARIFADO_PEDIDOS = "fila.almoxarifado.pedidos";

    // Garante que a exchange exista na infraestrutura, mesmo que o Almoxarifado suba antes do serviço de Pedidos.
    @Bean
    public FanoutExchange exchangePedidos() {
        return new FanoutExchange(EXCHANGE_PEDIDOS);
    }

    // O segundo parâmetro "true" indica que a fila não será perdida se o broker reiniciar.
    @Bean
    public Queue filaAlmoxarifadoPedidos() {
        return new Queue(FILA_ALMOXARIFADO_PEDIDOS, true);
    }

    // Inscreve a fila do almoxarifado na exchange genérica de pedidos
    @Bean
    public Binding bindingAlmoxarifadoPedidos(Queue filaAlmoxarifadoPedidos, FanoutExchange exchangePedidos) {
        return BindingBuilder.bind(filaAlmoxarifadoPedidos).to(exchangePedidos);
    }

    //pro payload ser mapeado para DTOs/Events.
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
