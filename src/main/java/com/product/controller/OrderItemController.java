package com.product.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.controller.DTO.OrderItemDTO;
import com.product.manager.OrderItemManager;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
	@Autowired
	OrderItemManager orderItemManager;
	
	
	
	/**
	 * fetches order item for given item id
	 * @param itemId to fetch order item details
	 * @return order item 
	 */
	@RequestMapping(value="/getOrderItem",method=RequestMethod.GET)
	public OrderItemDTO getOrderItem(@RequestParam("itemId") @NotBlank Integer itemId) {
		return orderItemManager.getOrderItem(itemId);
	}
	
	
	/**
	 * Retrieves all order item details
	 * @return list OrderItemDTO
	 */
	@RequestMapping(value="/getOrderItemDetails",method=RequestMethod.GET)
	public List<OrderItemDTO> getOrderItems(){
		return orderItemManager.getOrderItems();
	}
	
	
	/**
	 * creates new order item in Database
	 * @param data to save in database
	 * @return true if data is saved successfully otherwise false
	 */
	@RequestMapping(value="/createOrderItem",method=RequestMethod.POST)
	public boolean createOrderItem(@RequestBody @Valid List<OrderItemDTO> data ) {
		return orderItemManager.saveOrderItemData(data);			
	}

}
