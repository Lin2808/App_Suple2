/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Esta línea importa la clase PedidoDao del paquete DAO
import DAO.PedidoDao;

//Esta línea importa la clase Pedido del paquete Models
import Models.Pedido;

/**
 *
 * @author churri
 */
public class PedidoController {
    
    //Se crea una variable de tipo PedidoDao
    PedidoDao pedao;
    //Esta variable se usará para mostrar un mensaje
    String message;

    public PedidoController() {
        this.message = "";//Se inicializa la variable mensaje
    }
    //Este método registra un pedido
    public String insertarPedido(String estado, String id_usuario, String total)
    {
        //Se crea el objeto de la clase PedidoDao
        pedao = new PedidoDao();
        //Se crea el objeto y se instancia la clase Pedido
        Pedido pe = new Pedido();

        this.message = "Error en los parametros de entrada";
        
        //En las siguientes líneas se hará uso de los método set de la clase
        //Pedido para settear los datos necesarios para guardar un pedido
        pe.setEstado(estado);
        pe.setId_usuario(id_usuario);
        pe.setTotal(total);
        
        //validacion de los descuentos
        int descuento = Integer.parseInt(pedao.cantidadVentasPorMes(id_usuario));
        pe.setDescuento(descuento == 0 ? descuento : (descuento > 0 &&
                descuento < 11) ? descuento : 10);
        
        //Se realizará una validación para saber si se realizó o no con
        //éxito la operación
        if (pedao.insertarPedido(pe)) {
            this.message = "procesando...";
        } else {
            this.message = "error de base de datos";
        }
        return this.message;
    }
    
    //Listar los pedido de un usuario
    public String listarPedidos(String id_usuario) {
        pedao = new PedidoDao();
        return pedao.listarPedidos(id_usuario);
    }
    
    //Lista los pedidos cancelados de un usuario
    public String listarPedidosCancelados(String id_usuario) {
        pedao = new PedidoDao();
        return pedao.listarPedidosCancelados(id_usuario);
    }
    
    //Lista los pedidos de un usuario usuario de la tienda
    public String listarPedidosTienda(String id_usuario){
        pedao = new PedidoDao();
        Pedido pe = new Pedido();
        
        pe.setId_usuario(id_usuario);
        return pedao.listarPedidosTienda(pe);
    }
    
    //Elimina un pedido, se para el id del pedido como parámetro
    public String cancelarPedido (String id_pedido){
        pedao = new PedidoDao();
        if(pedao.cancelarPedido(id_pedido)){
                this.message = "Pedido cancelado correctamente";
        }else{
            this.message = "error";
        }
        return this.message;
    }
    
    //Cambia el estado del pedido a "despachado", se envía comoparámetro
    // el id del pedido
    public String despacharPedido (String id_pedido){
        pedao = new PedidoDao();
        if(pedao.despacharPedido(id_pedido)){
                this.message = "Pedido despachado correctamente";
        }else{
            this.message = "error";
        }
        return this.message;
    }
    
    //Devuelve el descuento que tendrá el cliente dependiendo de las
    //compras que este haya realizado por mes
    public String cantidadDescuento(String id_usuario){
        pedao = new PedidoDao();
        int descuento = Integer.parseInt(pedao.cantidadVentasPorMes(id_usuario));
        int descuento_response = (descuento == 0 ? descuento : (descuento > 0
                && descuento < 11) ? descuento : 10);
        return  String.valueOf(descuento_response);
    }
    
}
