/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Esta línea importa la clase UsuarioDao del paquete DAO
import DAO.UsuarioDao;

//Esta línea importa la clase UserSession del paquete Models
import Models.UserSession;

//Esta línea importa la clase Usuario del paquete Models
import Models.Usuario;

//Esta importa la biblioteca para usar DefaultTableModel
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author churri
 */
public class UsuarioController {
    
    //Se crea una variable de tipo UsuarioDao
    UsuarioDao usdao;
    
    //Esta variable se usará para mostrar un mensaje
    String message;

    public UsuarioController() {
        message = "";//Se inicializa la variable mensaje
    }
    
    //Este método resgitra un nuevo usuario
    public String insertarUsuario(String nombres, String apellidos,
            String nombre_tienda, String estado, String tipo_usuario,
            String usuario, String contrasenia) {
        //Se crea el objeto de la clase UsuarioDao
        usdao = new UsuarioDao();
        //Se crea el objeto y se instancia la clase Usuario
        Usuario us = new Usuario();
        this.message = "Error en los parametros de entrada.";
        //En las siguientes líneas se hará uso de los método set de la clase
        //Usuario para settear los datos necesarios para registrar un
        //nuevo usuario
        us.setNombres(nombres);
        us.setApellidos(apellidos);
        us.setNombre_tienda(nombre_tienda);
        us.setEstado(estado);
        us.setTipo_usuario(tipo_usuario);
        us.setUsuario(usuario);
        us.setContrasenia(contrasenia);
        //Se realizará una validación para saber si se realizó o no con
        //éxito la operación
        if(usdao.insertarUsuario(us)){
            this.message = "Usuario registrado correctamente.";
        }else{
            this.message = "Erro al agregar un nuevo usuario.";
        }
        return this.message;
    }
    //Este método camiba el estado del usuario a "habilitado"
    public String habilitarUsuario (String id_usuario){
        usdao = new UsuarioDao();
        Usuario us = new Usuario();
        this.message = "Error en los parametros de entrada.";
        //Se envía el id del usuario que se desea habilitar
        us.setId_usuario(id_usuario);
        if(usdao.habilitar(us)){
            this.message = "Usuario habilitado correctamente";
        }else{
            this.message = "Error al habilitar el usuario";
        }
        return this.message;
    }
     //Este método obtiene los datos del usuario que inició sesión
    public UserSession login(String usuario, String contrasenia){
        UserSession usr = null;
        usdao = new UsuarioDao();
        DefaultTableModel employeeresponse = usdao.login(usuario, contrasenia);
        if (employeeresponse.getRowCount() > 0) {
            usr = new UserSession();
            usr.setId_user(employeeresponse.getValueAt(0, 0).toString());
            usr.setNombre(employeeresponse.getValueAt(0, 1).toString());
            usr.setApellido(employeeresponse.getValueAt(0, 2).toString());
            usr.setNombre_usuario(employeeresponse.getValueAt(0, 6).toString());
            usr.setCargo(employeeresponse.getValueAt(0, 5).toString());
            usr.setEstado(employeeresponse.getValueAt(0, 4).toString());
        } else {
            usr = null;
        }
        return usr;
    }
    //Lista todos los usuario registrados
    public String listarUsuarios(){
        usdao = new UsuarioDao();
        return usdao.listarUsuarios();
    }

}
