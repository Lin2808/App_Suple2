/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controller.DetallePedidoController;
import Controller.DetallePedidoEliminadoController;
import Controller.PedidoController;
import Controller.PedidoEliminadoController;
import Models.UserSession;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dúval Carvajal S.
 */
@WebServlet(name = "PedidoServlet", urlPatterns = {"/PedidoServlet"})
public class PedidoServlet extends HttpServlet {

    private UserSession usr = null;
    private String response_server = "{}";
    private String opcion = "-1";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(response_server);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        opcion = request.getParameter("opcion");
        PedidoController pedcon = new PedidoController();
        usr = (UserSession) request.getSession().getAttribute("usuario");
        switch (opcion) {
            case "listarpedidos":
                response_server = pedcon.listarPedidos(usr.getId_user());
                break;
            case "listarpedidoscancelados":
                response_server = pedcon.listarPedidosCancelados(usr.getId_user());
                break;
            case "listarpedidosTienda":
                response_server = pedcon.listarPedidosTienda(usr.getId_user());
                break;
            case "insertarPedido":
                String estado = "p";
                String detalle = request.getParameter("detalle");
                String total = request.getParameter("total");
                System.out.println(usr.getId_user());
                String status = pedcon.insertarPedido(estado, usr.getId_user(), 
                        total);
                if (status.equals("procesando...")) {
                    String[] detalle_0 = detalle.split("/");
                    for (int i = 0; i < detalle_0.length; i++) {
                        String[] detalle_1 = detalle_0[i].split(";");
                        DetallePedidoController detpecon = 
                                new DetallePedidoController();
                        //detpecon.disminuirStock(detalle_1[0], detalle_1[1]);
                        response_server = detpecon.insertarDetallePedido
        (detalle_1[0], detalle_1[1], detalle_1[2]);
                    }
                }
                break;
            case "cancelarPedido":
                String id_pedido = request.getParameter("id_pedido");
                String fecha_pedido = request.getParameter("fecha_pedido");
                String descuento = request.getParameter("descuento");
                String totalx = request.getParameter("total");
                DetallePedidoEliminadoController depel = 
                        new DetallePedidoEliminadoController();
                DetallePedidoController detpeconxx = 
                        new DetallePedidoController();
                PedidoEliminadoController pedelx = 
                        new PedidoEliminadoController();
                pedelx.insertarPedidoEliminado(fecha_pedido, 
                        usr.getId_user(), descuento, totalx);
                System.out.println(depel.getProductos(id_pedido));
                String[] detalle_0 = depel.getProductos(id_pedido).split("/");
                for (int i = 0; i < detalle_0.length; i++) {
                    String[] detalle_1 = detalle_0[i].split(";");
                    System.out.println(detalle_1[0] + " - " + detalle_1[1]);
                    detpeconxx.aumentarStock(detalle_1[0], detalle_1[1]);
                    depel.insertarDetallePedidoEliminado(detalle_1[1],
                            detalle_1[2], detalle_1[0]);
                }
                PedidoController pedc = new PedidoController();
                response_server = pedc.cancelarPedido(id_pedido);
                break;
                case "cancelarPedidoTienda":
                String id_pedidot = request.getParameter("id_pedido");
                String fecha_pedidot = request.getParameter("fecha_pedido");
                String descuentot = request.getParameter("descuento");
                String totalxt = request.getParameter("total");
                String id_usuario = request.getParameter("id_usuario");
                DetallePedidoEliminadoController depelt = 
                        new DetallePedidoEliminadoController();
                DetallePedidoController detpeconxxt = 
                        new DetallePedidoController();
                PedidoEliminadoController pedelxt = 
                        new PedidoEliminadoController();
                pedelxt.insertarPedidoEliminado(fecha_pedidot, id_usuario, 
                        descuentot, totalxt);
                System.out.println(depelt.getProductos(id_pedidot));
                String[] detalle_0t = depelt.getProductos(id_pedidot).split("/");
                for (int i = 0; i < detalle_0t.length; i++) {
                    String[] detalle_1 = detalle_0t[i].split(";");
                    System.out.println(detalle_1[0] + " - " + detalle_1[1]);
                    detpeconxxt.aumentarStock(detalle_1[0], detalle_1[1]);
                    depelt.insertarDetallePedidoEliminado(detalle_1[1], 
                            detalle_1[2], detalle_1[0]);
                }
                PedidoController pedct = new PedidoController();
                response_server = pedct.cancelarPedido(id_pedidot);
                break;
            case "despacharPedido":
                String id_pedidos = request.getParameter("id_pedido");
                response_server = pedcon.despacharPedido(id_pedidos);
                break;

            case "disminuiStock":
                String id_producto = request.getParameter("id_producto");
                String cantidad = request.getParameter("cantidad");
                DetallePedidoController detpecon = new 
        DetallePedidoController();
                response_server = detpecon.disminuirStock(id_producto, 
                        cantidad);
                break;

            case "aumentarStock":
                String id_productox = request.getParameter("id_producto");
                String cantidadx = request.getParameter("cantidad");
                DetallePedidoController detpeconx = 
                        new DetallePedidoController();
                response_server = detpeconx.aumentarStock(id_productox, 
                        cantidadx);
                break;
            case "cantidadDescuento":
                response_server = pedcon.cantidadDescuento(usr.getId_user());
                break;
            default:
                break;
        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
