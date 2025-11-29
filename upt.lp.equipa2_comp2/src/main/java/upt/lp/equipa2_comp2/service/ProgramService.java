/**
 * 
 */
package upt.lp.equipa2_comp2.service;
import upt.lp.equipa2_comp2.dto.ProgramDTO;
import upt.lp.equipa2_comp2.entity.Partner;
import upt.lp.equipa2_comp2.entity.Type;
import upt.lp.equipa2_comp2.mapper.ProgramMapper;
import upt.lp.equipa2_comp2.entity.Program;
import upt.lp.equipa2_comp2.repository.PartnerRepository;
import upt.lp.equipa2_comp2.repository.ProgramRepository;
import upt.lp.equipa2_comp2.repository.TypeRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
/**
 * 
 */
@Service
public class ProgramService {
	
	private final ProgramRepository programRepository;
	
	private final PartnerRepository partnerRepository;
	
	private final TypeRepository typeRepository;
	
	/**
	 * @param programRepository
	 * @param partnerRepository
	 * @param typeRepository
	 */
	public ProgramService(ProgramRepository programRepository, PartnerRepository partnerRepository,
			TypeRepository typeRepository) {
		super();
		this.programRepository = programRepository;
		this.partnerRepository = partnerRepository;
		this.typeRepository = typeRepository;
	}

	public List<Program> getAllPrograms(){
		return programRepository.findAll();
	}
	
	public Program getProgram(Long id) {
		return programRepository.findById(id).orElseThrow(() -> new RuntimeException("Program not found"));
	}
	
	public Program createProgram(ProgramDTO progDTO) {
		
		//partner - se não existir cria
		Partner partner = partnerRepository.findByPartner(progDTO.getPartner()).orElseGet(() -> {
			Partner newPartner = new Partner();
			newPartner.setPartner(progDTO.getPartner());
			return partnerRepository.save(newPartner);
		});
			
		// type - se não existir cria
		Type type = typeRepository.findByType(progDTO.getType()).orElseGet(() -> {
			Type newType = new Type();
			newType.setType(progDTO.getType());
			return typeRepository.save(newType);
		});
		
		
		//associar
		Program prog = ProgramMapper.toEntity(progDTO);
		//associa o programa ao partner
		prog.setPartner(partner);
		//associa o type ao programa
		prog.setType(type);
		
		return programRepository.save(prog);
	}
	
	public Program updateProgramPelaLocalizacao(String nomeP, Program prog) {
		Program existing = programRepository.findByNomeP(nomeP).orElseThrow(() -> new RuntimeException("Program not found"));
		
		if (prog.getLocation() != null && !prog.getLocation().isBlank()) { //se localizaçao nao for null nem estiver em branco
		    existing.setLocation(prog.getLocation());
		}
		
	    existing.setLocation(prog.getLocation());
	    
		return programRepository.save(existing);
	}
	
	public void deleteProgram(Long id) {
		programRepository.deleteById(id);
	}
	
	public Optional<Program> findByNomeP(String nomeP){ //query. se encontrar mostra o objeto se não retorna vazio
		return programRepository.findByNomeP(nomeP);
	}
		
}
