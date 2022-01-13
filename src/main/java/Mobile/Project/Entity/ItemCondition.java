package Mobile.Project.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import Mobile.Project.Enums.ItemConditionEnum.ItemConditionCode;
/**
 * Entité ItemCondition
 * @author Dell
 *
 */
@Entity
@Table(name = "ItemCondition")
public class ItemCondition {

 	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	@Column(columnDefinition = "serial")
 	private Long id;
 	
 	private ItemConditionCode itemConditionCode;
 	
 	private String description;
 	
 	public ItemCondition()
 	{
 		
 	}

	public ItemCondition(Long id, ItemConditionCode itemConditionCode, String description) {
		super();
		this.id = id;
		this.itemConditionCode = itemConditionCode;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemConditionCode getItemConditionCode() {
		return itemConditionCode;
	}

	public void setItemConditionCode(ItemConditionCode itemConditionCode) {
		this.itemConditionCode = itemConditionCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
 	


}