package com.cs.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class PathsDemo {
	
	
	public static void main(String[] args) throws Exception {
		
		Path path = Paths.get("d:/data");
		
		Files.walkFileTree(path, new CsFileVistor());
		
		
	}
	
	
	static class CsFileVistor extends SimpleFileVisitor<Path>{
		
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
				throws IOException {
			System.out.println(file.getFileName());
			return FileVisitResult.CONTINUE;
		}
	}

}
