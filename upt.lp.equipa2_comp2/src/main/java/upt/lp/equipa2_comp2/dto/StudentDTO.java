package upt.lp.equipa2_comp2.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class StudentDTO {
	    @NotBlank(message = "Tem de preencher obrigatoriamente o nome")
	    private String name;

	    @NotBlank(message = "Tem de preencher o email obrigatoriamente")
	    @Email(message = "Email inválido")
	    private String email;

	    @NotBlank(message = "A password é obrigatória")
	    @Size(min = 9, message = "A password deve ter pelo menos 9 caracteres")
	    private String password;

	    @NotNull(message = "O estudante deve ter um numero")
	    private int num;
	    
	    public int getNum() {
	    	return num;
	    }
	    public void setNum() {
	    	this.num = num;
	    }
	    
	    public String getName() {
	    	return name; 
	    	}
	    
	    public void setName(String name) {
	    	this.name = name; 
	    	}
	    

	    public String getEmail() {
	    	return email; 
	    	}
	    
	    public void setEmail(String email) {
	    	this.email = email; 
	    	}
	    

	    public String getPassword() {
	    	return password; 
	    	}
	    
	    public void setPassword(String password) {
	    	this.password = password; 
	    	}
	
}
