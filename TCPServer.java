
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.ServerSocket;

class TCPServer {
    public static void main(String args[]) throws Exception {
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6666);
        Socket connectionSocket = welcomeSocket.accept();
        System.out.println("Connection established");
        BufferedReader inFromClient;
        DataOutputStream outToClient;

        while (true) {
            inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();

            if (clientSentence  == null) {
                System.out.println("Connection closed");
                connectionSocket = welcomeSocket.accept();
                System.out.println("Connection re-established");
                continue;
            }
            
            System.out.println("Received: " + clientSentence);
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
        }
    }
}
