package Mobile.Project.DTO;

import java.util.HashSet;
import java.util.Set;

import Mobile.Project.Entity.ItemImage;
import Mobile.Project.Entity.User;

/**
 * Classe ItemDto qui est le DTO de Item
 * @author Dell
 *
 */
public class ItemDto {

 	private Long id;
	
	private String title;
	
	private String description;
	
	private String shortDescription;

	private UserDto owner;
	
	private Set<ItemImage> itemImages = new HashSet<ItemImage>();
	
	public ItemDto()
	{
		
	}

	
	public ItemDto(Long id, String title, String description, String shortDescription, UserDto owner,
			Set<ItemImage> itemImages) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.shortDescription = shortDescription;
		this.owner = owner;
		this.itemImages = itemImages;
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

	public UserDto getOwner() {
		return owner;
	}

	public void setOwner(UserDto owner) {
		this.owner = owner;
	}


	public Set<ItemImage> getItemImages() {
		return itemImages;
	}


	public void setItemImages(Set<ItemImage> itemImages) {
		this.itemImages = itemImages;
	}
	
}
