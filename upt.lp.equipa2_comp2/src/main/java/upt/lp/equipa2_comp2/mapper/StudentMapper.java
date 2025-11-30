package upt.lp.equipa2_comp2.mapper;
import upt.lp.equipa2_comp2.dto.StudentDTO;
import upt.lp.equipa2_comp2.dto.StudentResponseDTO;
import upt.lp.equipa2_comp2.entity.Student;

public class StudentMapper {

	
	public static Student toEntity(StudentDTO dto) {
		Student s= new Student();
		s.setEmail(dto.getEmail());
		s.setName(dto.getName());
		s.setNum(dto.getNum());
		s.setPassword(dto.getPassword());
		
		return s;
	}
	
	public static StudentResponseDTO toDTO(Student s) {
		StudentResponseDTO dto= new StudentResponseDTO();
		dto.setName(s.getName());
		dto.setNum(s.getNum());
		dto.setEmail(s.getEmail());
		
		return dto;
	}
}
