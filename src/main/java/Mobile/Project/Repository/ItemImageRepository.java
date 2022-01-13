package Mobile.Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Mobile.Project.Entity.ItemImage;
/**
 * Repository pour l'accès à la table ItemImage de la base de données
 * @author Dell
 *
 */
public interface ItemImageRepository extends JpaRepository<ItemImage, Long>{

}
