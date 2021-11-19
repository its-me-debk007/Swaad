data class Order(
    val address: String,
    val customer: Customer,
    val id: Int,
    val order_details: List<OrderDetail>,
    val restaurant: Restaurant,
    val total: Int
)