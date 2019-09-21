
Product module:
    get    :  https://nafiul-ecom.herokuapp.com/product/                         methodName : getAllProduct
    get    :  https://nafiul-ecom.herokuapp.com/product/id/<product_id>          methodName : getProductById
    get    :  https://nafiul-ecom.herokuapp.com/product/name/<product_name>      methodName : getAllProductById
    post   :  https://nafiul-ecom.herokuapp.com/product/add/                     methodName : addProduct
    put    :  https://nafiul-ecom.herokuapp.com/product/update                   methodName : updateProduct
    delete :  https://nafiul-ecom.herokuapp.com/product/delete/id/<product_id>   methodName : deleteProduct


Order module:
    get    : https://nafiul-ecom.herokuapp.com/order/                            methodName : getAllOrder
    get    : https://nafiul-ecom.herokuapp.com/order/id/<order_id>               methodName : getOrderById
    get    : https://nafiul-ecom.herokuapp.com/order/                            methodName : getAllOrder
    get    : https://nafiul-ecom.herokuapp.com/order/customer-id/<customer_id>   methodName : getAllOrdersByCustomerId
    post   : https://nafiul-ecom.herokuapp.com/order/add/                         methodName : addOrder

Cart module:
    get    : https://nafiul-ecom.herokuapp.com/cart/                            methodName : getAllCart
    get    : https://nafiul-ecom.herokuapp.com/cart/id/<cart_id>                methodName : getCartById
    post   : https://nafiul-ecom.herokuapp.com/cart/add                         methodName : addCart
    put    : https://nafiul-ecom.herokuapp.com/cart/update/<cart_id>            methodName : updateCart
    delete : https://nafiul-ecom.herokuapp.com/cart/delete/<cart_id>            methodName : deleteCart

Payment module:
    get    : https://nafiul-ecom.herokuapp.com/payment/                         methodName : getAllPayments
    get    : https://nafiul-ecom.herokuapp.com/payment/add/                      methodName : addPayment

Customer module:
    get    : https://nafiul-ecom.herokuapp.com/customer/                        methodName : getAllCustomers
    get    : https://nafiul-ecom.herokuapp.com/customer/id/<customer_id>        methodName : getCustomerById
    post   : https://nafiul-ecom.herokuapp.com/customer/add/                     methodName : addCustomer
    update : https://nafiul-ecom.herokuapp.com/customer/update/                 methodName : updateCustomer deleteCustomerById
    update : https://nafiul-ecom.herokuapp.com/customer/delete/id/<customer_id> methodName : deleteCustomerById
