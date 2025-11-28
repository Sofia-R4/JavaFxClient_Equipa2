/**
 * 
 */
package upt.lp.equipa2_comp2.upt.lp.equipa2_comp2.entity;

import java.util.ArrayList;

/**
 * 
 */

import java.util.List;

import jakarta.persistence.*;
	@Entity
	@Table(name="Partners")
	public class PartnerEntity {
		
		@Id
		@Column(name="id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column (nullable = false)
		private String partner;
		
		@OneToMany(mappedBy= "partner", cascade=CascadeType.ALL, orphanRemoval=true)
		private List<ProgramEntity>programas= new ArrayList<ProgramEntity>();

		/**
		 * @param namePartner
		 */
		public PartnerEntity(String partner) {
			this.partner = partner;
		}
		
		public PartnerEntity() {
			
		}

		/**
		 * @return the id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * @param namePartner the namePartner to set
		 */
		public void setPartner(String partner) {
			this.partner = partner;
		}

		public List<ProgramEntity> getProgramas(){
			return programas;
		}
		
		public void adicionarPrograma(ProgramEntity novoPrograma) {
			programas.add(novoPrograma);
			novoPrograma.setPartner(this);
		}

		/**
		 * @return the namePartner
		 */
		public String getPartner() {
			return partner;
		}

		@Override
		public String toString() {
			return "Partner [id=" + id + ", Partner=" + partner + "]";
		}
		
		
	}