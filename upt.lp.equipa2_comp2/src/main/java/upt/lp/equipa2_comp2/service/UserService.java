/**
 * 
 */
package upt.lp.equipa2_comp2.service;

import upt.lp.equipa2_comp2.entity.User;
import upt.lp.equipa2_comp2.mapper.UserMapper;
import upt.lp.equipa2_comp2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import upt.lp.equipa2_comp2.dto.UserDTO;
import upt.lp.equipa2_comp2.dto.UserResponseDTO;
import java.util.List;

import org.springframework.stereotype.Service;
/**
 * 
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO) 
                .toList();
    }

    public UserResponseDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User não encontrado"));
        return UserMapper.toDTO(user);
    }

    public UserResponseDTO create(UserDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já existe");
        }

        User user = UserMapper.toEntity(dto);
        user = userRepository.save(user);

        return UserMapper.toDTO(user);
    }
}