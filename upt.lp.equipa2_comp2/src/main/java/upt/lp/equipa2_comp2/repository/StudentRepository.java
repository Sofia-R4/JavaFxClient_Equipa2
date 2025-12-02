/**
 * 
 */
package upt.lp.equipa2_comp2.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upt.lp.equipa2_comp2.entity.Student;
/**
 * 
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	boolean existsByEmail(String email); //verifica se já existe na bd
}
