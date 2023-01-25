public class File extends FileSystemComponent {
	private String fileName;
	private String extension;
	int fileSize;

	public File(String fileName, String extension, int fileSize) {
		this.fileName = fileName;
		this.extension = extension;
		this.fileSize = fileSize;
	}

	@Override
	public String getInfo() {
		return fileName + "." + extension + " | File Size: " + fileSize + " kb";
	}
}
