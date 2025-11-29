/**
 * 
 */
package upt.lp.equipa2_comp2.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * 
 */
@Entity 
@DiscriminatorValue("Student")
public class Student extends User {
	private int num;
	
	
	public Student() {
		
	}
	
	public Student(String name, String email, String password, int num) {
		super(name, email, password);
		this.num = num;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return super.toString() + ", Student [num=" + num + "]";
	}

}