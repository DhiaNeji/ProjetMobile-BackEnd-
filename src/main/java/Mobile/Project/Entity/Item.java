package Mobile.Project.Entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Dell
 * Entité Item
 */
@Entity
@Table(name = "Item")
public class Item {

	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	@Column(columnDefinition = "serial")
 	private Long id;
	
	private String title;
	
	private String description;
	
	private String shortDescription;
	
	@JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private ItemCondition itemCondition;
    
    @JsonIgnore
    @OneToMany(mappedBy = "item",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ItemImage> itemImages = new HashSet<ItemImage>();
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId")
    private User owner;

    @JsonIgnore
    @OneToMany(mappedBy = "primaryKey.item",
            cascade = CascadeType.ALL)
    private Set<TradeItem> Tradeitem=new HashSet<TradeItem>();
    
	public Item()
	{
		
	}

	public Item(String title, String description, String shortDescription) {
		super();
		this.title = title;
		this.description = description;
		this.shortDescription = shortDescription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public ItemCondition getItemCondition() {
		return itemCondition;
	}

	public void setItemCondition(ItemCondition itemCondition) {
		this.itemCondition = itemCondition;
	}

	public Set<ItemImage> getItemImages() {
		return itemImages;
	}

	public void setItemImages(Set<ItemImage> itemImages) {
		this.itemImages = itemImages;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<TradeItem> getTradeitem() {
		return Tradeitem;
	}

	public void setTradeitem(Set<TradeItem> tradeitem) {
		Tradeitem = tradeitem;
	}
	
	
}
