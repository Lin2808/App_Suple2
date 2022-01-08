/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DataStatic.Conection;
import Models.Usuario;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author churri
 */
public class UsuarioDao {
    
    Conection conex;
    String sql = "";
    
    public UsuarioDao() {
        conex = new Conection();
    }
    //Registra un nuevo usuario
    public boolean insertarUsuario(Usuario us){
        sql = String.format("insert into usuario (nombres, apellidos, "
                + "nombre_tienda, estado, tipo_usuario,"
                + "usuario, contrasenia) values('%s','%s','%s','%s','%s','%s',"
                + "'%s')",us.getNombres(),
                us.getApellidos(), us.getNombre_tienda(), us.getEstado(), 
                us.getTipo_usuario(), us.getUsuario(), us.getContrasenia());
        return conex.modifyBD(sql);
    }
    //Cambia el estado del usuario a "habilitado"
    public boolean habilitar(Usuario us){
        sql = "update usuario set estado = 'a' where id_usuario = "
                +us.getId_usuario()+"";
        return conex.modifyBD(sql);
    }
    //Retorna los usuarios
    public DefaultTableModel login(String nombre_user, String contrasenia){
        sql = "select * from usuario where usuario = '"+nombre_user+"' and "
                + "contrasenia = '"+contrasenia+"'";
        System.out.println(sql);
        return conex.returnRecord(sql);
    }
    //Lista los todos usuarios registrados
    public String listarUsuarios(){
        sql = "select * from usuario order by id_usuario";
        return conex.getRecordsInJson(sql);
    }
   
    
}
