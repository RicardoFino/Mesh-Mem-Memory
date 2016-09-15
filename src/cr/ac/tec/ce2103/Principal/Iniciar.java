package cr.ac.tec.ce2103.Principal;

import cr.ac.tec2103.MeshMemMemory.Conexion;

public class Iniciar {
    private Conexion NuevaConexion = new Conexion();
    
    public void Inicializar(){
        NuevaConexion.run();
    }
    
}
