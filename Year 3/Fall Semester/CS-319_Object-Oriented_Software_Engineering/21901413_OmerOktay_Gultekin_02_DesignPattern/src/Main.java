public class Main {

	public static void main(String[] args) {
		// Part 1
		// ------------- Example 1 -------------
		System.out.println("------------- Example 1 -------------");
		File file = new File("video", "mp4", 200);
		System.out.println(file.getInfo());
		System.out.println("---------- End of Example 1 ---------");
		// ---------- End of Example 1 ---------
		System.out.println();

		// ------------- Example 2 -------------
		System.out.println("------------- Example 2 -------------");
		Directory emptyDir = new Directory("MyDocuments");
		System.out.println(emptyDir.getInfo());
		System.out.println("---------- End of Example 2 ---------");
		// ---------- End of Example 2 ---------
		System.out.println();

		// ------------- Example 3 -------------
		System.out.println("------------- Example 3 -------------");
		Directory oneFileDir = new Directory("FavoriteMusic");
		oneFileDir.addComponent(new File("MetallicaTheUnforgiven", "mp3", 100));
		System.out.println(oneFileDir.getInfo());
		System.out.println("---------- End of Example 3 ---------");
		// ---------- End of Example 3 ---------
		System.out.println();

		// ------------- Example 4 -------------
		System.out.println("------------- Example 4 -------------");
		Directory multCompDir = new Directory("BilkentStuff");
		multCompDir.addComponent(new File("InternshipReport", "pdf", 10));
		multCompDir.addComponent(new File("setup", "exe", 5));
		multCompDir.addComponent(new File("ToDoList", "docx", 15));
		multCompDir.addComponent(oneFileDir);
		System.out.println(multCompDir.getInfo());
		System.out.println("---------- End of Example 4 ---------");
		// ---------- End of Example 4 ---------
		System.out.println();

		// ------------- Example 5 -------------
		System.out.println("------------- Example 5 -------------");
		Directory multDirFile = new Directory("MyPC");
		multDirFile.addComponent(emptyDir);
		multDirFile.addComponent(multCompDir);
		System.out.println(multDirFile.getInfo());
		System.out.println("---------- End of Example 5 ---------");
		// ---------- End of Example 5 ---------
		System.out.println();

		// Part 2
		// ------------- Example 6 -------------
		System.out.println("------------- Example 6 -------------");
		FileSystemComponent decoratedComponent = new IndentationFileSystemComponent(multDirFile);
		System.out.println(decoratedComponent.getInfo());
		System.out.println("---------- End of Example 6 ---------");
		// ---------- End of Example 6 ---------
		System.out.println();

		// ------------- Example 7 -------------
		System.out.println("------------- Example 7 -------------");
		FileSystemComponent decoratedComponent2 = new TypeIndicatedFileSystemComponent(multDirFile);
		System.out.println(decoratedComponent2.getInfo());
		System.out.println("---------- End of Example 7 ---------");
		// ---------- End of Example 7 ---------
		System.out.println();

		// ------------- Example 8 -------------
		System.out.println("------------- Example 8 -------------");
		FileSystemComponent decoratedComponent3 = new TypeIndicatedFileSystemComponent(
				new IndentationFileSystemComponent(multDirFile));
		System.out.println(decoratedComponent3.getInfo());
		System.out.println("---------- End of Example 8 ---------");
		// ---------- End of Example 8 ---------
		System.out.println();

		// Part 3
		// ------------- Example 9 -------------
		System.out.println("------------- Example 9 -------------");
		multDirFile.adjustMemoryRepresentation(SizeBasedModeling.getInstance());
		System.out.println(multDirFile.applyMemoryRepresentation());
		System.out.println("---------- End of Example 9 ---------");
		// ---------- End of Example 9 ---------
		System.out.println();

		// ------------- Example 10 ------------
		System.out.println("------------- Example 10 ------------");
		multDirFile.adjustMemoryRepresentation(TypeBasedModeling.getInstance());
		System.out.println(multDirFile.applyMemoryRepresentation());
		System.out.println("---------- End of Example 10 --------");
		// ---------- End of Example 10 --------
	}
}
