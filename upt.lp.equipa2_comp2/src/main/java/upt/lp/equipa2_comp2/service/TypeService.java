/**
 * 
 */
package upt.lp.equipa2_comp2.service;
import upt.lp.equipa2_comp2.dto.TypeDTO;
import upt.lp.equipa2_comp2.entity.Type;
import upt.lp.equipa2_comp2.mapper.TypeMapper;
import upt.lp.equipa2_comp2.repository.ProgramRepository;
import upt.lp.equipa2_comp2.repository.TypeRepository;

import java.util.List;

import org.springframework.stereotype.Service;
/**
 * 
 */
@Service
public class TypeService {
	
	private final TypeRepository typeRepository;
	
	/**
	 * @param typeRepository
	 */
	public TypeService(TypeRepository typeRepository) {
		super();
		this.typeRepository = typeRepository;
	}

	public List<Type> getAllTypes(){
		return typeRepository.findAll();
	}
	
	public Type getType(Long id) {
		return typeRepository.findById(id).orElseThrow(()-> new RuntimeException("Tipo não encontrado"));
	}
	
	public Type createType(TypeDTO tDTO) {
		Type t = TypeMapper.toEntity(tDTO);
		return typeRepository.save(t);
	}
	
	public Type updateType(Long id, TypeDTO tDTO) {
		Type existing = typeRepository.findById(id).orElseThrow(() -> new RuntimeException("Tipo não encontrado"));
		existing.setType(tDTO.getType());
		return typeRepository.save(existing);
	}
	
	public void deleteType(Long id) {
		typeRepository.deleteById(id);
	}

}
