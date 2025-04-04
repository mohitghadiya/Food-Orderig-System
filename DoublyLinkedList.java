class DoublyLinkedList {
    static class Node {
        CartItem data;
        Node next;
        Node prev;

        Node(CartItem cartItem) {
            this.data = cartItem;
            this.next = null;
            this.prev = null;
        }

    }

    static Node head = null;

    void addLast(CartItem cartItem) {
        Node n = new Node(cartItem);
        if (head == null) {
            head = n;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = n;
            n.prev = current;
        }
    }

    public void remove(int itemNumber) {
        Node temp = head;
        int currentPosition = 1;
        if (temp == null) {
            System.out.println("Item number " + itemNumber + " does not exist.");
            return;
        }
        while (temp != null && currentPosition < itemNumber) {
            temp = temp.next;
            currentPosition++;
        }
        if (temp == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
        } else {
            if (temp.prev != null) {
                temp.prev.next = temp.next;
            }
            if (temp.next != null) {
                temp.next.prev = temp.prev;
            }
        }

        System.out.println("Cart number " + itemNumber + " has been removed from the cart.");
    }

    static public void removeAllData() {
        head = null;
    }

    static void printList() {
        Node current = head;
        while (current != null) {
            System.out.println(" Item_ID: " + current.data.getItemId() + ", Restaurant_ID: "
                    + current.data.getRestaurantId() + ", Category_ID: " + current.data.getCategoryId()
                    + ", Restaurant_Name: "
                    + current.data.getRestaurant_name() + ", Category_Name: " + current.data.getCategory_name()
                    + ", Item_Name: "
                    + current.data.getItem_name() + ", Item_Price: " + current.data.getItem_price()
                    + ", Item_Quantity: " + current.data.getQuantity()
                    + ", Restaurant_Rating: "
                    + current.data.getRestaurant_rating());
            current = current.next;
        }
    }
}
