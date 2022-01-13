package Mobile.Project.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import Mobile.Project.Entity.Item;
import Mobile.Project.Entity.Trade;
import Mobile.Project.Entity.TradeItem;
import Mobile.Project.Entity.User;
import Mobile.Project.Repository.ItemRepository;
import Mobile.Project.Repository.TradeRepository;
import Mobile.Project.Repository.UserRepository;

/**
 * Classe Service de Trading
 * @author Dell
 *
 */
@Service
public class TradeService {

	@Autowired
	TradeRepository tradeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	RabbitMQSenderService rabbitMqService;
	
	/**
	 * Créer une demande de Trading
	 * @param senderId : ID de user sender
	 * @param receiverId : ID de user receiver
	 * @param reItemId : ID de l'item to receive
	 * @param seItemId : ID de l'item to send
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> addTrade(int senderId,int receiverId,int reItemId,int seItemId)
	{
		User sender=this.userRepository.findById((long)senderId).get();
		User receiver=this.userRepository.findById((long)receiverId).get();
		Item sentItem=this.itemRepository.findById((long)reItemId).get();
		Item receivedItem=this.itemRepository.findById((long)seItemId).get();
		Trade trade=new Trade();
		trade.setIssueDate(LocalDateTime.now());
		trade.setModifiedBy("admin");
		trade.setModifiedDate(LocalDateTime.now());
		trade.setSender(sender);
		trade.setReceiver(receiver);
		TradeItem tradeItem=new TradeItem();
		tradeItem.setItem(sentItem);
		tradeItem.setTrade(trade);
		tradeItem.setUser(sender);
		
		TradeItem tradeItem_=new TradeItem();
		tradeItem_.setItem(receivedItem);
		tradeItem_.setTrade(trade);
		tradeItem_.setUser(receiver);
		Set<TradeItem> l1=new HashSet<TradeItem>();
		l1.add(tradeItem);
		//sentItem.setTradeitem(l1);
		this.itemRepository.save(sentItem);
		//receivedItem.getTradeitem().add(tradeItem);
		//this.itemRepository.save(receivedItem);
		//trade.setTradeitem(l1);
		trade.setTradeitem(l1);
		this.tradeRepository.save(trade);
		//this.rabbitMqService.send("A new Trade request has been created");
		return ResponseEntity.ok("OK");
	}
}
