package upt.lp.equipa2_comp2.mapper;

import upt.lp.equipa2_comp2.entity.Program;
import upt.lp.equipa2_comp2.entity.Partner;
import upt.lp.equipa2_comp2.entity.Type;
import upt.lp.equipa2_comp2.dto.ProgramDTO;

public class ProgramMapper {
   	
	// ENTITY -> DTO
	public static ProgramDTO toDTO(Program prog) {   //
		ProgramDTO dto = new ProgramDTO();
		dto.setId(prog.getId());
		dto.setNomeP(prog.getNomeP());
		dto.setDescription(prog.getDescription());
		dto.setLocation(prog.getLocation());
		dto.setContact(prog.getContact());
		dto.setVagas(prog.getVagas());
		dto.setPartner(prog.getPartner() !=null ? prog.getPartner().getPartner() : null); // pegar o nome
		dto.setType(prog.getType() !=null ? prog.getType().getType() : null);          // pegar o nome
		return dto;
	}
	 
	 // DTO -> ENTITY
	public static Program toEntity(ProgramDTO progDTO) {
		Program prog = new Program();
		prog.setId(progDTO.getId());
		prog.setNomeP(progDTO.getNomeP());
		prog.setDescription(progDTO.getDescription());
		prog.setLocation(progDTO.getLocation());
        prog.setContact(progDTO.getContact());
        prog.setVagas(progDTO.getVagas());

	        // partner e type são criados apenas no service
	        
        return prog;
	}
}


