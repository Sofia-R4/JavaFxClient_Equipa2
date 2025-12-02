/**
 * 
 */
package upt.lp.equipa2_comp2.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upt.lp.equipa2_comp2.entity.User;
/**
 * 
 */
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);//verifica se já existe na bd
}
