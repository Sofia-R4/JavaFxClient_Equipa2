/**
 * 
 */
package upt.lp.equipa2_comp2.upt.lp.equipa2_comp2.service;
import upt.lp.equipa2_comp2.upt.lp.equipa2_comp2.entity.Partner;
import upt.lp.equipa2_comp2.upt.lp.equipa2_comp2.repository.PartnerRepository;

import java.util.List;

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
		return partnerRepository.findById(id).orElse(null);
	}
	
	public Partner getPartner(Long id) {
		return partnerRepository.findById(id).orElse(null);
	}
	
	public Partner createPartner(Partner p) {
		return partnerRepository.save(p);
	}
	
	public Partner updatePartner(Long id, Partner p) {
		Partner existing = partnerRepository.findById(id).orElseThrow(() -> new RuntimeException("Partner not found"));
		
		return partnerRepository.save(existing);
	}
	
	public void deletePartner(Long id) {
		partnerRepository.deleteById(id);
	}

}
