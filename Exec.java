import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Exec {
	public static void rshell() {
		System.out.println("Not implemented");
	}
	public static void exec(String[] cmd) {
		String s;

		try {
			String cmdString = Arrays.toString(cmd);
			cmdString = cmdString.replace(",", "");
			cmdString = cmdString.replace("[", "");
			cmdString = cmdString.replace("]", "");
			
			Process process = Runtime.getRuntime().exec(cmdString);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stdErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			while((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}
			while((s = stdErr.readLine()) != null) {
				System.out.println(s);
			}
		}catch (Exception e) {
			System.out.println("Error:");
			e.printStackTrace();
		}
	}
}
