package paddy.tnpwebapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import paddy.tnpwebapp.model.Job;

@Repository
public interface JobRepo extends JpaRepository<Job, Integer> {

	@Query("FROM Job WHERE lastDate>=CURDATE() OR lastDate=null ORDER BY jobId DESC")
	List<Job> getAllActiveJobs();

	@Query("SELECT j FROM Job j JOIN j.eligibleDepts d WHERE "
			+ "(d.deptId= :deptId) AND (j.lastDate=null OR j.lastDate>=CURDATE()) ORDER BY j.jobId DESC")
	List<Job> getActiveJobsForStudent(@Param("deptId") int deptId);

	@Query("SELECT j FROM Job j JOIN j.eligibleDepts d WHERE (d.deptId= :deptId) AND "
			+ "(j.lastDate=null OR j.lastDate>=CURDATE()) AND (CONCAT(j.companyName,j.roleName) LIKE %:keyword%)")
	List<Job> searchActiveJobsForStudent(@Param("deptId") int deptId, @Param("keyword") String keyword);

}