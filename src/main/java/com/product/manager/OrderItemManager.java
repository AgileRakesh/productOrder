package com.product.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.controller.DTO.OrderItemDTO;
import com.product.dao.OrderManagementDAO;





@Component
public class OrderItemManager {
	
	@Autowired
	OrderManagementDAO orderManagementRepo;
	
	public boolean saveOrderItemData(List<OrderItemDTO> list) {
		orderManagementRepo.insertOrderItemData(list);
		
		return true;
	}
	public List<OrderItemDTO> getOrderItems(){
		return orderManagementRepo.getAllOrderItems();
	}
	
	
	
	
	
	public OrderItemDTO getOrderItem(Integer id) {
		return orderManagementRepo.getOrderItem(id);
	}

}
