import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class ServerClient {
	
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 19002);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write("ncmj/新手入门1 zuopai 2'(1,1,1,2,2,2,3,3,3,4,4,4,5,5);");
			bw.newLine();
			bw.flush();
			String st = null;
			while((st=br.readLine())!=null)
				 System.out.println(st);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}	
