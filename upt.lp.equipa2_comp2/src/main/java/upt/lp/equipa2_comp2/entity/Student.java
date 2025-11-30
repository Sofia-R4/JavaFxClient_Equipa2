/**
 * 
 */
package upt.lp.equipa2_comp2.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * 
 */
@Entity 
@Table(name="Estudantes")
public class Student extends User {
	private int num;
	
	@ManyToMany
    @JoinTable(name = "inscricoes",joinColumns = @JoinColumn(name = "student_id"),inverseJoinColumns = @JoinColumn(name = "program_id"))
	private List<Program> programasInscritos = new ArrayList<>();
	
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
	
	public List<Program> getProgramasInscritos() {
	    return programasInscritos;
	}
	
	public void adicionarProgramasInscritos(Program p) {
		programasInscritos.add(p);
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