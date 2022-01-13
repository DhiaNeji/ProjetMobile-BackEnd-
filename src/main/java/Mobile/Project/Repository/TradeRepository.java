package Mobile.Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Mobile.Project.Entity.Trade;

/**
 * Repository pour l'accès à la table Trade de la base de données
 * @author Dell
 *
 */
public interface TradeRepository extends JpaRepository<Trade, Long>{

}
