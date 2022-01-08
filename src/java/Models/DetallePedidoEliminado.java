/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

//Esta es la clase modelo hará interacción con la tabla DetallePedidoEliminado
public class DetallePedidoEliminado {
    private String id_detalle_pedido_eliminado = "";
    private String id_pedido_eliminado = "";
    private String id_producto = "";
    private String cantidad = "";
    private String precio = "";

    public DetallePedidoEliminado() {
    }

    public String getId_detalle_pedido_eliminado() {
        return id_detalle_pedido_eliminado;
    }

    public void setId_detalle_pedido_eliminado(String id_detalle_pedido_eliminado) {
        this.id_detalle_pedido_eliminado = id_detalle_pedido_eliminado;
    }

    public String getId_pedido_eliminado() {
        return id_pedido_eliminado;
    }

    public void setId_pedido_eliminado(String id_pedido_eliminado) {
        this.id_pedido_eliminado = id_pedido_eliminado;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
}
