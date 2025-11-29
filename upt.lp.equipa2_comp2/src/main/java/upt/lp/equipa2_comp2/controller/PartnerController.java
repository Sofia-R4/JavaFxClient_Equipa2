/**
 * 
 */
package upt.lp.equipa2_comp2.controller;
import upt.lp.equipa2_comp2.entity.Partner;
import upt.lp.equipa2_comp2.service.PartnerService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

/**
 * 
 */
@RestController
@RequestMapping ("/api/Partners")
public class PartnerController {
	
	private final PartnerService partnerService;

	/**
	 * @param partnerService
	 */
	public PartnerController(PartnerService partnerService) {
		super();
		this.partnerService = partnerService;
	}
	
	@GetMapping
	 public List<Partner> getAll() {
		return partnerService.getAllPartners();
	 }
	
	@GetMapping("/{id}")
	 public Partner getById(@PathVariable Long id) {
		return partnerService.getPartner(id);
	 }
	
	@PostMapping
	 public Partner create(@RequestBody Partner p) {
		return partnerService.createPartner(p);
	 }
	
	@PutMapping("/{id}")
	 public Partner update(@PathVariable Long id, @RequestBody Partner p) {
		return partnerService.updatePartner(id, p);
	 }
	
	@DeleteMapping("/{id}")
	 public void delete(@PathVariable Long id) {
		partnerService.deletePartner(id);
	 }
}
