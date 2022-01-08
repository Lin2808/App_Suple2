/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

//Esta línea importa la clase PedidoEliminadoDao del paquete DAO
import DAO.PedidoEliminadoDao;
//Esta línea importa la clase PedidoEliminado del paquete Models
import Models.PedidoEliminado;

/**
 * 
 * @author churri
 */
public class PedidoEliminadoController {
    //Se crea una variable de tipo PedidoEliminadoDao
    PedidoEliminadoDao pedao;
    //Esta variable se usará para mostrar un mensaje
    String message;
    
    public PedidoEliminadoController (){
        this.message = "";
    }
    
    //Este método registra un pedido que ha sido eliminado
    public String insertarPedidoEliminado(String fecha_pedido,
            String id_usuario, String descuento, String total){
        //Se crea el objeto de la clase PedidoEliminadoDao
        pedao = new PedidoEliminadoDao();
        //Se crea el objeto y se instancia la clase Modelo PedidoEliminado
        PedidoEliminado pe = new PedidoEliminado();
        this.message = "Error en los parametros ingresados";
        //En las siguientes líneas se hará uso de los método set de la clase
        //PedidoEliminado para settear los datos necesarios para agregar
        //un pedido que ha sido eliminado
        pe.setFecha_pedido(fecha_pedido);
        pe.setId_usuario(id_usuario);
        pe.setDescuento(descuento);
        pe.setTotal(total);
        //Se realizará una validación para saber si se realizó o no con
        //éxito la operación
        if(pedao.insertarPedidoEliminado(pe)){
            this.message = "corecto";
        }else{
            this.message = "error de base dedatos";
        }
        
        return this.message;
    }
    
}
