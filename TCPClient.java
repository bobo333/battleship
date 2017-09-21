
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

class TcpMessage implements Serializable {
    private String message;

    public TcpMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

class TCPClient {
    public static void main(String args[]) throws Exception {
        String sentence;
        String modifiedSentence;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 6666);
        ObjectOutputStream objOut = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream objIn = new ObjectInputStream(clientSocket.getInputStream());
        
        boolean keepGoing = true;

        while (keepGoing) {
            sentence = inFromUser.readLine();

            if (sentence.isEmpty()) {
                keepGoing = false;
                continue;
            }

            System.out.println(sentence);

            TcpMessage outgoingMessage = new TcpMessage(sentence);
            objOut.writeObject(outgoingMessage);

            TcpMessage incomingMessage = (TcpMessage) objIn.readObject();
            System.out.println("FROM SERVER: " + incomingMessage.getMessage());
        }

        clientSocket.close();
    }
}
