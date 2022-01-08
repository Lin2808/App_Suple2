

package Models;

//Esta es la clase modelo hará interacción con la tabla DetallePedido
public class DetallePedido {
    private String id_detalle_pedido = "";
    private String id_pedido = "";
    private String id_usuario = "";
    private String id_producto = "";
    private String precio = "";
    private String cantidad = "";

    public DetallePedido() {
    }

    public String getId_detalle_pedido() {
        return id_detalle_pedido;
    }

    public void setId_detalle_pedido(String id_detalle_pedido) {
        this.id_detalle_pedido = id_detalle_pedido;
    }

    public String getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(String id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
}
