import java.util.List;

public class TypeBasedModeling implements MemoryReprBehavior {
	private static TypeBasedModeling instance;

	private TypeBasedModeling() {
		// The constructor does nothing but it is needed for Singleton implementation.
		// (Client code should not access the constuctor of the Singleton Object)
	}

	// This method ensures that only one instance is created.
	public static TypeBasedModeling getInstance() {
		if (instance == null) {
			instance = new TypeBasedModeling();
		}
		return instance;
	}


	@Override
	public String displayMemory(List<FileSystemComponent> content, int dirLevel) {
		StringBuilder memoryInfo = new StringBuilder();
		if (dirLevel == 0) {
			memoryInfo.append("{ ");
		}
		memoryInfo.append("[D").append(dirLevel).append(" ");
		for (FileSystemComponent component : content) {
			if (component.getClass() == Directory.class) {
				// The recursive case of the method, it will call the method while there is a subfolder in folder.
				memoryInfo.append(this.displayMemory(((Directory) component).dirContent, dirLevel + 1));
			} else {
				memoryInfo.append("(F) ");
			}
		}
		memoryInfo.append("EOD").append(dirLevel).append("] ");
		if (dirLevel == 0) {
			memoryInfo.append("}");
		}
		return memoryInfo.toString();
	}
}
