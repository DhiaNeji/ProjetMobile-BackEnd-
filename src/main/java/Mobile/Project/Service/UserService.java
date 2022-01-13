package Mobile.Project.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Mobile.Project.DTO.UserDto;
import Mobile.Project.Entity.User;
import Mobile.Project.Repository.UserRepository;

/**
 * Classe Service de l'utilisateur
 * @author Dell
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
    @Autowired
    private ModelMapper modelMapper;
    
    /**
     * Ajouter un nouvel utilisateur à la base de données
     * @param fullName : nom complet 
     * @param email : email
     * @param password : mot de passe de l'utilisateur
     * @throws IOException
     */
	public void save(String fullName,String email,String password) throws IOException
	{
		User user=new User();
		user.setEmail(email);
		user.setFullName(fullName);
		user.setHashedPassword(this.passwordEncoder.encode(password));
		user.setProfileImage(null);
		this.userRepository.save(user);
	}
	
	/**
	 * Récupérer un tel utilisateur de la base de données
	 * @param id : id de l'utilisateur à chercher
	 * @return User
	 */
	public User getUserById(long id)
	{
		Optional<User> opt=this.userRepository.findById(id);
		if(opt.isPresent())
		{
			return opt.get();
		}
		else
			return null;
		
	}
	
	/**
	 * Récupérer un tel utilisateur de la base de données
	 * @param id : id de l'utilisateur à chercher
	 * @return UserDto
	 */
	public UserDto getUserDtoById(long id)
	{
		Optional<User> opt=this.userRepository.findById(id);
		if(opt.isPresent())
		{
			User user=opt.get();
			byte[] profileImage=user.getProfileImage();
			user.setProfileImage(decompressImage(profileImage));
			return convertToDto(user);
		}
		else
			return null;
	}
	
	/**
	 * Metter à jour les information d'un tel utilisateur
	 * @param id : id de user
	 * @param fullName : nom de user
	 * @param email : email de user
	 * @return : new UserDto
	 */
	public UserDto updateUserById(long id, String fullName, String email)
	{
		Optional<User> opt=this.userRepository.findById(id);
		if(opt.isPresent())
		{
			User user=opt.get();
			user.setEmail(email);
			user.setFullName(fullName);
			this.userRepository.save(user);
			return convertToDto(user);
		}
		else
			return null;
	}
	/**
	 * Convertir un User en DTO
	 * @param user : User à convertir
	 * @return : UserDto
	 */
	@Transactional
	public UserDto convertToDto(User user)
	{
		UserDto userDto=this.modelMapper.map(user,UserDto.class);
		return userDto;
	}
	/**
	 * Décompresser une image
	 * @param data : image sous format des bytes
	 * @return : image décompressée sous format des bytes
	 */
	private byte[] decompressImage(byte[] data)
	{
		Inflater inflater=new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream(data.length);
		byte[] buffer=new byte[1024];
		try {
			while(!inflater.finished())
			{
				int count=inflater.inflate(buffer);
				outputStream.write(buffer,0,count);
			}
			outputStream.close();
		}
		catch (IOException ex) {	 
		}
		catch(DataFormatException ex)
		{
			
		}
		return outputStream.toByteArray();
	}
	

	/**
	 * Compresser une image
	 * @param data : image sous format des bytes
	 * @return : image compressée sous format des bytes
	 */
	private byte[] compressImage(byte[] data)
	{
		Deflater deflater=new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream(data.length);
		byte[] buffer=new byte[1024];
		try {
			while(!deflater.finished())
			{
				int count=deflater.deflate(buffer);
				outputStream.write(buffer,0,count);
			}
			outputStream.close();
		}
		catch (IOException ex) {	 
		}
		return outputStream.toByteArray();
	}
}
