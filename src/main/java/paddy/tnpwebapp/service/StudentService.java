package paddy.tnpwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import paddy.tnpwebapp.model.Student;
import paddy.tnpwebapp.repo.StudentRepo;

@Service
public class StudentService implements UserDetailsService {

	@Autowired
	private StudentRepo studentRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Student student = studentRepo.findByEmail(username);
		if (student == null)
			throw new UsernameNotFoundException("User does not exist");
		if (!student.isEnabled())
			throw new UsernameNotFoundException("Account is disabled");
		return new StudentUserDetails(student);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
