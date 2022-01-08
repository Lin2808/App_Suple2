/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Esta línea importa la clase DetallePedidoDao del paquete DAO
import DAO.DetallePedidoDao;

//Esta línea importa la clase DetallePedido del paquete Models
import Models.DetallePedido;

/**
 *
 * @author churri
 */
public class DetallePedidoController {
    //Se crea una variable de tipo DetallePedidoDao
    DetallePedidoDao detpdao;
    //Esta variable se usará para mostrar un mensaje
    String message;

    public DetallePedidoController() {
        this.message = "";//Se inicializa la variable mensaje
    }
    
    //Este método registra un detalle de pedido
    public String insertarDetallePedido(String id_producto, String cantidad,
            String precio){
        //Se crea el objeto de la clase DetallePedidoDao
        detpdao = new DetallePedidoDao();
        //Se crea el objeto y se instancia la clase Modelo DetallePedido
        DetallePedido detm = new DetallePedido();
        this.message = "Error en los parametros ingresados";
        //En las siguientes líneas se hará uso de los método set de la clase
        //DetallePedido para settear los datos necesarios para agregar
        //un nuevo detalle de pedido
        detm.setCantidad(cantidad);
        detm.setPrecio(precio);
        detm.setId_producto(id_producto);
        //Se realizará una validación para saber si se realizó o no con
        //éxito la operación
        if (detpdao.insertarDetallePedido(detm))
        {
            this.message = "Pedido realizado con exito";
        }
        else
        {
            this.message = "error de base de datos";
        }
        return this.message;
    }
    
    //Este método disminuye el stock del producto
    public String disminuirStock(String id_producto, String id_cantidad) {
        //Se crea el objeto de la clase DetallePedidoDao
        detpdao = new DetallePedidoDao();
        //Se crea el objeto y se instancia la clase Modelo DetallePedido
        DetallePedido detm = new DetallePedido();
        this.message = "Error en los parametros ingresados";
        //En las siguientes líneas se hará uso de los método set de la clase
        //DetallePedido para settear los datos necesarios para disminuir
        //el stock del producto
        detm.setId_producto(id_producto);
        detm.setCantidad(id_cantidad);
        //Se realizará una validación para saber si se realizó o no con
        //éxito la operación
        if (detpdao.disminuirStock(detm)) {
            this.message = "Stock actualizado correctamente";
        } else {
            this.message = "error de base de datos";
        }
        return this.message;
    }
    
    //Este método aumenta el stock del producto
    public String aumentarStock(String id_producto, String id_cantidad) {
        //Se crea el objeto de la clase DetallePedidoDao
        detpdao = new DetallePedidoDao();
        DetallePedido detm = new DetallePedido();
        this.message = "Error en los parametros ingresados";
        //En las siguientes líneas se hará uso de los método set de la clase
        //DetallePedido para settear los datos necesarios para aumentar
        //el stock del producto
        detm.setId_producto(id_producto);
        detm.setCantidad(id_cantidad);
        //Se realizará una validación para saber si se realizó o no con
        //éxito la transacción
        if (detpdao.aumentarStock(detm)) {
            this.message = "Stock actualizado correctamente";
        } else {
            this.message = "error de base de datos";
        }
        return this.message;
    }

}
