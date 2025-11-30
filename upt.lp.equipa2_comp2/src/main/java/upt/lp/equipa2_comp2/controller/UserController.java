/**
 * 
 */
package upt.lp.equipa2_comp2.controller;
import upt.lp.equipa2_comp2.dto.UserDTO;
import upt.lp.equipa2_comp2.dto.UserResponseDTO;
import upt.lp.equipa2_comp2.service.UserService;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
/**
 * 
 */
@RestController
@RequestMapping ("/voluntariado/users")
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
	public List<UserResponseDTO>getAll(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public UserResponseDTO getById(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	@PostMapping("/admin")
	public UserResponseDTO create (@Valid  @RequestBody UserDTO dto) {
		return userService.create(dto);
	}
	
}
