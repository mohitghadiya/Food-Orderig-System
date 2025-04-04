
class CartItem extends Customer {
    private int itemId;
    private int restaurantId;
    private int categoryId;
    private String Restaurant_name;
    private String Category_name;
    private String Item_name;
    private double Item_price;
    private int quantity;
    private int Restaurant_rating;

    public CartItem(int itemId, int restaurantId, int categoryId, String Restaurant_name, String Category_name,
            String Item_name, double Item_price, int quantity, int Restaurant_rating) {
        this.itemId = itemId;
        this.restaurantId = restaurantId;
        this.categoryId = categoryId;
        this.Restaurant_name = Restaurant_name;
        this.Category_name = Category_name;
        this.Item_name = Item_name;
        this.Item_price = Item_price;
        this.quantity = quantity;
        this.Restaurant_rating = Restaurant_rating;
    }

    public int getItemId() {
        return itemId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getRestaurant_name() {
        return Restaurant_name;
    }

    public String getCategory_name() {
        return Category_name;
    }

    public String getItem_name() {
        return Item_name;
    }

    public double getItem_price() {
        return Item_price;
    }

    public int getRestaurant_rating() {
        return Restaurant_rating;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int newquantity) {
        this.quantity = newquantity;
    }
}