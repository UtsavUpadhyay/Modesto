import java.io.*;
import java.net.*; 
import java.util.*;


public class ChatSer {

    private static final String USAGE = "Usage: java ChatServer";

    /** Default port number on which this server to be run. */
    private static final int PORT_NUMBER = 58000;
   
    /** List of print writers associated with current clients,
     * one for each. */
    private List<PrintWriter> clients;

    /** Creates a new server. */
    public ChatSer() {
        clients = new LinkedList<PrintWriter>();
    }

    /** Starts the server. */
    public void start() {
        System.out.println("server started on port "
                           + PORT_NUMBER + "!"); 
        try {
            ServerSocket s = new ServerSocket(PORT_NUMBER,10,InetAddress.getLocalHost());
            System.out.println(s.getInetAddress());
            for (;;) {
                Socket incoming = s.accept(); 
                new ClientHandler(incoming).start(); 
                System.out.println(s.getInetAddress());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("server stopped."); 
    }

    /** Adds a new client identified by the given print writer. */
    private void addClient(PrintWriter out) {
        synchronized(clients) {
            clients.add(out);
        }
    }

    /** Adds the client with given print writer. */
    private void removeClient(PrintWriter out) {
        synchronized(clients) {
            clients.remove(out);
        }
    }

    /** Broadcasts the given text to all clients. */
    private void broadcast(String msg) {
        for (PrintWriter out: clients) {
            out.println(msg);
            out.flush();
        }
    }

    public static void main(String[] args) throws IOException {
    	 
    	if (args.length > 0) {
            System.out.println(USAGE);
            System.exit(-1);
          
        }
        new ChatSer().start();
    }

    /** A thread to serve a client. This class receive messages from a
     * client and broadcasts them to all clients including the message
     * sender. */
    private class ClientHandler extends Thread {

        /** Socket to read client messages. */
        private Socket incoming; 

        /** Creates a hander to serve the client on the given socket. */
        public ClientHandler(Socket incoming) {
            this.incoming = incoming;
        }

        /** Starts receiving and broadcasting messages. */
        public void run() {
            PrintWriter out = null;
            try {
                out = new PrintWriter(
                        new OutputStreamWriter(incoming.getOutputStream()));
                
                // inform the server of this new client
                ChatSer.this.addClient(out);

                
                
                out.flush();

                BufferedReader in 
                    = new BufferedReader(
                        new InputStreamReader(incoming.getInputStream())); 
                for (;;) {
                    String msg = in.readLine(); 
                    if (msg == null) {
                        break; 
                    } else {
                        if (msg.trim().equals("BYE")) 
                            break; 
                        System.out.println("Received: " + msg);
                        // broadcast the receive message
                        ChatSer.this.broadcast(msg);
                    }
                }
                incoming.close(); 
                ChatSer.this.removeClient(out);
            } catch (Exception e) {
                if (out != null) {
                    ChatSer.this.removeClient(out);
                }
                e.printStackTrace(); 
            }
        }
    }
}