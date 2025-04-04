class Stack {

    private static int top = -1;
    private static int maxSize = 100;
    private static OrderedItem[] stackArr = new OrderedItem[maxSize];
    static int order_no = 1;

    public static void push(OrderedItem order) {
        if (top < maxSize - 1) {
            stackArr[++top] = order;
        } else {
            System.out.println("Stack is full. Cannot add more orders.");
        }
    }

    public OrderedItem pop() {
        if (top >= 0) {
            return stackArr[top--];
        } else {
            System.out.println("Stack is empty.");
            return null;
        }
    }

    public void peek() {
        try {
            if (top >= 0) {
                System.out.println(" item_name = "
                        + stackArr[top].getItem_name() + " , Quantity = " + stackArr[top].getQuantity()
                        + " , Total_price = " + stackArr[top].getTotal_price() + " , Order_date = "
                        + stackArr[top].getOrder_date() + " , Overall_total = " + stackArr[top].getOverall_total());
            } else {
                System.out.println("Stack is empty.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public void display() {
        if (top >= 0) {
            for (int i = top; i >= 0; i--) {
                System.out.println("Order_no = " + order_no + " , item_name = "
                        + stackArr[i].getItem_name() + " , Quantity = " + stackArr[i].getQuantity()
                        + " , Total_price = " + stackArr[i].getTotal_price() + " , Order_date = "
                        + stackArr[i].getOrder_date() + " , Overall_total = " + stackArr[i].getOverall_total());
                order_no++;
            }
        } else {
            System.out.println("Stack is empty");
        }

    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }
}