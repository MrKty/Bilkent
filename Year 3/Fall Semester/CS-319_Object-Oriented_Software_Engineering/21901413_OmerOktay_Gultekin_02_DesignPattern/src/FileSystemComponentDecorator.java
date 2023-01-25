public abstract class FileSystemComponentDecorator extends FileSystemComponent {
	protected FileSystemComponent fileSystemComponent;

	public FileSystemComponentDecorator(FileSystemComponent fileSystemComponent) {
		this.fileSystemComponent = fileSystemComponent;
	}
}