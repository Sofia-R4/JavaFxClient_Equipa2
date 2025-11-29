/**
 * 
 */
package upt.lp.equipa2_comp2.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upt.lp.equipa2_comp2.entity.Partner;
/**
 * 
 */
public interface PartnerRepository extends JpaRepository<Partner, Long> {

	Optional <Partner> findByPartner(String partner);

}
