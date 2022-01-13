package Mobile.Project.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe UserDto qui est le DTO de User
 * @author Dell
 *
 */
public class UserDto {

	private Long id;
	
	private String fullName;
	
	private String email;

	private byte[] profileImage;
	
	public UserDto() {
		super();
	}

	public UserDto(Long id, String fullName, String email, byte[] profileImage) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.profileImage = profileImage;
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
	
	
}
