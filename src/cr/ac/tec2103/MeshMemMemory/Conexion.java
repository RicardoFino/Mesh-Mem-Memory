package cr.ac.tec2103.MeshMemMemory;
import java.io.*;
import java.net.*;

public class Conexion extends Thread{
    private int puerto = 8080;
    private int IDcliente = 1;
    private ServerSocket Servidor;
    private Thread hilo;

    
    @Override
    public void run(){
        
        try{
            Servidor = new ServerSocket (puerto);
            InetAddress localHost = InetAddress.getLocalHost();
            String NameServer = localHost.getHostName();
            String IP_Server = localHost.getHostAddress();
            System.out.println("Servidor "+NameServer+ " en linea, ingresar a la IP: "+ IP_Server+" Puerto: "+ puerto);
            
            while(true){
                Socket nuevoSocket = Servidor.accept();
                System.out.println("Cliente :"+ IDcliente +" Conexion estrablecida " + nuevoSocket);
                System.out.println("Nueva Conexion");
                IDcliente++;
                //((Server) new Server (nuevoSocket,IDcliente)).start();//ver que sucede
                hilo = new Thread(new Server(nuevoSocket, IDcliente),"hilo");
                hilo.start();
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
