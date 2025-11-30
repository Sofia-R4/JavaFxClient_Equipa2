/**
 * 
 */
package upt.lp.equipa2_comp2.controller;
import upt.lp.equipa2_comp2.entity.Student;
import upt.lp.equipa2_comp2.service.StudentService;

import upt.lp.equipa2_comp2.dto.StudentDTO;
import upt.lp.equipa2_comp2.dto.StudentResponseDTO;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
/**
 * 
 */
@RestController
@RequestMapping ("/voluntariado/students")
public class StudentController {
	
	private final StudentService studentService;

	/**
	 * @param studentService
	 */
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@GetMapping
	public List<StudentResponseDTO>getAll(){
		return studentService.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public StudentResponseDTO getById(@PathVariable Long id) {
		return studentService.getStudent(id);
	}
	
	@PostMapping("/student")
	public StudentResponseDTO create (@Valid @RequestBody StudentDTO dto) {
		return studentService.createStudent(dto);
	}
}
