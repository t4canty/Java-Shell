import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class fileIO {
	static String os = System.getProperty("os.name").toLowerCase();
	public static void ls(Path cDir) throws IOException {
		try {
			Files.list(cDir).forEach(path -> { 
				if(path.normalize().toString().indexOf("\\") != -1)
					System.out.println(path.normalize().toString().substring(path.normalize().toString().lastIndexOf("\\")+1, path.normalize().toString().length()));
				else if(path.normalize().toString().indexOf("/") != -1)
					System.out.println(path.normalize().toString().substring(path.normalize().toString().lastIndexOf("/")+1, path.normalize().toString().length()));
				else
					System.out.println(path.normalize());
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void cp(String input, String output) {
		try {
			Files.copy(Paths.get(input), Paths.get(output));
		} catch (Exception e) {
			System.out.println("Error in copy");
			e.printStackTrace();
		}
	}
	public static void mv(String input, String output) {
		cp(input, output);
		rm(input, "");
	}
	public static void rm(String input, String input2) {
		try {
			if(input2.equals("-r")) {
				Files.walk(Paths.get(input))
			     .sorted(Comparator.reverseOrder())
			     .map(Path::toFile)
			     .forEach(File::delete);
			}else {
				Files.deleteIfExists(Paths.get(input));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problem in rm");
		}
	}
	public static void cat(Path cDir, String input) {
		if(os.indexOf("win") >= 0){
			if(input.substring(0, 1) != "\\" && input.indexOf("\\") == -1){
				input = cDir.toString() + "\\" + input;
			}
		}else if(os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") >= 0){
			if(input.substring(0, 1) != "/" && input.indexOf("/") == -1){
				input = cDir.toString() + "/" + input;
			}
		}
		try (BufferedReader br = new BufferedReader(new FileReader(input))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void echo(File input, String text) {
		System.out.println("not implemented");
	}
	public static Path cd(Path cDir, String dir) {
		if(os.indexOf("win") >= 0){
			if(dir.equals("..")) {
				Path currentDir = Paths.get(cDir.toString(), "..\\");
				return currentDir.normalize();
			} else if(dir.equals(".")) {
				return cDir.normalize();
			}else if(dir.equals("~")) {
				return Paths.get(".").toAbsolutePath();
			}else if(!dir.substring(0).equals("\\") && dir.indexOf("\\") == -1) {
				return Paths.get(cDir.toString() + "\\" + dir);
			}else {
				return Paths.get(dir);
			}
		}else {
			if(dir.equals("..")) {
				Path currentDir = Paths.get(cDir.toString(), "../");
				return currentDir.normalize();
			} else if(dir.equals(".")) {
				return cDir.normalize();
			}else if(dir.equals("~")) {
				return Paths.get(".").toAbsolutePath();
			}else if(!dir.substring(0).equals("/") && dir.indexOf("/") == -1) {
				return Paths.get(cDir.toString() + "/" + dir);
			}else {
				return Paths.get(dir);
			}
		}
	}
}
