package paddy.tnpwebapp.model.dto;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import paddy.tnpwebapp.model.DriveType;
import paddy.tnpwebapp.model.Gender;

public class JobDto {
	
	private String companyName;
	private String roleName;
	private Date lastDate;
	private DriveType driveType;
	private MultipartFile detailedPdf;
	private String applyLink;
	private Gender gender;
	private List<Integer> deptIdList;
	
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
	public MultipartFile getDetailedPdf() {
		return detailedPdf;
	}
	public void setDetailedPdf(MultipartFile file) {
		this.detailedPdf = file;
	}
	public String getApplyLink() {
		return applyLink;
	}
	public void setApplyLink(String applyLink) {
		this.applyLink = applyLink;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public List<Integer> getDeptIdList() {
		return deptIdList;
	}
	public void setDeptIdList(List<Integer> deptIdList) {
		this.deptIdList = deptIdList;
	}
	public JobDto(String companyName, String roleName, Date lastDate, DriveType driveType, MultipartFile detailedPdf,
			String applyLink, Gender gender, List<Integer> deptIdList) {
		super();
		this.companyName = companyName;
		this.roleName = roleName;
		this.lastDate = lastDate;
		this.driveType = driveType;
		this.detailedPdf=detailedPdf;
		this.applyLink = applyLink;
		this.gender = gender;
		this.deptIdList = deptIdList;
	}
	public JobDto() {
		super();
	}

}
