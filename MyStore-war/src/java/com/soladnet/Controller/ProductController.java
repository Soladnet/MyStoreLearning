/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soladnet.Controller;

import com.soladnet.entity.Product;
import com.soladnet.service.ProductRepositoryLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author rasheed
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

    @EJB
    private ProductRepositoryLocal productRepository;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String name;
            String desc;
            int qty;
            double price;
            JSONObject json = new JSONObject();
            json.put("status", false);
            Product product;
            switch (request.getParameter("action")) {
                case "create":
                    name = request.getParameter("productName");
                    desc = request.getParameter("productDesc");
                    qty = Integer.parseInt(request.getParameter("productQty"));
                    price = Double.parseDouble(request.getParameter("productPrice"));
                    if (name == null) {
                        json.put("message", "Product Name is missing");
                        break;
                    }
                    if (name.isEmpty()) {
                        json.put("message", "Product Name is missing");
                        break;
                    }
                    if (desc == null) {
                        json.put("message", "Description Name is missing");
                        break;
                    }
                    if (desc.isEmpty()) {
                        json.put("message", "Description Name is missing");
                        break;
                    }
                    if (qty == 0) {
                        json.put("message", "Product quatity is 0");
                        break;
                    }
                    if (price == 0.0) {
                        json.put("message", "Product price is 0");
                        break;
                    }
                    product = new Product();
                    product.setName(name);
                    product.setDesciption(desc);
                    product.setQuatity(qty);
                    product.setUnitPrice(price);
                    productRepository.add(product);
                    json.put("message", "Product added successfully");
                    break;
                case "get products":
                    List<Product> dbProducts = productRepository.listAll();

                    JSONArray jsonArray = new JSONArray();
                    
                    for (Product item : dbProducts) {
                        JSONObject temp = new JSONObject();
//                        System.out.println(String.format("%s %s %s %s %s", item.getId(), item.getName(), item.getDesciption(), item.getQuatity(), item.getUnitPrice()));
                        temp.put("name", item.getName());
                        temp.put("description", item.getDesciption());
                        temp.put("price", item.getUnitPrice());
                        temp.put("quantity", item.getQuatity());
                        temp.put("id", item.getId());
                        System.out.println(temp.toString());
                        jsonArray.put(temp);
                    }
                    json.put("status", true);
                    json.put("products", jsonArray);
                    break;
                default:
                    json.put("message", "Action not recorgnized");

            }
            out.println(json.toString());
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
