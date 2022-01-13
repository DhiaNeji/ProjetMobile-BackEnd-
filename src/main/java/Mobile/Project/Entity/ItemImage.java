package Mobile.Project.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Entité ItemImage qui présente l'image d'un tel item
 * @author Dell
 *
 */
@Entity
@Table(name = "ItemImage")
public class ItemImage {

	 	@Id
	 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 	@Column(columnDefinition = "serial")
	 	private Long id;

	    private String name;

	    private String contentType;

	    private Long size;

	    @Lob
	    private byte[] data;
	
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "item_id")
	    @JsonIgnore
	    private Item item;
	    
	    public ItemImage()
	    {
	    	
	    }

		public ItemImage(Long id, String name, String contentType, Long size, byte[] data) {
			super();
			this.id = id;
			this.name = name;
			this.contentType = contentType;
			this.size = size;
			this.data = data;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getContentType() {
			return contentType;
		}

		public void setContentType(String contentType) {
			this.contentType = contentType;
		}

		public Long getSize() {
			return size;
		}

		public void setSize(Long size) {
			this.size = size;
		}

		public byte[] getData() {
			return data;
		}

		public void setData(byte[] data) {
			this.data = data;
		}

		public Item getItem() {
			return item;
		}

		public void setItem(Item item) {
			this.item = item;
		}
	    
}
