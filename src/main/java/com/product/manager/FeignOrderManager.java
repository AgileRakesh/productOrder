package com.product.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.controller.DTO.OrderDTO;
import com.product.controller.DTO.OrderItemDTO;
import com.product.dao.OrderDetailsDAO;
import com.product.feign.proxy.OrderItemsUsingFeign;

@Component
public class FeignOrderManager {
	
	@Autowired
	OrderDetailsDAO orderDetailsDAO;
	
	@Autowired
	private OrderItemsUsingFeign orderItemsUsingFeign;
	
	/**
	 * @param list to save
	 * @return true if data is saved successfully otherwise false
	 */
	public boolean saveOrderData(List<OrderDTO> list) {
		orderDetailsDAO.insertOrderData(list);
		return true;
	}
	
	/**
	 * @return all order from order table
	 */
	public List<OrderDTO> getOrders(){
		
		List<OrderDTO> orderList= orderDetailsDAO.getAllOrders();
		//iterate for every order and get order items from /getOrderItem service by passing item id
		orderList.forEach(order->{
			OrderItemDTO orderItem=getOrderItem(order.getOrderItemIds());
			List<OrderItemDTO> itemList=new ArrayList<>();
			itemList.add(orderItem);
			order.setOrderItems(itemList);
		});
		
		return orderList.stream().filter(Objects::nonNull).collect(Collectors.toList());
	}
	
	/**
	 * @param id
	 * @return order item using feign client 
	 */
	public OrderItemDTO getOrderItem(Integer id) {
		OrderItemDTO ob1=orderItemsUsingFeign.getOrderItem(id);
		return ob1;
		
	}
	
	/**
	 * @param id
	 * @return get order item for provided order item
	 */
	public OrderDTO getOrder(Integer id) {
		List<OrderItemDTO> itemList=new ArrayList<>();	
		OrderDTO order= orderDetailsDAO.getOrder(id);
		itemList.add(getOrderItem(order.getOrderItemIds()));
		order.setOrderItems(itemList);
		return order;	
	}

}
