package Mobile.Project.Entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
    
/**
 * Entité Trade Item qui est la classe association entre Item et Trade
 * @author Dell
 *
 */
@Entity
@Table(name = "TradeItem")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.item",
    joinColumns = @JoinColumn(name = "itemId")),
    @AssociationOverride(name = "primaryKey.user",
    joinColumns = @JoinColumn(name = "UserId")),
    @AssociationOverride(name = "primaryKey.trade",
    joinColumns = @JoinColumn(name = "tradeId")) })
public class TradeItem {
	
	private TradeItemId primaryKey = new TradeItemId();

	   @EmbeddedId
	   public TradeItemId getPrimaryKey() {
	       return primaryKey;
	   }
	   									
	   public void setPrimaryKey(TradeItemId primaryKey)
	   {
		   this.primaryKey=primaryKey;
	   }
	   
	   @Transient
	   public Item getItem()
	   {
		   return getPrimaryKey().getItem();
	   }
	   
	   public void setItem(Item item)
	   {
		   getPrimaryKey().setItem(item);
	   }
	   
	   @Transient
	   public Trade getTrade()
	   {
		   return getPrimaryKey().getTrade();
	   }
	   
	   public void setTrade(Trade trade)
	   {
		   getPrimaryKey().setTrade(trade);
	   }
	   
	   @Transient
	   public User getUser()
	   {
		   return getPrimaryKey().getUser();
	   }
	   
	   public void setUser(User user)
	   {
		   getPrimaryKey().setUser(user);
	   }
}
