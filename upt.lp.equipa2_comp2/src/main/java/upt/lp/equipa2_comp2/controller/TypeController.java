/**
 * 
 */
package upt.lp.equipa2_comp2.controller;
import upt.lp.equipa2_comp2.dto.TypeDTO;
import upt.lp.equipa2_comp2.entity.Type;
import upt.lp.equipa2_comp2.service.TypeService;
import upt.lp.equipa2_comp2.mapper.TypeMapper;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.*;
/**
 * 
 */
@RestController
@RequestMapping ("/voluntariado/types")
public class TypeController {
	
	private final TypeService typeService;

	/**
	 * @param typeService
	 */
	public TypeController(TypeService typeService) {
		super();
		this.typeService = typeService;
	}
	
	@GetMapping
	public List<TypeDTO>getAllTypes(){
		return typeService.getAllTypes()
				.stream()
				.map(TypeMapper :: toDTO)
				.toList();
	}
	
	@GetMapping("/by-name/{type}")
	public TypeDTO getById(@PathVariable Long id) {
		return TypeMapper.toDTO(typeService.getType(id));
	}
	
	@PostMapping("/type")
	public TypeDTO createType(@Valid @RequestBody TypeDTO tDTO) {
		Type t = TypeMapper.toEntity(tDTO);
		return TypeMapper.toDTO(typeService.createType(tDTO));
	}
	
	@PutMapping("/{id}")
	public TypeDTO update(@PathVariable Long id,@Valid @RequestBody TypeDTO tDTO) {
		Type update = typeService.updateType(id, tDTO);
		return TypeMapper.toDTO(update);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		typeService.deleteType(id);
	}
}
