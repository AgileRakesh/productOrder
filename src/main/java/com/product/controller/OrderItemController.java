package com.product.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.controller.DTO.OrderItemDTO;
import com.product.manager.OrderItemManager;





@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
	@Autowired
	OrderItemManager orderItemManager;
	
	
	//fetches order item for given item id
	@GetMapping(value="/getOrderItem")
	public OrderItemDTO getOrderItem(@RequestParam("itemId") @NotBlank Integer itemId) {
		return orderItemManager.getOrderItem(itemId);
		
	}
	
	//Retrives all order item details
	@GetMapping(value="/getOrderItemDetails")
	public List<OrderItemDTO> getOrderItems(){
		return orderItemManager.getOrderItems();
	}
	
	//creates new order item in DB
	@PostMapping(value="/createOrderItem")
	public boolean createOrderItem(@RequestBody @Valid List<OrderItemDTO> data ) {
		orderItemManager.saveOrderItemData(data);	
		return true;
		
	}

}
