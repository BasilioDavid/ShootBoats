import java.io.*;
import java.net.Socket;

public class CerradoForzoso {
	public static void main(String[] args) {
		String serverName = "localhost";
		int port = 1231;
		try{
			System.out.println("Connecting to " + serverName + " on port " + port);
			Socket server = new Socket(serverName, port);

			System.out.println("Just connected to " + server.getRemoteSocketAddress());
			OutputStream outToServer = server.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);

			out.writeUTF("ForceClose");

			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
