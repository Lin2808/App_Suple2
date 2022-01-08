/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStatic.Conection;
import Models.DetallePedido;

/**
 *
 * @author churri
 */
public class DetallePedidoDao {

    Conection conex;
    String sql = "";

    public DetallePedidoDao() {
        conex = new Conection();
    }
    
    //Registra un nuevo pedido en la base de datos
    public boolean insertarDetallePedido(DetallePedido detp) {
        sql = String.format("insert into detalle_pedido (producto_id_producto, "
                + "cantidad, precio_unit, encabezado_pedido_id_encapedido) "
                + "values (%s, %s, %s, (select id_encapedido from "
                + "encabezado_pedido order by id_encapedido desc limit 1))",
                detp.getId_producto(), detp.getCantidad(), detp.getPrecio());
        return conex.modifyBD(sql);
    }
    
    //Elimina un detalle de pedido por medio de su id
    public boolean cancelarPedido(DetallePedido detp) {
        //Cancela un pedido
        sql = String.format("delete from detalle_pedido where "
                + "encabezado_pedido_id_encapedido = %s", detp.getId_pedido());
        return conex.modifyBD(sql);
    }
    //Disminuye el stock del producto
    public boolean disminuirStock(DetallePedido detp) {
        sql = String.format("update producto set stock = stock - %s "
                + "where id_producto = %s", detp.getCantidad(), 
                detp.getId_producto());
        return conex.modifyBD(sql);
    }
    //Aumenta el stock del producto
    public boolean aumentarStock(DetallePedido detp) {
        sql = String.format("update producto set stock = stock + %s where "
                + "id_producto = %s", detp.getCantidad(), detp.getId_producto());
        return conex.modifyBD(sql);
    }

}
