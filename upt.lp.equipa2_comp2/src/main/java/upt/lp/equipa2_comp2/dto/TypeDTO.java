package upt.lp.equipa2_comp2.dto;
import jakarta.validation.constraints.NotBlank;

public class TypeDTO {
	
	private Long id;
	
	@NotBlank(message= "tem de preencher o nome do tipo")
	private String type;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
