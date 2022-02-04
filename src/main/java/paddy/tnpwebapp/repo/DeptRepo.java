package paddy.tnpwebapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import paddy.tnpwebapp.model.Dept;
@Repository
public interface DeptRepo extends JpaRepository<Dept, Integer> {

}
