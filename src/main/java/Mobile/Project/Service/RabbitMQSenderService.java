package Mobile.Project.Service;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Classe Service pour envoyer un email vers le script Python à travers RabbitMQ
 * @author Dell
 *
 */
@Service
public class RabbitMQSenderService {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${rabbitmq.exchange}")
	private String exchange;
	
	@Value("${rabbitmq.routingkey}")
	private String routingkey;	
	
	/**
	 * Méthode d'envoi au canal RabbitMQ
	 * @param msg
	 */
	public void send(String msg) {
		rabbitTemplate.convertAndSend(exchange, routingkey, msg);
	    
	}
}
