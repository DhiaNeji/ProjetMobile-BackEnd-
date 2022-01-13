package Mobile.Project.Service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Mobile.Project.Entity.User;
import Mobile.Project.Repository.UserRepository;

/**
 * Classe pour implémenter les détails de l'utilisateur, nécessaire pour implementer le JWT
 * @author Dell
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository UserRepository;
	/**
	 * Indiquer à Spring Security comment récupérer un tel utilisateur à travers username
	 */
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user=this.UserRepository.findByUsername(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getFullName(), user.getHashedPassword(),
				new ArrayList<>());
	}

	
}
