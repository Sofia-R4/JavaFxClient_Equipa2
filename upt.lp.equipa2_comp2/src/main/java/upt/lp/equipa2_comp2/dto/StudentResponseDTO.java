package upt.lp.equipa2_comp2.dto;

public class StudentResponseDTO {
	private int num;
    private String name;
    private String email;

    
    public int getNum() {
    	return num; 
    	}
    
    public void setNum(int num) { 
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
}
