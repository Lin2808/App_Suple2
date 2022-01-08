/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author churri
 */

//Esta es la clase modelo hará interacción con la tabla PedidoEliminado
public class PedidoEliminado {

    private String id_pedido_eliminado = "";
    private String id_usuario = "";
    private String fecha_eliminado = "";
    private String fecha_pedido = "";
    private String descuento = "";
    private String total = "";

    public PedidoEliminado() {
    }

    public String getId_pedido_eliminado() {
        return id_pedido_eliminado;
    }

    public void setId_pedido_eliminado(String id_pedido_eliminado) {
        this.id_pedido_eliminado = id_pedido_eliminado;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha_eliminado() {
        return fecha_eliminado;
    }

    public void setFecha_eliminado(String fecha_eliminado) {
        this.fecha_eliminado = fecha_eliminado;
    }

    public String getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(String fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    
    
}
