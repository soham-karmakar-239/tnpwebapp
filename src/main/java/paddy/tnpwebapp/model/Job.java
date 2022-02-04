package paddy.tnpwebapp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Job {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int jobId;
	private String companyName;
	private String roleName;
	private Date lastDate;
	private DriveType driveType;
	private String filePath;
	private String applyLink;
	private Gender gender;
	private Date datePosted;
	
	public Date getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}
	@ManyToMany(cascade= {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="job_dept", joinColumns=@JoinColumn(name="jobId"), inverseJoinColumns=@JoinColumn(name="deptId"))
	private Set<Dept> eligibleDepts=new HashSet<>();
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public DriveType getDriveType() {
		return driveType;
	}
	public void setDriveType(DriveType driveType) {
		this.driveType = driveType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getApplyLink() {
		return applyLink;
	}
	public void setApplyLink(String applyLink) {
		this.applyLink = applyLink;
	}
	public Set<Dept> getEligibleDepts() {
		return eligibleDepts;
	}
	public void setEligibleDepts(Set<Dept> eligibleDepts) {
		this.eligibleDepts = eligibleDepts;
	}
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Job(String companyName, String roleName, Date lastDate, DriveType driveType, String filePath,
			String applyLink, Gender gender, Set<Dept> eligibleDepts, Date datePosted) {
		super();
		this.companyName = companyName;
		this.roleName = roleName;
		this.lastDate = lastDate;
		this.driveType = driveType;
		this.filePath = filePath;
		this.applyLink = applyLink;
		this.gender = gender;
		this.eligibleDepts = eligibleDepts;
		this.datePosted=datePosted;
	}
	public Job() {
	}

}
