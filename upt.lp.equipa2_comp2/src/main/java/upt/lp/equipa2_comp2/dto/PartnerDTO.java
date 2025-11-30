package upt.lp.equipa2_comp2.dto;
import jakarta.validation.constraints.NotBlank;

public class PartnerDTO {
	
	private Long id;
	
	@NotBlank(message= "tem de preencher o nome do partner")
	private String partner;

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
}
