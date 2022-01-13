package Mobile.Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Mobile.Project.Entity.User;

/**
 * Repository pour l'accès à la table Trade de la base de données
 * @author Dell
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value="select * from users where full_name=?1",nativeQuery=true)
	User findByUsername(String fullName);
}
