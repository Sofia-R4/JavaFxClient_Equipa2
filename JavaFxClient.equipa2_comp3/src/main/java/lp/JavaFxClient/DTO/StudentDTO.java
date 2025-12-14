package lp.JavaFxClient.DTO;

public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private int num; 
    private String password;

    public StudentDTO() {}

    public StudentDTO(Long id, String name, String email, int num) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.num = num;
    }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getNum() { return num; }
    public void setNum(int num) { this.num = num; }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}  
}
