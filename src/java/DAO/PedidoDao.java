/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStatic.Conection;
import Models.Pedido;

/**
 *
 * @author churri
 */
public class PedidoDao {

    Conection conex;
    String sql = "";

    public PedidoDao() {
        conex = new Conection();
    }
    //Registra un pedido en la bd
    public boolean insertarPedido(Pedido pd) {
        sql = String.format("insert into encabezado_pedido (fecha_venta, "
                + "estado, usuario_id_usuario, total, descuento) values "
                + "(now(),'%s',%s, %s, %s)", pd.getEstado(), pd.getId_usuario(), 
                pd.getTotal(), pd.getDescuento());
        return conex.modifyBD(sql);
    }
    
    //Obtiene la cantidad de ventas por mes de acuerdo al usuario
    public String cantidadVentasPorMes(String id_usuario) {
        sql = String.format("select count(id_encapedido) \n"
                + "from encabezado_pedido where DATE_PART('day', now() - "
                + "fecha_venta) <= 30 and usuario_id_usuario = %s", id_usuario);
        return conex.fillString(sql);
    }
    
    //Elimina un pedido de acuerdo al id del pedido
    public boolean cancelarPedido(Pedido pd) {
        sql = String.format("delte from encabezado_pedido where id_encapedido "
                + "= %s", pd.getId_pedido());
        return conex.modifyBD(sql);
    }
    
    //Lista los pedidos que tiene un usuario
    public String listarPedidos(String id_usuario) {
        sql = "select distinct ep.id_encapedido,ep.fecha_venta, "
                + "us.nombre_tienda, ep.total, ep.descuento, ep.estado\n"
                + "from encabezado_pedido as ep inner join detalle_pedido as "
                + "dp on ep.id_encapedido = "
                + "dp.encabezado_pedido_id_encapedido\n"
                + "inner join producto as pr on dp.producto_id_producto = "
                + "pr.id_producto \n"
                + "inner join usuario as us on pr.usuario_id_usuario = "
                + "us.id_usuario\n"
                + "where ep.usuario_id_usuario = " + id_usuario + "\n"
                + "group by ep.total, ep.id_encapedido, dp.id_detpedido, "
                + "pr.id_producto, us.id_usuario "
                + "order by ep.id_encapedido asc";
        return conex.getRecordsInJson(sql);
    }
    
    //Lista los pedidos cancelados
    public String listarPedidosCancelados(String id_usuario) {
        sql = "select distinct ep.id_pedeliminado, ep.fecha_pedido, "
                + "ep.fecha_pedido, us.nombre_tienda, ep.total, ep.descuento, "
                + "ep.descuento\n"
                + "from encabezado_pedido_eliminado as ep inner join "
                + "detalle_pedido_eliminado as dp on ep.id_pedeliminado = "
                + "dp.encabezado_pedido_eliminado_id_pedeliminado\n"
                + "inner join producto as pr on dp.producto_id_producto = "
                + "pr.id_producto \n"
                + "inner join usuario as us on pr.usuario_id_usuario = "
                + "us.id_usuario\n"
                + "where ep.usuario_id_usuario = " + id_usuario + "\n"
                + "group by ep.total, ep.id_pedeliminado, "
                + "dp.id_detalle_pedelimnado, pr.id_producto, us.id_usuario \n"
                + "order by ep.id_pedeliminado asc";
        return conex.getRecordsInJson(sql);
    }
    
    //Lista los pedidos de la tienda
    public String listarPedidosTienda(Pedido pd) {
        sql = "select distinct ep.id_encapedido,ep.fecha_venta, ep.total, "
                + "ep.descuento, ep.estado, ep.usuario_id_usuario\n"
                + "from encabezado_pedido as ep inner join detalle_pedido as "
                + "dp on ep.id_encapedido = dp.encabezado_pedido_id_encapedido\n"
                + "inner join producto as pr on dp.producto_id_producto = "
                + "pr.id_producto \n"
                + "inner join usuario as us on pr.usuario_id_usuario = "
                + "us.id_usuario\n"
                + "where us.id_usuario = " + pd.getId_usuario() + "\n"
                + "group by ep.total, ep.id_encapedido, dp.id_detpedido, "
                + "pr.id_producto, us.id_usuario \n"
                + "order by ep.id_encapedido asc";
        System.out.println(sql);
        return conex.getRecordsInJson(sql);
    }
    
    //Elimina un pedido
    public boolean cancelarPedido(String id_pedido) {
        sql = "delete from detalle_pedido where encabezado_pedido_id_encapedido "
                + "= " + id_pedido + "";
        System.out.println(sql);
        if (conex.modifyBD(sql)) {
            sql = "delete from encabezado_pedido where id_encapedido = "
                    + "" + id_pedido + "";
        }
        return conex.modifyBD(sql);
    }
    //Cambia el estado del pedido a "despachado"
    public boolean despacharPedido(String id_pedido) {
        sql = "update encabezado_pedido set estado = 'd' where id_encapedido "
                + "= " + id_pedido + "";
        System.out.println(sql);
        return conex.modifyBD(sql);
    }

}
