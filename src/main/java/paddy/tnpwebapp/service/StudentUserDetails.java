package paddy.tnpwebapp.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import paddy.tnpwebapp.model.Student;

public class StudentUserDetails implements UserDetails {

	private Student student;

	public Student getStudent() {
		return student;
	}

	StudentUserDetails(Student s) {
		this.student = s;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (student.getRollNo() < 100 && student.getDept().getDeptId() == 1473)
			return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		return student.getPassword();
	}
	
	public int getDeptId() {
		return student.getDept().getDeptId();
	}

	@Override
	public String getUsername() {
		return student.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return student.isEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		return student.isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return student.isEnabled();
	}

	@Override
	public boolean isEnabled() {
		return student.isEnabled();
	}

}
