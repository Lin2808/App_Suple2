/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Esta línea importa la clase DetallePedidoEliminadoDao del paquete DAO
import DAO.DetallePedidoEliminadoDao;

//Esta línea importa la clase DetallePedido del paquete Models
import Models.DetallePedidoEliminado;

//Esta importa la biblioteca para usar DefaultTableModel
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author churri
 */
public class DetallePedidoEliminadoController {

    //Se crea una variable de tipo DetallePedidoEliminadoDao
    DetallePedidoEliminadoDao dpedao;

    //Esta variable se usará para mostrar un mensaje
    String message;

    public DetallePedidoEliminadoController() {
        this.message = "";//Se inicializa la variable mensaje
    }

    //Este método registra un detalle de pedido que ha sido eliminado
    public String insertarDetallePedidoEliminado(String cantidad, String precio,
            String id_producto) {
        //Se crea el objeto de la clase DetallePedidoEliminadoDao
        dpedao = new DetallePedidoEliminadoDao();

        //Se crea el objeto y se instancia la clase DetallePedidoEliminado
        DetallePedidoEliminado detpe = new DetallePedidoEliminado();
        this.message = "Error en los parametros ingresados";
        //En las siguientes líneas se hará uso de los método set de la clase
        //DetallePedidoEliminadoDao para settear los datos necesarios para
        //guardar un detalle de pedido que ha sido eliminado
        detpe.setCantidad(cantidad);
        detpe.setPrecio(precio);
        detpe.setId_producto(id_producto);
        //Se realizará una validación para saber si se realizó o no con
        //éxito la operación
        if (dpedao.insertarDetallePedidoEliminado(detpe)) {
            this.message = "correcto";
        } else {
            this.message = "error en la base de datos";
        }

        return this.message;
    }
    //Este método sirve para obtener un  producto por su id
    public String getProductos(String id_pedido) {
        //Se crea el objeto de la clase DetallePedidoEliminadoDao
        dpedao = new DetallePedidoEliminadoDao();
        
        //Se crea el objeto y se instancia un objeto de tipo
        //DefaultTableModel en donde se obtiene el id del producto
        DefaultTableModel table = dpedao.getProductos(id_pedido);
        String detalle = "";
        //Obtener los datos del producto de la fila y columan seleccionada
        for (int i = 0; i < table.getRowCount(); i++) {
            detalle += table.getValueAt(i, 4).toString() + ";" +
                    table.getValueAt(i, 1).toString() + ";" +
                    table.getValueAt(i, 3).toString();
            if (i < table.getRowCount() - 1) {
                detalle += "/";
            }
        }
        System.out.println(detalle);
        return detalle;
    }

}
