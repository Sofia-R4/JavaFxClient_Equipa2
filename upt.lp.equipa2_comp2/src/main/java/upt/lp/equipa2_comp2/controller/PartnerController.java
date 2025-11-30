/**
 * 
 */
package upt.lp.equipa2_comp2.controller;
import upt.lp.equipa2_comp2.dto.PartnerDTO;
import upt.lp.equipa2_comp2.entity.Partner;
import upt.lp.equipa2_comp2.mapper.PartnerMapper;
import upt.lp.equipa2_comp2.service.PartnerService;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 
 */
@RestController
@RequestMapping ("/voluntariado/partners")
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
	 public List<PartnerDTO> getAllPartners() {
		return partnerService.getAllPartners()
				.stream()
				.map(PartnerMapper :: toDTO)
				.toList();
	 }
	
	@GetMapping("/{id}")
	 public PartnerDTO getById(@PathVariable Long id) {
		return PartnerMapper.toDTO(partnerService.getPartner(id));
	 }
	
	@PostMapping
	 public PartnerDTO createPartner(@Valid @RequestBody PartnerDTO pDTO) {
		Partner p = PartnerMapper.toEntity(pDTO);
		return PartnerMapper.toDTO(partnerService.createPartner(pDTO));
	 }
	
	@PutMapping("/{id}")
	 public PartnerDTO update(@PathVariable Long id, @Valid @RequestBody PartnerDTO pDTO) {
		Partner update = partnerService.updatePartner(id, pDTO);
		return PartnerMapper.toDTO(update);
	 }
	
	@DeleteMapping("/{id}")
	 public void delete(@PathVariable Long id) {
		partnerService.deletePartner(id);
	 }
}
