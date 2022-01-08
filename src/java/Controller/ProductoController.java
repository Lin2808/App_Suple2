/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

//Esta línea importa la clase ProductoDao del paquete DAO
import DAO.ProductoDao;

//Esta línea importa la clase Producto del paquete Models
import Models.Producto;

/**
 * 
 * @author churri
 */
public class ProductoController {
    //Se crea una variable de tipo ProductoDao
    ProductoDao pdao;
    //Esta variable se usará para mostrar un mensaje
    String message;
    
    public ProductoController (){
        this.message = ""; //Se inicializa la variable mensaje
    }
    
    //Este método registra un producto nuevo
    public String insertarProducto(String nombre, String stock,
            String precio_unit, String id_usuario){
        //Se crea el objeto de la clase ProductoDao
        pdao = new ProductoDao();
        //Se crea el objeto y se instancia la clase Modelo Producto
        Producto pd = new Producto();
        this.message = "Error en los parametros ingresados";
        //En las siguientes líneas se hará uso de los método set de la clase
        //Producto para settear los datos necesarios para registrar
        //un producto
        pd.setNombre(nombre);
        pd.setCantidad(stock);
        pd.setPrecio(precio_unit);
        pd.setId_usuario(id_usuario);
        //Se realizará una validación para saber si se realizó o no con
        //éxito la operación
        if(pdao.insertarProducto(pd)){
            this.message = "Producto agregado correctamente";
        }else{
            this.message = "Error al agregar producto.";
        }
        return this.message;
    }
    //Este método lista los productos de un usuario
    public String listarProductos(String id_usuario){
        pdao = new ProductoDao();
        Producto pd = new Producto();
        pd.setId_usuario(id_usuario);
        return pdao.listarProductos(pd);
    }
    //Lista los productos disponibles en la tienda
    public String listarProductosTienda(String id_usuario){
        pdao = new ProductoDao();
        Producto pd = new Producto();
        pd.setId_usuario(id_usuario);
        return pdao.listarProductosTienda(pd);
    }
    
}
