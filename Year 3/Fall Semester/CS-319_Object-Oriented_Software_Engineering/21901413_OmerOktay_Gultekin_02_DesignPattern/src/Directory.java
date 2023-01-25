import java.util.ArrayList;
import java.util.List;

public class Directory extends FileSystemComponent {
	private String directoryName;
	final List<FileSystemComponent> dirContent;
	private MemoryReprBehavior memoryReprBehavior;

	public Directory(String directoryName) {
		this.directoryName = directoryName;
		this.dirContent = new ArrayList<>();
	}

	public void addComponent(FileSystemComponent comp) {
		this.dirContent.add(comp);
	}

	@Override
	public String getInfo() {
		StringBuilder dirInfo = new StringBuilder();
		dirInfo.append("- ").append(directoryName).append("\n");
		for (int i = 0; i < this.dirContent.size(); i++) {
			dirInfo.append(this.dirContent.get(i).getInfo()).append("\n");
		}
		dirInfo.append("- DIRECTORY END | ").append(directoryName);
		return dirInfo.toString();
	}

	public void adjustMemoryRepresentation(MemoryReprBehavior memoryReprBehavior) {
		this.memoryReprBehavior = memoryReprBehavior;
	}

	public String applyMemoryRepresentation() {
		return this.memoryReprBehavior.displayMemory(this.dirContent, 0);
	}
}
