package upt.lp.equipa2_comp2.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public class ProgramDTO {
	
	private Long id;
	
	@NotBlank(message= "Tem de preencher o nome")
    private String nomeP;
	@NotBlank(message= "Tem de preencher a descrição")
    private String description;
	@NotBlank(message= "Tem de preencher a localização")
    private String location;
	@NotNull(message= "Tem preencher o contacto")
    private int contact;
	@NotNull(message= "Tem preencher o número de vagas")
    private int vagas;
    @NotBlank(message= "Tem de preencher o partner")
    private String partner;
    @NotBlank(message= "Tem de preencher o tipo")
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
	 * @return the nomeP
	 */
	public String getNomeP() {
		return nomeP;
	}
	/**
	 * @param nomeP the nomeP to set
	 */
	public void setNomeP(String nomeP) {
		this.nomeP = nomeP;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the contact
	 */
	public int getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(int contact) {
		this.contact = contact;
	}
	/**
	 * @return the vagas
	 */
	public int getVagas() {
		return vagas;
	}
	
	/**
	 * @param vagas the vagas to set
	 */
	public void setVagas(int vagas) {
		this.vagas = vagas;
	}
	
	/**
	 * @return the partner
	 */
	public String getPartner() {
		return partner;
	}
	
	/**
	 * @param partner the partner to set
	 */
	public void setPartner(String partner) {
		this.partner = partner;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param typeName the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
