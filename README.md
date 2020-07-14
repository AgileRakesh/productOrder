# productOrder
It contains two micro services for creating/reading order and order item. get order service uses feign client to call order item microservice.

import/clone in STS/eclipse then covert it to maven project.(click on project root then choose configure then convert to maven project)
----

OrderItemController(/orderItem): responsible for creating order items, retriving all order items and also fetches order item for given order item id using H2.
				
/orderItem/createOrderItem: service takes list of orderItems as json request (request body)
/orderItem/getOrderItem : service takes order 'itemId' as request param to fetch order item deatils. 
/orderItem/getOrderItemDetails : service fetches all order items from order item table
				
FeignClientOrderController(/ordersFeignClient): responsible for creating orders, retriving order and it fetches order deatils for given order id using H2. 
			    Here every order will communicate with order item service for retriving order item details using feign client.
ordersFeignClient/createOrder : service takes list of orders as json request (request body)
ordersFeignClient/getOrderDetails:service takes orderId as request param to fetch order deatils.
ordersFeignClient/getOrder:service fetches all orders from order item table.(uses feign client to get order items from /getOrderItem service)
			
Both controller services produces and consumes json.

OrderItemsUsingFeign : interface to define target microservice url and service call .(url will be changed if target microservice changes)

H2 Database: schema.sql , data.sql



json request for creating new order items:
---
  
[
    {
        "productCode": 25,
        "productName": "pump",
        "quantity": 3
    },
     {
        "productCode": 789,
        "productName": "mobile",
        "quantity": 1
    }
]


----

json request for creating new order:(note- please give valid integer order item ID from order item table)
----
[
    {
        "customerName":"ram",
        "shippingAddress":"chengal, hyderabad, Telangal-10",
        "total":2563,
        "orderItemIds":23
    }
]
