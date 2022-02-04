package paddy.tnpwebapp.controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import paddy.tnpwebapp.model.Job;
import paddy.tnpwebapp.model.dto.StudentDto;
import paddy.tnpwebapp.service.FileService;
import paddy.tnpwebapp.service.JobService;
import paddy.tnpwebapp.service.StudentUserDetails;

@RestController
public class HomeController {

	@Autowired
	private FileService fileService;

	@Autowired
	private JobService jobService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> homePage(@AuthenticationPrincipal StudentUserDetails userDetails,
			@RequestParam(required=false) String keyword) {
		List<Job> jobList = jobService.getActiveJobsByDept(userDetails.getDeptId(), keyword);
		StudentDto student = new StudentDto(userDetails.getStudent());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jobList", jobList);
		map.put("student", student);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@GetMapping("/download/{fileName}")
	public ResponseEntity<Resource> getFile(@PathVariable String fileName) throws FileNotFoundException {
		Resource resource = fileService.downloadFile(fileName);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
				.header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
						"attachment;fileName=" + resource.getFilename())
				.body(resource);
	}
}
