package paddy.tnpwebapp.advice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException ex){
		return new ResponseEntity<String>("File not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<String> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex){
		return new ResponseEntity<String>("Resource not found! Please try again.", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex){
		return new ResponseEntity<String>("Resource not found! Please try again.", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<String> handleIOException(IOException ex){
		return new ResponseEntity<String>("Error in uploading detailed PDF! Please try again.", HttpStatus.BAD_REQUEST);
	}
	
}
