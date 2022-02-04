package paddy.tnpwebapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Dept {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int deptId;
	private String deptName;
	
	@JsonIgnore
	@OneToMany(mappedBy="dept", cascade=CascadeType.ALL)
	private Set<Student> students;
	
	@JsonIgnore
	@ManyToMany(mappedBy="eligibleDepts")
	private Set<Job> jobs=new HashSet<>();
	
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	public Set<Job> getJobs() {
		return jobs;
	}
	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}
	public Dept(String deptName) {
		super();
		this.deptName = deptName;
	}
	public Dept() {
		super();
	}
	public Dept(String deptName, Set<Student> students, Set<Job> jobs) {
		super();
		this.deptName = deptName;
		this.students = students;
		this.jobs = jobs;
	}
	
}
