package Mobile.Project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Mobile.Project.Entity.Item;
/**
 * Repository pour l'accès à la table Item de la base de données
 * @author Dell
 *
 */
public interface ItemRepository extends JpaRepository<Item, Long>{

	@Query(value="select * from item where owner_id=?1",nativeQuery=true)
	public List<Item> findItemsByUserId(long id);
	
	@Query(value="select * from item where id not in (select item_id from trade_item)",nativeQuery=true)
	public List<Item> getAvailableItems();
}
