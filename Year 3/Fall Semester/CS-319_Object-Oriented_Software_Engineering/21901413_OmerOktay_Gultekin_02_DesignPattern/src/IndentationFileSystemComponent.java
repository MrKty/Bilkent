public class IndentationFileSystemComponent extends FileSystemComponentDecorator {

	public IndentationFileSystemComponent(FileSystemComponent fileSystemComponent) {
		super(fileSystemComponent);
	}

	@Override
	public String getInfo() {
		String info = addIndentation(super.fileSystemComponent.getInfo());
		return info;
	}

	private String addIndentation(String info) {
		StringBuilder indentedInfo = new StringBuilder();
		int indentationLevel = 0;
		for (String sentence : info.split("\n")) {
			boolean EOD = sentence.contains("DIRECTORY END");
			boolean SOD = sentence.contains("-") && !EOD;
			if (EOD) {
				indentationLevel--;
			}
			indentedInfo.append("\t".repeat(indentationLevel));
			if (SOD) {
				indentationLevel++;
			}
			indentedInfo.append(sentence).append("\n");
		}
		// The following is needed to delete newline after the newline since it should be the client
		// decision to add new line after printed info
		return indentedInfo.deleteCharAt(indentedInfo.length() - 1).toString();
	}
}
