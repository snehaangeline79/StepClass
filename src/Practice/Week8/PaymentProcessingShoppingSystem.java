package Practice.Week8;

import java.util.ArrayList;
import java.util.List;

public class PaymentProcessingShoppingSystem {

    static class Customer {

        private String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Product {

        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    static class OrderItem {

        private Product product;
        private int quantity;

        public OrderItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public double getTotal() {
            return product.getPrice() * quantity;
        }
    }

    interface PaymentMethod {
        boolean processPayment(double amount);
        String getMethodName();
    }

    static class CreditCardPayment implements PaymentMethod {

        @Override
        public boolean processPayment(double amount) {
            return true;
        }

        @Override
        public String getMethodName() {
            return "Credit Card";
        }
    }

    static class PayPalPayment implements PaymentMethod {

        @Override
        public boolean processPayment(double amount) {
            return false;
        }

        @Override
        public String getMethodName() {
            return "PayPal";
        }
    }

    static class BankTransferPayment implements PaymentMethod {

        @Override
        public boolean processPayment(double amount) {
            return true;
        }

        @Override
        public String getMethodName() {
            return "Bank Transfer";
        }
    }

    static class Order {

        enum Status {
            PENDING,
            PAID
        }

        private String orderId;
        private Customer customer;
        private List<OrderItem> items;
        private Status status;

        public Order(String orderId, Customer customer) {
            this.orderId = orderId;
            this.customer = customer;
            this.items = new ArrayList<>();
            this.status = Status.PENDING;
        }

        public void addProduct(
                Product product,
                int quantity) {

            if (quantity <= 0) {
                System.out.println(
                        "Quantity must be greater than zero."
                );
                return;
            }

            items.add(
                    new OrderItem(product, quantity)
            );
        }

        public boolean isEmpty() {
            return items.isEmpty();
        }

        public double calculateTotal() {

            double total = 0;

            for (OrderItem item : items) {
                total += item.getTotal();
            }

            return total;
        }

        public void pay(PaymentMethod paymentMethod) {

            if (isEmpty()) {
                System.out.println(
                        "Cannot process payment for an empty order."
                );
                return;
            }

            if (status == Status.PAID) {
                System.out.println(
                        "Order is already paid."
                );
                return;
            }

            System.out.println(
                    "Payment initiated via "
                            + paymentMethod.getMethodName()
                            + " for Order "
                            + orderId + "."
            );

            boolean success =
                    paymentMethod.processPayment(
                            calculateTotal()
                    );

            if (success) {

                status = Status.PAID;

                System.out.println(
                        "Payment for Order "
                                + orderId
                                + " successful."
                );

            } else {

                System.out.println(
                        "Payment for Order "
                                + orderId
                                + " failed."
                );
            }

            System.out.println(
                    "Order status: " + status
            );
        }
    }

    public static void main(String[] args) {

        Customer customerX =
                new Customer("Customer X");

        Customer customerY =
                new Customer("Customer Y");

        Customer customerZ =
                new Customer("Customer Z");

        Product productA =
                new Product("Product A", 50);

        Product productB =
                new Product("Product B", 30);

        Product productC =
                new Product("Product C", 100);

        Order orderX =
                new Order("X", customerX);

        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);

        System.out.println(
                "Order created for "
                        + customerX.getName() + "."
        );

        orderX.pay(
                new CreditCardPayment()
        );

        Order orderY =
                new Order("Y", customerY);

        System.out.println(
                "Order created for "
                        + customerY.getName() + "."
        );

        orderY.pay(
                new CreditCardPayment()
        );

        Order orderZ =
                new Order("Z", customerZ);

        orderZ.addProduct(productC, 1);

        System.out.println(
                "Order created for "
                        + customerZ.getName() + "."
        );

        orderZ.pay(
                new PayPalPayment()
        );
    }
}