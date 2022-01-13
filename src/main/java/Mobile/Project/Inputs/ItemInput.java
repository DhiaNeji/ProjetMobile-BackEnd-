package Mobile.Project.Inputs;

import java.util.List;

import Mobile.Project.Entity.ItemImage;

public class ItemInput {

	private String title;
	
	private String description;
	
	private String shortDescription;
	
	private String itemConditionCode;
	
	public ItemInput()
	{
		
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

	public String getItemConditionCode() {
		return itemConditionCode;
	}

	public void setItemConditionCode(String itemConditionCode) {
		this.itemConditionCode = itemConditionCode;
	}

	
}
