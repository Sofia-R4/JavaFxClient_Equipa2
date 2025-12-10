/**
 * 
 */
package upt.lp.equipa2_comp2.service;

import upt.lp.equipa2_comp2.dto.PartnerDTO;
import upt.lp.equipa2_comp2.entity.Partner;
import upt.lp.equipa2_comp2.mapper.PartnerMapper;
import upt.lp.equipa2_comp2.repository.PartnerRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
/**
 * 
 */
@Service
public class PartnerService {
	
	private final PartnerRepository partnerRepository;

	/**
	 * @param partnerRepository
	 */
	public PartnerService(PartnerRepository partnerRepository) {
		super();
		this.partnerRepository = partnerRepository;
	}
	
	public List<Partner> getAllPartners(){
		return partnerRepository.findAll();
	}
	
	public Partner getPartner(Long id) {
		return partnerRepository.findById(id).orElseThrow(() -> new RuntimeException("Parceiro não encontrado"));
	}
	
	public Partner createPartner(PartnerDTO pDTO) {
		Partner p = PartnerMapper.toEntity(pDTO);
		return partnerRepository.save(p);
	}
	
	public Partner updatePartner(Long id, PartnerDTO pDTO) {
		Partner existing = partnerRepository.findById(id).orElseThrow(() -> new RuntimeException("Parceiro não encontrado"));
		existing.setPartner(pDTO.getPartner());
		return partnerRepository.save(existing);
	}
	
	public void deletePartner(Long id) {
		partnerRepository.deleteById(id);
	}

}
