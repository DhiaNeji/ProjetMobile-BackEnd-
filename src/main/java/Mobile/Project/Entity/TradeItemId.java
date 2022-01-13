package Mobile.Project.Entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Classe qui présente la clé composée de l'entité TradeItem 
 * @author Dell
 *
 */
@Embeddable
public class TradeItemId implements Serializable{

	private Trade trade;
	private Item item;
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
    public Trade getTrade() {
        return trade;
    }
	
	@ManyToOne(cascade = CascadeType.ALL)
    public Item getItem() {
        return item;
    }

	@ManyToOne(cascade = CascadeType.ALL)
    public User getUser() {
        return user;
    }
	
	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public void setUser(User user)
	{
		this.user=user;
	}
}
