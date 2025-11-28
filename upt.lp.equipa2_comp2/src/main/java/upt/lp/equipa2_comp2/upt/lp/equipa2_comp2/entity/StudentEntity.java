/**
 * 
 */
package upt.lp.equipa2_comp2.upt.lp.equipa2_comp2.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * 
 */
@Entity 
@DiscriminatorValue("Student")
public class StudentEntity extends UserEntity {
	private int num;
	
	
	public StudentEntity() {
		
	}
	
	public StudentEntity(String nome, String email, String password, int num) {
		super(nome, email, password);
		this.num = num;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	@Override
	public String toString() {
		return super.toString() + ", Student [num=" + num + "]";
	}

}