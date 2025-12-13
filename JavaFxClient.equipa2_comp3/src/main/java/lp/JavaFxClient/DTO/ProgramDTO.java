package lp.JavaFxClient.DTO;


public class ProgramDTO {
	private Long id;
	
    private String nomeP;
	
    //private String description;
	
    //private String location;
	
    private int contact;
	
    //private int vagas;
	
    //private String partner;
    
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

