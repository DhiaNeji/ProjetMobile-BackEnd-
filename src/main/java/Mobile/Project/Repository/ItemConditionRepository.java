package Mobile.Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Mobile.Project.Entity.ItemCondition;
/**
 * Repository pour l'accès à la table ItemCondition
 * @author Dell
 *
 */
public interface ItemConditionRepository extends JpaRepository<ItemCondition,Long>{

}
