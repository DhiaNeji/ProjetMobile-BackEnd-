package Mobile.Project.Entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import Mobile.Project.Enums.TradeStatusEnums.TradeStatus;

/**
 * Entité Trade
 * @author Dell
 *
 */
@Entity
@Table(name = "Trade")
public class Trade {

	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	@Column(columnDefinition = "serial")
 	private Long id;
	
	private LocalDateTime issueDate;
	
	private LocalDateTime modifiedDate;
	
	private String modifiedBy;
	
	private TradeStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReceiverId")
	private User receiver;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SenderId")
	private User sender;
	
    @OneToMany(mappedBy = "primaryKey.trade",
            cascade = CascadeType.ALL)
	private Set<TradeItem> Tradeitem=new HashSet<TradeItem>();
	
	public Trade()
	{
		
	}

	public Trade(Long id, LocalDateTime issueDate, LocalDateTime modifiedDate, String modifiedBy, TradeStatus status) {
		super();
		this.id = id;
		this.issueDate = issueDate;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDateTime issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public TradeStatus getStatus() {
		return status;
	}

	public void setStatus(TradeStatus status) {
		this.status = status;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Set<TradeItem> getTradeitem() {
		return Tradeitem;
	}

	public void setTradeitem(Set<TradeItem> tradeitem) {
		Tradeitem = tradeitem;
	}

	
}
