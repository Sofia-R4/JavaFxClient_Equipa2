/**
 * 
 */
package upt.lp.equipa2_comp2.controller;
import upt.lp.equipa2_comp2.entity.Program;
import upt.lp.equipa2_comp2.service.ProgramService;
import upt.lp.equipa2_comp2.dto.ProgramDTO;
import java.util.List;
import upt.lp.equipa2_comp2.mapper.ProgramMapper;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
/**
 * 
 */
@RestController
@RequestMapping ("/voluntariado/programs")
public class ProgramController {
	
	private final ProgramService programService;

	/**
	 * @param programService
	 */
	public ProgramController(ProgramService programService) {
		
		this.programService = programService;
	}
	@GetMapping
	public List<ProgramDTO> getAllPrograms() {
		return programService.getAllPrograms()
				.stream()
				.map(ProgramMapper::toDTO)
				.toList();
	}
	
	 @GetMapping("/by-name/{nomeP}")
	 public ProgramDTO getByName(@PathVariable String nomeP) {
		 return ProgramMapper.toDTO(programService.getProgramByName(nomeP));
	 }
	 
	 @PostMapping
	 public ProgramDTO createProgram (@Valid @RequestBody ProgramDTO progDTO) {
		 Program prog = ProgramMapper.toEntity(progDTO);

		 return ProgramMapper.toDTO(programService.createProgram(progDTO));
	 }
	 

	 @PutMapping("/by-name/{nomeP}")
	 public ProgramDTO updateProgramLocation(@PathVariable String nomeP,@Valid @RequestBody ProgramDTO progDTO) {

		 	Program update = programService.updateProgramPelaLocalizacao(nomeP, ProgramMapper.toEntity(progDTO));
			 return ProgramMapper.toDTO(programService.updateProgramPelaLocalizacao(nomeP, ProgramMapper.toEntity(progDTO)));
		 }
	 
	 @DeleteMapping("{id}")
	 public void delete (@PathVariable Long id) {
		 programService.deleteProgram(id);

	 } 	 
}

