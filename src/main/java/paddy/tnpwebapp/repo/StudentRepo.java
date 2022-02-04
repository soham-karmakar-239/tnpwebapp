package paddy.tnpwebapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import paddy.tnpwebapp.model.Student;
@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{
	
	public Student findByEmail(String email);

}
