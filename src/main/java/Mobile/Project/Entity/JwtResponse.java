package Mobile.Project.Entity;

import java.io.Serializable;

/**
 * JwtResponse : C'est le format de la réponse après créer le JWT
 * @author Dell
 *
 */
public class JwtResponse implements Serializable{

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
