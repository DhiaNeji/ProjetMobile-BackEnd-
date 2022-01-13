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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entité User de notre application
 * @author Dell
 *
 */
@Entity
@Table(name = "Users")
public class User {

	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	@Column(columnDefinition = "serial")
 	private Long id;
	
	@JsonIgnore
	private String fullName;
	
	private String email;
	
	@Lob
    private byte[] profileImage;
	
	private String hashedPassword;
	
	@OneToMany(mappedBy = "owner",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Item> relatedItems = new HashSet<Item>();
	
	@OneToMany(mappedBy = "sender",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Trade> sentTrades = new HashSet<Trade>();
	
	@OneToMany(mappedBy = "receiver",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Trade> receivedTrades = new HashSet<Trade>();
	
    @OneToMany(mappedBy = "primaryKey.user",
            cascade = CascadeType.ALL)
	private Set<TradeItem> Tradeitem=new HashSet<TradeItem>();
	public User()
	{
		
	}

	public User(Long id, String fullName, String email, byte[] profileImage, String hashedPassword) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.profileImage = profileImage;
		this.hashedPassword = hashedPassword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	
}
