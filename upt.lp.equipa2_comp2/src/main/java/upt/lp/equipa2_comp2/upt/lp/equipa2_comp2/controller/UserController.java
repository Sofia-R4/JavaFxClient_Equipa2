/**
 * 
 */
package upt.lp.equipa2_comp2.upt.lp.equipa2_comp2.controller;
import upt.lp.equipa2_comp2.upt.lp.equipa2_comp2.entity.User;
import upt.lp.equipa2_comp2.upt.lp.equipa2_comp2.service.UserService;

import java.util.List;

import org.springframework.web.bind.annotation.*;
/**
 * 
 */
@RestController
@RequestMapping ("//user")
public class UserController {
	
	private UserService userService;

	/**
	 * @param userService
	 */
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping
	public List<User>getAll(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public User getById(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	@PostMapping
	public User create (@RequestBody User u) {
		return userService.createUser(u);
	}
	
	@PutMapping("/{id}")
	public User update(@PathVariable Long id, @RequestBody User u) {
		return userService.updateUser(id, u);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		userService.deleteUser(id);
	}

	//criação do repositório no git
	
}
