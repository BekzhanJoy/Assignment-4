
interface OrderState {
    void payOrder();
    void shipOrder();
    void deliverOrder();
    void cancelOrder();
}

class NewOrderState implements OrderState {
    private Order order;

    public NewOrderState(Order order) {
        this.order = order;
    }

    @Override
    public void payOrder() {
        System.out.println("Order paid.");
        order.setState(order.getPaidState());
    }

    @Override
    public void shipOrder() {
        System.out.println("Cannot ship an unpaid order.");
    }

    @Override
    public void deliverOrder() {
        System.out.println("Cannot deliver an unpaid order.");
    }

    @Override
    public void cancelOrder() {
        System.out.println("Order canceled.");
        order.setState(order.getCanceledState());
    }
}

class Order {
    private OrderState newState;
    private OrderState paidState;
    private OrderState shippedState;
    private OrderState deliveredState;
    private OrderState canceledState;

    private OrderState currentState;

    public Order() {
        newState = new NewOrderState(this);
        paidState = new PaidOrderState(this);
        shippedState = new ShippedOrderState(this);
        deliveredState = new DeliveredOrderState(this);
        canceledState = new CanceledOrderState(this);
        currentState = newState;
    }

    public void setState(OrderState state) {
        this.currentState = state;
    }

    public OrderState getNewState() {
        return newState;
    }

    public OrderState getPaidState() {
        return paidState;
    }

    public OrderState getShippedState() {
        return shippedState;
    }

    public OrderState getDeliveredState() {
        return deliveredState;
    }

    public OrderState getCanceledState() {
        return canceledState;
    }

    public void payOrder() {
        currentState.payOrder();
    }

    public void shipOrder() {
        currentState.shipOrder();
    }

    public void deliverOrder() {
        currentState.deliverOrder();
    }

    public void cancelOrder() {
        currentState.cancelOrder();
    }
}

public class Main {
    public static void main(String[] args) {
        Order order = new Order();

        order.payOrder();
        order.shipOrder();
        order.deliverOrder();
    }
}
