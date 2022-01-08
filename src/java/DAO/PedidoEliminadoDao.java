/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DataStatic.Conection;
import Models.PedidoEliminado;

/**
 * 
 * @author churri
 */
public class PedidoEliminadoDao {
    
    Conection conex;
    String sql = "";
    
    public PedidoEliminadoDao(){
        conex = new Conection();
    }
    //Elimina un pedido
    public boolean insertarPedidoEliminado(PedidoEliminado pe){
        sql = String.format("insert into encabezado_pedido_eliminado "
                + "(fecha_eliminar, fecha_pedido, usuario_id_usuario, "
                + "descuento, total) "
                + "values(now(), '%s', %s, %s, %s)",pe.getFecha_pedido(), 
                pe.getId_usuario(), pe.getDescuento(), pe.getTotal());
        return conex.modifyBD(sql);
    }
}
