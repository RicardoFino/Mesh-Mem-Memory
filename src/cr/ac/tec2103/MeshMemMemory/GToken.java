/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.tec2103.MeshMemMemory;

import java.util.UUID;

/**
 *
 *
 */
public class GToken {
    
    public String Generar(){
        return String.valueOf(UUID.randomUUID());
    }
    
}
