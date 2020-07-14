package com.product.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.product.controller.DTO.OrderDTO;
import com.product.exception.CustomOrderNotFoundException;




@Repository
public class OrderDetails implements OrderDetailsDAO{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String SAVE_ORDER="INSERT INTO ORDER_MANAGEMENT_ORDER_DETAILS(CUSTOMER_NAME,ORDER_DATE,"
			+ "SHIPPING_ADDRESS,ORDER_MANAGEMENT_ORDER_ITEM_DETAILS_ID,TOTAL_AMT) VALUES (?,CURRENT_TIMESTAMP,?,?,?)";
	
	private static final String GET_ORDERS="SELECT ORDER_MANAGEMENT_ORDER_DETAILS_ID AS orderId,CUSTOMER_NAME as customerName,ORDER_DATE as orderDate,SHIPPING_ADDRESS as shippingAddress,ORDER_MANAGEMENT_ORDER_ITEM_DETAILS_ID as orderItemIds ,TOTAL_AMT as total from ORDER_MANAGEMENT_ORDER_DETAILS";

	private static final String GET_ORDER="SELECT ORDER_MANAGEMENT_ORDER_DETAILS_ID AS orderId,CUSTOMER_NAME as customerName,ORDER_DATE as orderDate,SHIPPING_ADDRESS as shippingAddress,ORDER_MANAGEMENT_ORDER_ITEM_DETAILS_ID as orderItemIds ,TOTAL_AMT as total from ORDER_MANAGEMENT_ORDER_DETAILS where ORDER_MANAGEMENT_ORDER_DETAILS_ID=?";

	
	public int[] insertOrderData(List<OrderDTO> list) {
		return jdbcTemplate.batchUpdate(SAVE_ORDER,
				new BatchPreparedStatementSetter() {

					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, list.get(i).getCustomerName());
						ps.setString(2, list.get(i).getShippingAddress());
						ps.setInt(3, list.get(i).getOrderItemIds());
						ps.setInt(4,list.get(i).getTotal());
					}

					public int getBatchSize() {
						return list.size();
					}

				});	
		}
	public List<OrderDTO> getAllOrders(){
		return jdbcTemplate.query(GET_ORDERS,new BeanPropertyRowMapper<OrderDTO>(OrderDTO.class));
		
	}
	
	public OrderDTO getOrder(Integer id){
		try {
			return jdbcTemplate.queryForObject(GET_ORDER,new BeanPropertyRowMapper<OrderDTO>(OrderDTO.class),id);

		}catch(EmptyResultDataAccessException e) {
			 throw new CustomOrderNotFoundException("Order not found");
			}
		
	}
}
