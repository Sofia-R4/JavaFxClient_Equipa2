package upt.lp.equipa2_comp2.mapper;

import org.mapstruct.Mapper;
import upt.lp.equipa2_comp2.dto.UserDTO;
import upt.lp.equipa2_comp2.dto.UserResponseDTO;
import upt.lp.equipa2_comp2.entity.User;

public class UserMapper {

    
    public static User toEntity(UserDTO dto) {
        User u = new User();
        u.setName(dto.getName());
        u.setEmail(dto.getEmail());
        u.setPassword(dto.getPassword());
        
        return u;
    }
    
    public static UserResponseDTO toDTO(User u) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(u.getId());
        dto.setName(u.getName());
        dto.setEmail(u.getEmail());
        
        return dto;
    }
}

