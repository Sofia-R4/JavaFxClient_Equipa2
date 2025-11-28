/**
 * 
 */
package upt.lp.equipa2_comp2.upt.lp.equipa2_comp2.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 */
@Entity
@Table(name="Tipos")
public class TypeEntity {
	
	@Id 
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (nullable = false)
	private String type;
	
	@OneToMany(mappedBy= "type", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ProgramEntity>programas= new ArrayList<ProgramEntity>();
	
	/**
	 * @param type
	 */
	public TypeEntity(String type) {
		this.type = type;
	}
	
	public TypeEntity() {
		
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	public void setType(String type){
		this.type=type;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	public List<ProgramEntity> getProgramas(){
		return programas;
	}
	
	public void adicionarPrograma(ProgramEntity novoPrograma) {
		programas.add(novoPrograma);
		novoPrograma.setType(this);
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", type=" + type + "]";
	}
	
	
}