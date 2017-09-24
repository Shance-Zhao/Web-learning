package com.shopping.pojo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="general_table")
@PrimaryKeyJoinColumn(name = "productID")

public class General extends Product{

	public General(){}
}
