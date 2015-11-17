/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soladnet.service;


import com.soladnet.entity.Product;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rasheed
 */
@Local
public interface ProductRepositoryLocal {

    void add(Product product);

    void update(Product product);

    List listAll();
    
}
