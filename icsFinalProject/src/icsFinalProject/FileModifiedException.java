package icsFinalProject;

import java.io.IOException;

public class FileModifiedException extends IOException {
	String fileName;
	String errorType;
	public FileModifiedException(String fileName, String errorType) {
		super();
		this.fileName = fileName;
		this.errorType = errorType;
	}
	public String getFileName() {
		return fileName;
	}
	public String getErrorType() {
		return errorType;
	}
	
	
}
