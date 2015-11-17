/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soladnet.service;

import com.soladnet.entity.Customer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rasheed
 */
@Local
public interface CustomerRepositoryLocal {

    List listAll();

    void add(Customer customer);

    void update(Customer customer);
    
}
