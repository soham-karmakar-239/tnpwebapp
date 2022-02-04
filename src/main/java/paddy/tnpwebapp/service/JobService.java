package paddy.tnpwebapp.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import paddy.tnpwebapp.model.Dept;
import paddy.tnpwebapp.model.Job;
import paddy.tnpwebapp.model.dto.JobDto;
import paddy.tnpwebapp.repo.DeptRepo;
import paddy.tnpwebapp.repo.JobRepo;

@Service
public class JobService {

	@Autowired
	private JobRepo jobRepo;

	@Autowired
	private DeptRepo deptRepo;

	@Autowired
	private FileService fileService;

	public Job addNewJob(JobDto jobDto) throws IOException {
		if(jobDto.getDetailedPdf()==null) throw new IOException();
		Job job = new Job();
		job.setDatePosted(new Date());
		createJobFromDto(job, jobDto);
		return jobRepo.save(job);
	}

	public void deleteJob(int jobId) throws EmptyResultDataAccessException {
		jobRepo.deleteById(jobId);
	}

	public Job getJobById(int jobId) throws NoSuchElementException {
		Job job=jobRepo.findById(jobId).get();
		return job;
	}

	public List<Job> getAllActiveJobs() {
		return jobRepo.getAllActiveJobs();
	}

	public List<Job> getActiveJobsByDept(int deptId, String keyword) {
		if(keyword==null) return jobRepo.getActiveJobsForStudent(deptId);
		return jobRepo.searchActiveJobsForStudent(deptId, keyword);
	}

	public Job updateJob(int jobId, JobDto jobDto) throws IOException {
		Job job = jobRepo.findById(jobId).get();
		if (job == null)
			throw new NoSuchElementException();
		job.setEligibleDepts(new HashSet<Dept>());
		createJobFromDto(job, jobDto);
		return jobRepo.save(job);
	}

	private void createJobFromDto(Job job, JobDto jobDto) throws IOException {
		
		job.setApplyLink(jobDto.getApplyLink());
		job.setCompanyName(jobDto.getCompanyName());
		job.setDriveType(jobDto.getDriveType());
		job.setGender(jobDto.getGender());
		job.setLastDate(jobDto.getLastDate());
		job.setRoleName(jobDto.getRoleName());
		
		Set<Dept> eligibleDeptSet = new HashSet<Dept>();
		for (int i : jobDto.getDeptIdList()) {
			eligibleDeptSet.add(deptRepo.findById(i).get());
		}
		job.getEligibleDepts().addAll(eligibleDeptSet.stream().map(d -> {
			Dept dd = deptRepo.findById(d.getDeptId()).get();
			dd.getJobs().add(job);
			return dd;
		}).collect(Collectors.toList()));
		
		if(jobDto.getDetailedPdf()!=null) {
			String jobName = jobDto.getCompanyName() + "." + jobDto.getRoleName() + "." + jobDto.getLastDate().toString()
					+ ".";
			String fileName = fileService.uploadFile(jobDto.getDetailedPdf(), jobName);
			String filePath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileName)
					.toUriString();
			job.setFilePath(filePath);
		}
	}


}
