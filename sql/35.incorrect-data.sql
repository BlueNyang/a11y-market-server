UPDATE orders
   SET
   order_status = 'PENDING'
 WHERE order_status = 'DELIVERED'