package com.soladnet.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TransactionRecords.class)
public abstract class TransactionRecords_ {

	public static volatile SingularAttribute<TransactionRecords, Product> product;
	public static volatile SingularAttribute<TransactionRecords, String> narration;
	public static volatile SingularAttribute<TransactionRecords, Long> id;
	public static volatile SingularAttribute<TransactionRecords, Date> transactionDate;
	public static volatile SingularAttribute<TransactionRecords, Customer> customer;

}

