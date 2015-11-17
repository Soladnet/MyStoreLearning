/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soladnet.service;

import com.soladnet.entity.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rasheed
 */
@Stateless
public class CustomerRepository implements CustomerRepositoryLocal {

    @PersistenceContext(unitName = "MyStore-ejbPU")
    private EntityManager em;

    public void persist(Customer customer) {
        System.out.println(String.format("persisting customer %s %s", customer.getFirstName(), customer.getLastName()));
        em.persist(customer);
        List<Customer> dbCustomer = listAll();
        for(Customer singlCustomer:dbCustomer){
            System.out.println(String.format("%s %s %s", singlCustomer.getId(),singlCustomer.getFirstName(),singlCustomer.getLastName()));
        }
    }

    public void merge(Customer customer) {
        em.merge(customer);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Customer> listAll() {
        List<Customer> customer = em.createNamedQuery("Customer.listAll", Customer.class).getResultList();
        return customer.isEmpty() ? null : customer;
    }

    @Override
    public void add(Customer customer) {
        System.out.println("add called for customer");
        persist(customer);
    }

    @Override
    public void update(Customer customer) {
        merge(customer);
    }
}
