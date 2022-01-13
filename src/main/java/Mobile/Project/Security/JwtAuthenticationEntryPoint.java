package Mobile.Project.Security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
/**
 * Filter à ajouter aux autres filtres
 * @author Dell
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint,Serializable{

	private static final long serialVersionUID = -7858869558953243875L;

	/**
	 * En cas d'erreur pendant l'auth, retourner l'erreur
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		if( authException instanceof BadCredentialsException)
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unauthorized");
		else
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");	
	}
}
