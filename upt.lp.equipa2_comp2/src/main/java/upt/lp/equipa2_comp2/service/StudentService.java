/**
 * 
 */
package upt.lp.equipa2_comp2.service;
import upt.lp.equipa2_comp2.entity.Student;
import upt.lp.equipa2_comp2.mapper.StudentMapper;
import upt.lp.equipa2_comp2.repository.ProgramRepository;
import upt.lp.equipa2_comp2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import upt.lp.equipa2_comp2.dto.StudentDTO;
import upt.lp.equipa2_comp2.dto.StudentResponseDTO;
import java.util.List;

import org.springframework.stereotype.Service;
/**
 * 
 */
@Service
public class StudentService {
	
	private final StudentRepository studentRepository;

	/**
	 * @param studentRepository
	 */
	//Constructor injection
	public StudentService(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}
	
	public List<StudentResponseDTO> getAllStudents(){
		return studentRepository.findAll()
				.stream()
				.map(StudentMapper::toDTO)
				.toList();
	}
	
	public StudentResponseDTO getStudent(Long id) {
		Student s= studentRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Estudante nao encontrado"));
		
		return StudentMapper.toDTO(s);
	}
	
	public StudentResponseDTO createStudent(StudentDTO dto) {
		if(studentRepository.existsByEmail(dto.getEmail())) {
			throw new RuntimeException("Email ja existe");
		}
		
		Student s = StudentMapper.toEntity(dto);
		s= studentRepository.save(s);
		
		return StudentMapper.toDTO(s);
	}
	
	

}
