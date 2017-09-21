
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.net.Socket;
import java.net.ServerSocket;

class TCPServer {
    public static void main(String args[]) throws Exception {
        TcpMessage clientInput;
        TcpMessage clientOutput;
        ServerSocket welcomeSocket = new ServerSocket(6666);
        Socket connectionSocket = welcomeSocket.accept();
        System.out.println("Connection established");
        ObjectOutputStream outToClient;
        ObjectInputStream inFromClient;
        inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
        outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());

        while (true) {
            try {
                clientInput = (TcpMessage) inFromClient.readObject();
            } catch (EOFException e) {
                System.out.println("Connection closed");
                connectionSocket = welcomeSocket.accept();
                System.out.println("Connection re-established");
                inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
                outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
                continue;
            }
            
            System.out.println("Received: " + clientInput);
            clientOutput = new TcpMessage(clientInput.getMessage().toUpperCase());

            outToClient.writeObject(clientOutput);
        }
    }
}
