/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DataStatic.Conection;
import Models.Producto;

/**
 * 
 * @author churri
 */
public class ProductoDao {
    
    Conection conex;
    String sql = "";
    
    public ProductoDao(){
        conex = new Conection();
    }
    //Registra un producto
    public boolean insertarProducto(Producto pc){
        sql = String.format("insert into producto(nombre, stock, precio_unit, "
                + "usuario_id_usuario) values('%s', %s, %s, %s)",pc.getNombre(), 
                pc.getCantidad(), pc.getPrecio(), pc.getId_usuario());
        return conex.modifyBD(sql);
    }
    //Lista los productos disponibles en stock
    public String listarProductos(Producto pc){
        sql = "select * from producto where usuario_id_usuario = "
                +pc.getId_usuario()+" and stock > 0 order by id_producto asc";
        return conex.getRecordsInJson(sql);
    }
    //Losta los productos disponibles en stock
    public String listarProductosTienda(Producto pc){
        sql = "select * from producto where usuario_id_usuario = "
                +pc.getId_usuario()+" and stock > 0 order by id_producto asc";
        return conex.getRecordsInJson(sql);
    }

}
