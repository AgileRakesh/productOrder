package com.product.dao;

import java.util.List;

import com.product.controller.DTO.OrderDTO;

public interface OrderDetailsDAO {
	
	public int[] insertOrderData(List<OrderDTO> list);
	
	public List<OrderDTO> getAllOrders();
	
	public OrderDTO getOrder(Integer id);

}
