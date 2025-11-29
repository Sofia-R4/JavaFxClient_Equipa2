/**
 * 
 */
package upt.lp.equipa2_comp2.controller;
import upt.lp.equipa2_comp2.entity.Program;
import upt.lp.equipa2_comp2.service.ProgramService;

import java.util.List;

import org.springframework.web.bind.annotation.*;
/**
 * 
 */
@RestController
@RequestMapping ("/api/programs")
public class ProgramController {
	
	private final ProgramService programService;

	/**
	 * @param programService
	 */
	public ProgramController(ProgramService programService) {
		super();
		this.programService = programService;
	}
	
	@GetMapping
	 public List<Program> getAll() {
		return programService.getAllPrograms();
	 }
	
	 @GetMapping("/{id}")
	 public Program getById(@PathVariable Long id) {
		 return programService.getProgram(id);
	 }
	 
	 @PostMapping
	 public Program createProgram (@RequestBody Program prog) {
		 return programService.createProgram(prog);
	 }
	 
	 @PutMapping("/by-name/{nomeP}/location")
	 public Program updateProgramLocation(@PathVariable String nomeP, @RequestBody Program prog) {
	 	return programService.updateProgramPelaLocalizacao(nomeP, prog);
	 }
	 
	 @DeleteMapping("{id}")
	 public void delete (@PathVariable Long id) {
		 programService.deleteProgram(id);
	 } 
	 
}
