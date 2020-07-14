package com.product.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.product.controller.DTO.OrderItemDTO;
import com.product.exception.CustomOrderNotFoundException;



@Repository
public class OrderItem implements OrderItemDAO{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String SAVE_ORDER_ITEM="insert into ORDER_MANAGEMENT_ORDER_ITEM_DETAILS(PRODUCT_NAME,PRODUCT_CODE,QUANTITY) values (?,?,?) ";
	private static final String GET_ORDER_ITEMS="SELECT PRODUCT_NAME AS productName,PRODUCT_CODE as productCode,QUANTITY as quantity, ORDER_MANAGEMENT_ORDER_ITEM_DETAILS_ID orderItemId from ORDER_MANAGEMENT_ORDER_ITEM_DETAILS";
	private static final String GET_ORDER_ITEM="SELECT PRODUCT_NAME AS productName,PRODUCT_CODE as productCode,QUANTITY as quantity, ORDER_MANAGEMENT_ORDER_ITEM_DETAILS_ID orderItemId from ORDER_MANAGEMENT_ORDER_ITEM_DETAILS where ORDER_MANAGEMENT_ORDER_ITEM_DETAILS_ID=?";

	
	
	public int[] insertOrderItemData(List<OrderItemDTO> list) {
		return jdbcTemplate.batchUpdate(SAVE_ORDER_ITEM,
				new BatchPreparedStatementSetter() {

					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, list.get(i).getProductName());
						ps.setInt(2, list.get(i).getProductCode());
						ps.setInt(3, list.get(i).getQuantity());
					}

					public int getBatchSize() {
						return list.size();
					}

				});	
		}
	
	public List<OrderItemDTO> getAllOrderItems(){
		return jdbcTemplate.query(GET_ORDER_ITEMS,new BeanPropertyRowMapper<OrderItemDTO>(OrderItemDTO.class));
		
	}
	
	public OrderItemDTO getOrderItem(Integer itemId) {
		try {
			
			return jdbcTemplate.queryForObject(GET_ORDER_ITEM,new BeanPropertyRowMapper<OrderItemDTO>(OrderItemDTO.class),itemId);

		}catch(EmptyResultDataAccessException e) {
		 throw new CustomOrderNotFoundException("Order item not found");
		}
	
}	
		
	
	
	

}
