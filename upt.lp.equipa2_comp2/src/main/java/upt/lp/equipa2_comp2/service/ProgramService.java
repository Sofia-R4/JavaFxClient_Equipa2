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
	
	private final ProgramRepository programRepository;  //não pode ser alterada depois de iniciada
	
	private final PartnerRepository partnerRepository;
	
	private final TypeRepository typeRepository;
	
	/**
	 * @param programRepository
	 * @param partnerRepository
	 * @param typeRepository
	 */
	public ProgramService(ProgramRepository programRepository, PartnerRepository partnerRepository,
			TypeRepository typeRepository) { //com o construtor, o service pode usar diretamente a bd
		super();
		this.programRepository = programRepository;
		this.partnerRepository = partnerRepository;
		this.typeRepository = typeRepository;
	}

	public List<Program> getAllPrograms(){  //devolve todos os programas
		return programRepository.findAll(); //o repositório JPA comunica com a tabela e devolve a lista de tudo
	}
	
	public Program getProgramByName(String nameP) { //procura um programa pelo nome
		return programRepository.findByNomeP(nameP).orElseThrow(() -> new RuntimeException("Program not found"));
	}  //chama o repositório que fala com a bd e devolve o que encontrar (que pode ou não existir)
	
	public Program createProgram(ProgramDTO progDTO) {
		
		//partner - se não existir cria
		Partner partner = partnerRepository.findByPartner(progDTO.getPartner()).orElseGet(() -> { //verifica se já existe
			Partner newPartner = new Partner();  //se não cria
			newPartner.setPartner(progDTO.getPartner()); //pega o nome do DTO, cria um novo objeto Partner
			return partnerRepository.save(newPartner);  //salva na bd
		});
			
		// type - se não existir cria
		Type type = typeRepository.findByType(progDTO.getType()).orElseGet(() -> { //verifica se já existe
			Type newType = new Type();  //se não cria
			newType.setType(progDTO.getType()); //pega o nome do DTO, cria um novo objeto Type
			return typeRepository.save(newType); //salva na bd
		});
		
		
		//associar
		Program prog = ProgramMapper.toEntity(progDTO);
		//associa o programa ao partner
		prog.setPartner(partner);
		//associa o programa ao type
		prog.setType(type);
		
		return programRepository.save(prog); //salva na bd
	}
	
	public Program updateProgramPelaLocalizacao(String nomeP, Program prog) {
		Program existing = programRepository.findByNomeP(nomeP).orElseThrow(() -> new RuntimeException("Program not found"));
		//procura na bd se existe, se não retorna nao encontrado
		if (prog.getLocation() != null && !prog.getLocation().isBlank()) { //se localizaçao nao for null nem estiver em branco
		    existing.setLocation(prog.getLocation()); //atualiza
		}
	    
		return programRepository.save(existing); //retorna o programa atualizado
	}
	
	public void deleteProgram(Long id) { //nao retorna nada entao é void
		programRepository.deleteById(id); //apaga na bd
	}		
}
