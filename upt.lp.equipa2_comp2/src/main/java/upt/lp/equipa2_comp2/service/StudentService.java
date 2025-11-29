/**
 * 
 */
package upt.lp.equipa2_comp2.service;
import upt.lp.equipa2_comp2.entity.Student;
import upt.lp.equipa2_comp2.repository.StudentRepository;

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
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	public Student getStudent(Long id) {
		return studentRepository.findById(id).orElse(null);
	}
	
	public Student createStudent(Student s) {
		return studentRepository.save(s);
	}
	
	public Student updateStudent(Long id, Student s) {
		Student existing = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Partner not found"));
		existing.setName(s.getName());
	    existing.setEmail(s.getEmail());
	    existing.setPassword(s.getPassword());
	    existing.setNum(s.getNum());
		return studentRepository.save(existing);
	}
	
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}

}
