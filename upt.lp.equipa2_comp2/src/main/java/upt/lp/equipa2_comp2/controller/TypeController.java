/**
 * 
 */
package upt.lp.equipa2_comp2.controller;
import upt.lp.equipa2_comp2.entity.Type;
import upt.lp.equipa2_comp2.service.TypeService;

import java.util.List;

import org.springframework.web.bind.annotation.*;
/**
 * 
 */
@RestController
@RequestMapping ("/api/type")
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
	public List<Type>getAll(){
		return typeService.getAllTypes();
	}
	
	@GetMapping("/{id}")
	public Type getById(@PathVariable Long id) {
		return typeService.getType(id);
	}
	
	@PostMapping
	public Type create (@RequestBody Type t) {
		return typeService.createType(t);
	}
	
	@PutMapping("/{id}")
	public Type update(@PathVariable Long id, @RequestBody Type t) {
		return typeService.updateType(id, t);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		typeService.deleteType(id);
	}
}
