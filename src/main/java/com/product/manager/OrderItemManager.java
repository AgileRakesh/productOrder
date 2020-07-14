package com.product.manager;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.controller.DTO.OrderItemDTO;
import com.product.dao.OrderItemDAO;





@Component
public class OrderItemManager {
	
	@Autowired
	OrderItemDAO orderManagementRepo;
	
	/**
	 * @param list of OrderItemDTO to save
	 * @return returns true if provided data saved in database
	 */
	public boolean saveOrderItemData(List<OrderItemDTO> list) {
		orderManagementRepo.insertOrderItemData(list);	
		return true;
	}
	
	
	/**
	 * @return all orderItems from order item table.
	 */
	public List<OrderItemDTO> getOrderItems(){
		List<OrderItemDTO> orderItemList=orderManagementRepo.getAllOrderItems();
		return orderItemList.stream().filter(Objects::nonNull).collect(Collectors.toList());
	}
	
	/**
	 * @param id
	 * @return order details for provided order id.
	 */
	public OrderItemDTO getOrderItem(Integer id) {
		return orderManagementRepo.getOrderItem(id);
	}

}
