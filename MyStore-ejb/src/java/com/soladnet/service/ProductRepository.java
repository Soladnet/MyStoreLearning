/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soladnet.service;

import com.soladnet.entity.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rasheed
 */
@Stateless
public class ProductRepository implements ProductRepositoryLocal {

    @PersistenceContext(unitName = "MyStore-ejbPU")
    private EntityManager em;

    public void persist(Product product) {
        em.persist(product);
    }
    public void merge(Product product){
        em.merge(product);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void add(Product product) {
        persist(product);
    }

    @Override
    public void update(Product product) {
        merge(product);
    }

    @Override
    public List listAll() {
        List<Product> product = em.createNamedQuery("Product.listAll",Product.class).getResultList();
        return product;
    }

 
}
