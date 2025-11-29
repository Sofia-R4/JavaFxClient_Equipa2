package upt.lp.equipa2_comp2.mapper;

import upt.lp.equipa2_comp2.entity.Program;
import upt.lp.equipa2_comp2.entity.Partner;
import upt.lp.equipa2_comp2.entity.Type;
import upt.lp.equipa2_comp2.dto.ProgramDTO;

public class ProgramMapper {
   	
	// ENTITY -> DTO
	 public static ProgramDTO toDTO(Program p) {
	        ProgramDTO dto = new ProgramDTO();
	        dto.setId(p.getId());
	        dto.setNomeP(p.getNomeP());
	        dto.setDescription(p.getDescription());
	        dto.setLocation(p.getLocation());
	        dto.setContact(p.getContact());
	        dto.setVagas(p.getVagas());
	        dto.setPartner(p.getPartner().getPartner()); // pegar o nome
	        dto.setType(p.getType().getType());          // pegar o nome
	        return dto;
	    }
	 
	 // DTO → ENTITY
	    public static Program toEntity(ProgramDTO dto) {
	        Program p = new Program();
	        p.setId(dto.getId());
	        p.setNomeP(dto.getNomeP());
	        p.setDescription(dto.getDescription());
	        p.setLocation(dto.getLocation());
	        p.setContact(dto.getContact());
	        p.setVagas(dto.getVagas());

	        // partner e type são criados apenas no service
	        
	        return p;
	    }
}


