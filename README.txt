
Product module:
    get    :  localhost:8080/product/                         methodName : getAllProduct
    get    :  localhost:8080/product/id/<product_id>          methodName : getProductById
    get    :  localhost:8080/product/name/<product_name>      methodName : getAllProductById
    post   :  localhost:8080/product/add/                     methodName : addProduct
    put    :  localhost:8080/product/update                   methodName : updateProduct
    delete :  localhost:8080/product/delete/id/<product_id>   methodName : deleteProduct


Order module:
    get    : localhost:8080/order/                            methodName : getAllOrder
    get    : localhost:8080/order/id/<order_id>               methodName : getOrderById
    get    : localhost:8080/order/                            methodName : getAllOrder
    get    : localhost:8080/order/customer-id/<customer_id>   methodName : getAllOrdersByCustomerId
    post   : localhost:8080/order/add                         methodName : addOrder

Cart module:
    get    : localhost:8080/cart/                            methodName : getAllCart
    get    : localhost:8080/cart/id/<cart_id>                methodName : getCartById
    post   : localhost:8080/cart/add                         methodName : addCart
    put    : localhost:8080/cart/update/<cart_id>            methodName : updateCart
    delete : localhost:8080/cart/delete/<cart_id>            methodName : deleteCart

Payment module:
    get    : localhost:8080/payment/                         methodName : getAllPayments
    get    : localhost:8080/payment/add                      methodName : addPayment

Customer module:
    get    : localhost:8080/customer/                        methodName : getAllCustomers
    get    : localhost:8080/customer/id/<customer_id>        methodName : getCustomerById
    post   : localhost:8080/customer/add                     methodName : addCustomer
    update : localhost:8080/customer/update/                 methodName : updateCustomer deleteCustomerById
    update : localhost:8080/customer/delete/id/<customer_id> methodName : deleteCustomerById
