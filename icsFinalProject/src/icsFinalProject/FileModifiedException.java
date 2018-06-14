package icsFinalProject;

import java.io.IOException;

//Class Description: the class iherits the Exception class and throws an exception message when
//the input-file hash or the input-file syntax does not match.
public class FileModifiedException extends IOException {
	String fileName;
	String errorType;
	
	//creates a new exception with the given filename and error type
	public FileModifiedException(String fileName, String errorType) {
		this.fileName = fileName;
		this.errorType = errorType;
	}
	
	//gives the filename with error
	public String getFileName() {
		return fileName;
	}
	
	//gives the error type of the file
	public String getErrorType() {
		return errorType;
	}
	
	
}
