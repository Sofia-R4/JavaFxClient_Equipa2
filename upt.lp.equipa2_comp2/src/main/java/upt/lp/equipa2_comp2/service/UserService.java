/**
 * 
 */
package upt.lp.equipa2_comp2.service;

import upt.lp.equipa2_comp2.entity.User;
import upt.lp.equipa2_comp2.repository.ProgramRepository;
import upt.lp.equipa2_comp2.repository.UserRepository;

import java.util.List;

import org.springframework.stereotype.Service;
/**
 * 
 */
@Service
public class UserService {
	
	private final UserRepository userRepository;

	/**
	 * @param userRepository
	 */
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User getUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User createUser(User u) {
		return userRepository.save(u);
	}
	
	public User updateUser(Long id, User u) {
		User existing = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Partner not found"));
		
		return userRepository.save(existing);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
