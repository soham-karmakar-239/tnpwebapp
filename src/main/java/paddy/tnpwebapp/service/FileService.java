package paddy.tnpwebapp.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	private final String uploadDir = "detailed pdfs";
	private Path filePath;

	public FileService() {
		this.filePath = Paths.get(uploadDir).toAbsolutePath().normalize();
		try {
			Files.createDirectories(filePath);
		} catch (IOException e) {
			System.out.println("Error in creating directory");
			e.printStackTrace();
		}

	}

	public String uploadFile(MultipartFile file, String jobName) throws IOException{
		String fileName = jobName + StringUtils.cleanPath(file.getOriginalFilename());
		Path currentFilePath;
		currentFilePath = Paths.get(filePath + "//" + fileName);
		Files.copy(file.getInputStream(), currentFilePath, StandardCopyOption.REPLACE_EXISTING);
		return fileName;
	}

	public Resource downloadFile(String fileName) throws FileNotFoundException {
		Path path = Paths.get(uploadDir).toAbsolutePath().resolve(fileName);
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			throw new FileNotFoundException();
		}
		if (resource.exists() && resource.isReadable()) {
			return resource;
		} else {
			throw new FileNotFoundException();
		}
	}

}