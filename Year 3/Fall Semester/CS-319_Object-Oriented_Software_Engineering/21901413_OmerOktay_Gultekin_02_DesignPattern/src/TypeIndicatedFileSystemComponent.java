public class TypeIndicatedFileSystemComponent extends FileSystemComponentDecorator {
	public TypeIndicatedFileSystemComponent(FileSystemComponent fileSystemComponent) {
		super(fileSystemComponent);
	}

	@Override
	public String getInfo() {
		String info = addTypeIndication(super.fileSystemComponent.getInfo());
		return info;
	}

	private String addTypeIndication(String info) {
		StringBuilder typeIndicatedInfo = new StringBuilder();

		for (String sentence : info.split("\n")) {
			boolean EOD = sentence.contains("DIRECTORY END");
			boolean isFile = sentence.contains("File Size:");
			if (EOD) {
				typeIndicatedInfo.append("(!) ");
			} else if (isFile) {
				typeIndicatedInfo.append("(f) ");
			} else {
				typeIndicatedInfo.append("(d) ");
			}
			typeIndicatedInfo.append(sentence).append("\n");
		}

		// The following is needed to delete newline after the newline since it should be the client
		// decision to add new line after printed info
		return typeIndicatedInfo.deleteCharAt(typeIndicatedInfo.length() - 1).toString();
	}
}
