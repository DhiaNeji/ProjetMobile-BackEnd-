package Mobile.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Mobile.Project.DTO.UserDto;
import Mobile.Project.Entity.JwtRequest;
import Mobile.Project.Entity.JwtResponse;
import Mobile.Project.Security.JwtTokenUtil;
import Mobile.Project.Service.UserDetailsServiceImpl;
import Mobile.Project.Service.UserService;

/**
 * Classe controller de User
 * @author Dell
 *
 */
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	/**
	 * Méthode pour s'inscrire sur l'application
	 * @param fullName : nom complet de l'utilisateur
	 * @param email : email de user
	 * @param password : mot de passe de user
	 * @return Réponse de la requete
	 */
	@PostMapping("/signup")
	public ResponseEntity<String> saveUser(@RequestParam("fullName") String fullName,
			@RequestParam("email") String email,@RequestParam("password") String password)
	{
	       try {
	    	   	this.userService.save(fullName, email, password);
	    	   return ResponseEntity.status(HttpStatus.OK).build();
	                                 
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	}
	
	/**
	 * Connecter un tel utilisateur et génerer le JWT
	 * @param authenticationRequest contenant le nom complet et le mot de passe
	 * @return : authenticationResponse : 401 si mot de passe erroné ou JWT en cas de succès
	 * @throws Exception
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	/**
	 * Récuperer un user 
	 * @param id : id de user à récupere
	 * @return Réponse de la requete
	 */
	@GetMapping("{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable long id)
	{
		System.out.println("hh");
		UserDto userDto=this.userService.getUserDtoById(id);
		return ResponseEntity.ok(userDto);
	}
	
	/**
	 * Mettre à jour les informations d'un tel utilisateur 
	 * @param fullName
	 * @param email
	 * @param password
	 * @return Réponse de la requete
	 */
	@PutMapping()
	public ResponseEntity<UserDto> updateUser(@RequestParam("fullName") String fullName,
			@RequestParam("email") String email,@RequestParam("password") String password)
	{
		
		long id=1; 
		UserDto userDto=this.userService.updateUserById(id,fullName,email);
		return ResponseEntity.ok(userDto);
	}
	
	/**
	 * Méthode de connexion qui sera utilisée par Spring Security pour assurer le Flow JWT
	 * @param username : nom complet de user
	 * @param password : mot de passe de user
	 * @throws Exception
	 */
	private void authenticate(String username, String password) throws Exception {
		try {
		Authentication a=	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
