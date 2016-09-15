package cr.ac.tec2103.MeshMemMemory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Server extends Thread {
    private Socket nuevoSocket;
    private static Socket socket;
    private static InputStreamReader SocketEn;
    private static BufferedReader entrada;
    private static int IDcliente;
    private static String MensajeEn;
    private static String MSG;
    static JSONObject json = new JSONObject();
    
    public Server (Socket nuevoSocket, int IDcliente){
        this.nuevoSocket = nuevoSocket;
        this.IDcliente = IDcliente;
    }
    
    public static void CrearCliente (Socket nuevoSocket, int IDjugador){
        Server.socket = nuevoSocket;
        try{
            LectorMSG((new BufferedReader (
                    new InputStreamReader (
                            socket.getInputStream()))));
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
	
    public static void EnviarMSG(String MSG){
	try{    
	    DataOutputStream Out = new DataOutputStream(socket.getOutputStream());
	    Out.writeChars(MSG);
	    Out.flush();
        }catch (IOException e){
	        e.getMessage();     
	}
    }
	 
    public static void LectorMSG (BufferedReader entrada){
	BufferedReader Mensaje = entrada;        
	while(true){
	    try{
	        MensajeEn = Mensaje.readLine();
	        LectorJSON(MensajeEn);
	    }catch(IOException e){
	        System.out.println("Error al leer el mensaje "+ e.getMessage());
	    }
	}  
    }
	 
    public static void LectorJSON(String MensajeEn){
        System.out.println("Mensaje entrando "+ MensajeEn);
	JSONParser parse = new JSONParser();
	try{
	    String json = MensajeEn;
	    Object obj = parse.parse(json);
	    JSONObject jsonObject = (JSONObject) obj;
            String Accion = (String) jsonObject.get("Accion");
            Accion(Accion, jsonObject);

	}catch(ParseException e){
	    System.out.println("Type no especificado");
	}
    }
	    
    private static void Accion (String Accion, JSONObject json){
        System.out.println("Json recibido");
        
        if (Accion.equals("Hola")){
            json.put("Accion","Conexion");
            json.put("MSG","ok");
            json.put("jjj","bien");
            json.put("ggg","juan");
            System.out.println(json.toString());
            EnviarMSG(json.toString());

        }else if (Accion.equals("GToken")){
            GToken G = new GToken();
            json.put("Accion","Token");
            json.put("Token",G.Generar());
            EnviarMSG(json.toString());
        }else if(Accion.equals("toFree")){
            // LLAMAR METODO QUE HACE ESTO
        
        }else if (Accion.equals("Desreferencia")){
            // LLAMAR METODO QUE HACE ESTO
        
        }else if (Accion.equals("Igualdad")){
            // LLAMAR METODO QUE HACE ESTO
        
        }else if (Accion.equals("Asignacion")){
            // LLAMAR METODO QUE HACE ESTO
        
        }else if (Accion.equals("Diferencia")){
            // LLAMAR METODO QUE HACE ESTO
        
        }else if (Accion.equals("xAssign")){
            // LLAMAR METODO QUE HACE ESTO
        
        }else if (Accion.equals("Salir")){
            System.out.println(socket + "Desconectado");

//            try{
//                socket.close();
//
//            }catch(IOException e){
//                System.out.println(e.getMessage());
//            }
        }
        
        else{
            System.out.println("Formato del mensaje erroneo");
        }
    }
    
    @Override
    public void run(){
        Server.CrearCliente(nuevoSocket, IDcliente);
    }
}
