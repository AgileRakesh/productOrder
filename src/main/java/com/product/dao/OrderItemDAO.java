package com.product.dao;

import java.util.List;

import com.product.controller.DTO.OrderItemDTO;

public interface OrderItemDAO {
	
	public int[] insertOrderItemData(List<OrderItemDTO> list);
	
	public List<OrderItemDTO> getAllOrderItems();
	
	public OrderItemDTO getOrderItem(Integer itemId);

}
