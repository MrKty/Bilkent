import java.util.List;

public interface MemoryReprBehavior {
	// dirLevel is used for recursively calling the function without the need of helper functions
	String displayMemory(List<FileSystemComponent> content, int dirLevel);
}
