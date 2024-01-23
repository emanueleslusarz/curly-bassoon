package p15112023;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    // Definizione IP e PORTA del Server
    String ipServer = "127.0.0.1";
    int portaServer = 8888;

    // Creazione del Socket
    Socket socket;

    // Creazione del buffer per l'INPUT DA TASTIERA
    BufferedReader tastiera;

    // Creazione degli stream di INPUT e OUTPUT
    BufferedReader streamInput;
    DataOutputStream streamOutput;

    // Creazione delle stringhe MESSAGGI
    String messaggioRicevuto;
    String messaggioInviato;

    Scanner input;

    // Metodo che STABILISCE la connessione con il Server
    public Socket connettiAlServer(){
        System.out.println("i: Connessione al Server");

        try{

            tastiera = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(ipServer, portaServer);
            streamOutput = new DataOutputStream(socket.getOutputStream());
            streamInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        }catch (UnknownHostException exception){
            System.out.println("e: Server non trovato");
        }catch (Exception exception){
            System.out.println("e: Errore nel tentativo di connessione al server");
        }

        return socket;
    }

    // Metodo che stabilisce la comunicazione con il server
    public void comunicazioneConIlServer(){

        input = new Scanner(System.in);

        try {

            // Acquisizione del messaggio
            System.out.println("Inserisci il messaggio da inviare al server");
            messaggioInviato = input.nextLine();

            // Invio del messaggio
            streamOutput.writeBytes(messaggioInviato+'\n');

            // Attesa nella risposta del server
            messaggioRicevuto = streamInput.readLine();

            // Vista a schermo del messaggio
            System.out.println("Messaggio Ricevuto: " + messaggioRicevuto);

            // Chiusuara della connessione
            socket.close();

        }catch (Exception exception){
            System.out.println("e: Errore nel tentativo di connessione al server");
        }

    }

    public static void main (String [] Args){

        Client client = new Client();

        while (true){
            client.connettiAlServer();
            client.comunicazioneConIlServer();
        }

    }


}
