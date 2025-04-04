
class OrderedItem extends Customer {
    private int order_id;
    private int customer_id;
    private String item_name;
    private int quantity;
    private double total_price;
    private String order_date;
    private int overall_total;

    public OrderedItem(int order_id, int customer_id, String item_name, int quantity, double total_price,
            String order_date,
            int overall_total) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.item_name = item_name;
        this.quantity = quantity;
        this.total_price = total_price;
        this.order_date = order_date;
        this.overall_total = overall_total;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal_price() {
        return total_price;
    }

    public String getOrder_date() {
        return order_date;
    }

    public int getOverall_total() {
        return overall_total;
    }

}