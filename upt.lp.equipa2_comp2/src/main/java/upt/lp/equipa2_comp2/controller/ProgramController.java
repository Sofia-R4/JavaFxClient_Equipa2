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
		super();
		this.programService = programService;
	}
	
	public List<ProgramDTO> getAll() {
		return programService.getAllPrograms()
				.stream()
				.map(ProgramMapper::toDTO)
				.toList();
	}
	
	 @GetMapping("/{id}")
	 public ProgramDTO getById(@PathVariable Long id) {
		 return ProgramMapper.toDTO(programService.getProgram(id));
	 }
	 
	 @PostMapping
	 public ProgramDTO createProgram (@RequestBody ProgramDTO progDTO) {
		 Program prog = ProgramMapper.toEntity(progDTO);
		 return ProgramMapper.toDTO(programService.createProgram(progDTO));
	 }
	 
	 @PutMapping("/{id}")
	 public ProgramDTO updateProgramLocation(@PathVariable String nomeP, @RequestBody ProgramDTO progDTO) {
		 	Program update = programService.updateProgramPelaLocalizacao(nomeP, ProgramMapper.toEntity(progDTO));
			 return ProgramMapper.toDTO(update);
		 }
	 
	 @DeleteMapping("{id}")
	 public void delete (@PathVariable Long id) {
		 programService.deleteProgram(id);
	 } 
}
