import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class fileIO {
	public static void ls(Path cDir) throws IOException {
		try {
			Files.list(cDir).forEach(path -> { 
				if(path.normalize().toString().indexOf("\\") != -1)
					System.out.println(path.normalize().toString().substring(path.normalize().toString().lastIndexOf("\\")+1, path.normalize().toString().length()));
				else
					System.out.println(path.normalize());
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void cp(File input, String output) {
		System.out.println("not implemented");
		
	}
	public static void mv(File input, String output) {
		System.out.println("not implemented");
		rm(input);
		
	}
	public static void rm(File input) {
		System.out.println("not implemented");
	}
	public static void cat(File input) {
		System.out.println("not implemented");
	}
	public static void echo(File input, String text) {
		System.out.println("not implemented");
	}
	public static Path cd(Path cDir, String dir) {
			if(dir.equals("..")) {
				Path currentDir = Paths.get(cDir.toString(), "..\\");
				//System.out.println(currentDir.toAbsolutePath().normalize());
				return currentDir.normalize();
			} else if(dir.equals(".")) {
				return cDir.normalize();
			}else if(dir.equals("~")) {
				return Paths.get(".");
			}else if(!dir.substring(0).equals("\\") && dir.indexOf("\\") == -1) {
				return Paths.get(cDir.toString() + "\\" + dir);
			}else {
				return Paths.get(dir);
			}
	}
}
