package paddy.tnpwebapp.controller;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paddy.tnpwebapp.model.Job;
import paddy.tnpwebapp.model.dto.JobDto;
import paddy.tnpwebapp.service.JobService;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private JobService jobService;

	@GetMapping("/")
	public ResponseEntity<List<Job>> adminHome() {
		return new ResponseEntity<List<Job>>(jobService.getAllActiveJobs(), HttpStatus.OK);
	}

	@PostMapping("/job/new")
	ResponseEntity<Job> addNewJob(@ModelAttribute JobDto jobDto) throws IOException {
		String contentType=jobDto.getDetailedPdf().getContentType();
		if(!contentType.equalsIgnoreCase("application/pdf")) throw new IOException();
		return new ResponseEntity<Job>(jobService.addNewJob(jobDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/job/{jobId}")
	public ResponseEntity<Job> getJobById(@PathVariable int jobId) throws NoSuchElementException {
		return new ResponseEntity<Job>(jobService.getJobById(jobId), HttpStatus.OK);
	}

	@PutMapping("/job/{jobId}")
	public ResponseEntity<Job> updateJob(@PathVariable int jobId, @ModelAttribute JobDto jobDto) throws IOException {
		return new ResponseEntity<Job>(jobService.updateJob(jobId,jobDto), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/job/{jobId}")
	public ResponseEntity<HttpStatus> deleteJob(@PathVariable int jobId) throws EmptyResultDataAccessException{
		jobService.deleteJob(jobId);
		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
	}
}
