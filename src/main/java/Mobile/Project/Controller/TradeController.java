package Mobile.Project.Controller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Mobile.Project.Entity.Item;
import Mobile.Project.Entity.Trade;
import Mobile.Project.Entity.TradeItem;
import Mobile.Project.Entity.User;
import Mobile.Project.Repository.ItemRepository;
import Mobile.Project.Repository.TradeRepository;
import Mobile.Project.Repository.UserRepository;
import Mobile.Project.Service.RabbitMQSenderService;
import Mobile.Project.Service.TradeService;

/**
 * Classe Controller de Trade
 * @author Dell
 *
 */
@RestController
@RequestMapping("trade")
public class TradeController {

	@Autowired
	TradeService tradeService;
	
	/**
	 * Créer une demande de Trade 
	 * @return Réponse de la requete
	 */
	@GetMapping("/{seId}/{reId}/{seItemId}/{reItemId}")
	public ResponseEntity<?> createNewTrade(@PathVariable("seId") int seId,@PathVariable("reId") int reId,
			@PathVariable("seItemId") int seItemId,@PathVariable("reItemId") int reItemId)
	{
		return this.tradeService.addTrade(seId, reId, seItemId, reItemId);
	}
}
