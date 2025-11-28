/**
 * 
 */
package upt.lp.equipa2_comp2.upt.lp.equipa2_comp2.service;
import upt.lp.equipa2_comp2.upt.lp.equipa2_comp2.entity.Program;
import upt.lp.equipa2_comp2.upt.lp.equipa2_comp2.repository.ProgramRepository;

import java.util.List;

import org.springframework.stereotype.Service;
/**
 * 
 */
@Service
public class ProgramService {
	
	private final ProgramRepository programRepository;

	/**
	 * @param programRepository
	 */
	public ProgramService(ProgramRepository programRepository) {
		super();
		this.programRepository = programRepository;
	}
	
	public List<Program> getAllPrograms(){
		return programRepository.findById(id).orElse(null);
	}
	
	public Program getProgram(Long id) {
		return programRepository.findById(id).orElse(null);
	}
	
	public Program createProgram(Program prog) {
		return programRepository.save(prog);
	}
	
	public Program updateProgram(Long id, Program prog) {
		Program existing = programRepository.findById(id).orElseThrow(() -> new RuntimeException("Partner not found"));
		
		return programRepository.save(existing);
	}
	
	public void deleteProgram(Long id) {
		programRepository.deleteById(id);
	}

}
