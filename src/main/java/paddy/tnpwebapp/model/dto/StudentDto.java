package paddy.tnpwebapp.model.dto;

import paddy.tnpwebapp.model.Student;

public class StudentDto {
	
	private long rollNo;
	private String name;
	private String deptName;
	
	public long getRollNo() {
		return rollNo;
	}

	public void setRollNo(long rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	StudentDto(){}
	
	public StudentDto(Student s){
		this.rollNo=s.getRollNo();
		this.name=s.getFname()+" "+s.getLname();
		this.deptName=s.getDept().getDeptName();
	}

}
