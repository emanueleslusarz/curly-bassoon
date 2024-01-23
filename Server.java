package p15112023;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    // Definizione dell'oggetto ServerSocket e quello del client
    ServerSocket server;
    Socket socket; // Socket per il client

    // Creazione delle stringhe MESSAGGI
    String messaggioRicevuto;
    String messaggioInviato;

    // Creazione degli stream di INPUT e OUTPUT
    BufferedReader streamInput;
    DataOutputStream streamOutput;

    Scanner input;

    // Definizione porta server (per accettare richieste)
    int portaAscolto = 8888;

    // Metodo per l'attesa della connessione del client
    public Socket attendi (){

        try {

            server = new ServerSocket(portaAscolto);
            socket = server.accept();
            server.close();

            streamInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            streamOutput = new DataOutputStream(socket.getOutputStream());

            System.out.println("i: Server in esecuzione");

        }catch (Exception exception){
            System.out.println("e: Errore nell'instanziazione del Server");
        }

        return socket;
    }

    // Metodo per la comunicazione verso il server
    public void comunica(){

        input = new Scanner(System.in);

        try {

            // Ottengo e mostro il messaggio del client
            messaggioRicevuto = streamInput.readLine();

            System.out.println("Risposta del client: " + messaggioRicevuto);

            // Scrivo la risposta
            System.out.println("Inserisci il messaggio da inviare al client");
            messaggioInviato = input.nextLine();

            streamOutput.writeBytes(messaggioInviato+'\n');

            // Chiudo la connessione con il client
            socket.close();

        }catch(Exception exception){

            System.out.println("e: Errore nella connessione");

        }

    }


    public static void main (String [] Args){
        Server server = new Server();

        while (true){
            server.attendi();
            server.comunica();
        }

    }

}
