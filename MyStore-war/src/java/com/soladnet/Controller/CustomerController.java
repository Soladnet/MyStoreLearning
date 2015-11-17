/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soladnet.Controller;

import com.soladnet.entity.Customer;
import com.soladnet.service.CustomerRepositoryLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author rasheed
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerController"})
public class CustomerController extends HttpServlet {

    @EJB
    private CustomerRepositoryLocal customerRepository;

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

            JSONObject json = new JSONObject();
            json.put("status", false);
            String firstName;
            String lastName;
            Customer customer;
            switch (request.getParameter("action")) {
                case "create":
                    firstName = request.getParameter("firstName");
                    lastName = request.getParameter("lastName");
                    if (firstName == null) {
                        json.put("message", "First Name is missing");
                        break;
                    }
                    if (firstName.isEmpty()) {
                        json.put("message", "First Name is missing");
                        break;
                    }
                    if (lastName == null) {
                        json.put("message", "Last Name is missing");
                        break;
                    }
                    if (lastName.isEmpty()) {
                        json.put("message", "Last Name is missing");
                        break;
                    }
                    customer = new Customer();
                    customer.setFirstName(firstName);
                    customer.setLastName(lastName);

                    customerRepository.add(customer);
                    json.put("status", true);
                    json.put("message", "Customer saved successfully");
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
