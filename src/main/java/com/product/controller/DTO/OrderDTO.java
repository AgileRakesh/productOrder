package com.product.controller.DTO;

import java.util.List;

public class OrderDTO {
	private Integer orderId;
	private String customerName;
	private String shippingAddress;
	private Integer total;
	private Integer orderItemIds;
	private List<OrderItemDTO> orderItems;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getOrderItemIds() {
		return orderItemIds;
	}
	public void setOrderItemIds(Integer orderItemIds) {
		this.orderItemIds = orderItemIds;
	}
	public List<OrderItemDTO> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}
