import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Main {
	public static void main(String args[]) throws IOException {
		Path cDir = Paths.get(".");
		System.out.print("Welcome to java shell. Written by Screencap. \n Type 'help' for more. \n");
		while(true) {
			System.out.print(cDir.toAbsolutePath().normalize().toString() + ">");
			Scanner in = new Scanner(System.in);
			List<String> choice = new ArrayList<String>();
			String userinput = in.nextLine();
			String userin[] = userinput.split(" ");
			choice = Arrays.asList(userin);
			switch(choice.get(0)) {
			case "help":
				System.out.print("Help: \nls - list directory\ncp - copies file\nrm - deletes file\ncat - reads file\necho - writes string to file\nexec - executes a string to powershell\nrshell - starts reverse shell\ncd - changes directory\nexit - exits\nhelp - displayes help\n");
				break;
			case "ls":
				fileIO.ls(cDir);
				break;
			case "cp":
				try {
				File input = new File(choice.get(1));
				fileIO.cp(input, choice.get(2));
				break;
				}catch (Exception arrayIndexOutOfBoundsException) {
					System.out.println("Error: Missing arguments");
					break;
				}
			case "mv":
				try {
				File input = new File(choice.get(1));
				fileIO.mv(input, choice.get(2));
				break;
				}catch (Exception arrayIndexOutOfBoundsException) {
					System.out.println("Error: Missing arguments");
					break;
				}
			case "rm":
				try {
				File input = new File(choice.get(1));
				fileIO.rm(input);
				break;
				}catch (Exception arrayIndexOutOfBoundsException) {
					System.out.println("Error: Missing arguments");
					break;
				}
				
			case "cat":
				try {
				File input = new File(choice.get(1));
				fileIO.cat(input);
				break;
				}catch (Exception arrayIndexOutOfBoundsException) {
					System.out.println("Error: Missing arguments");
					break;
				}
			case "echo":
				try {
				File input = new File(choice.get(1));
				fileIO.echo(input, choice.get(2));
				break;
				}catch (Exception arrayIndexOutOfBoundsException) {
					System.out.println("Error: Missing arguments");
					break;
				}
			case "exec":
				try {
				Exec.exec(choice.get(1));
				break;
				}catch (Exception arrayIndexOutOfBoundsException) {
					System.out.println("Error: Missing arguments");
					break;
				}
			case "rshell":
				Exec.rshell();
				break;
			case "owo":
				System.out.println("OWO");
				break;
			case "uwu":
				System.out.println("uwu");
				break;
			case "cd":
				try {
				cDir = fileIO.cd(cDir.normalize(), choice.get(1));
				break;
				}catch (Exception arrayIndexOutOfBoundsException) {
					System.out.println("Error: Missing arguments");
					break;
				}finally {
					break;
				}
			case "exit":
				in.close();
				System.exit(0);
				break;
			default:
				System.out.println("Unknown command, type 'help' for commands");
			}
		}
	}
}
