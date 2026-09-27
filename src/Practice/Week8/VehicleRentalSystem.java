package Practice.Week8;

import java.util.ArrayList;
import java.util.List;

public class VehicleRentalSystem {

    static abstract class Vehicle {
        protected String vehicleId;
        protected boolean available;

        public Vehicle(String vehicleId) {
            this.vehicleId = vehicleId;
            this.available = true;
        }

        public abstract double calculateCharge(int days);

        public String getVehicleId() {
            return vehicleId;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }

    static class Sedan extends Vehicle {
        public Sedan(String vehicleId) {
            super(vehicleId);
        }

        @Override
        public double calculateCharge(int days) {
            return days * 50;
        }
    }

    static class SUV extends Vehicle {
        public SUV(String vehicleId) {
            super(vehicleId);
        }

        @Override
        public double calculateCharge(int days) {
            return days * 80;
        }
    }

    static class Truck extends Vehicle {
        public Truck(String vehicleId) {
            super(vehicleId);
        }

        @Override
        public double calculateCharge(int days) {
            return days * 100;
        }
    }

    static class Customer {
        private String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Rental {
        private Vehicle vehicle;
        private Customer customer;
        private int days;
        private boolean active;

        public Rental(Vehicle vehicle, Customer customer, int days) {
            this.vehicle = vehicle;
            this.customer = customer;
            this.days = days;
            this.active = true;
        }

        public void returnVehicle() {
            vehicle.setAvailable(true);
            active = false;
            System.out.println(
                    vehicle.getVehicleId() + " returned by "
                            + customer.getName() + "."
            );
        }
    }

    static class RentalSystem {
        private List<Rental> rentals = new ArrayList<>();

        public void rentVehicle(Vehicle vehicle, Customer customer, int days) {

            if (!vehicle.isAvailable()) {
                System.out.println(
                        vehicle.getVehicleId() + " is currently unavailable."
                );
                return;
            }

            Rental rental = new Rental(vehicle, customer, days);
            rentals.add(rental);

            vehicle.setAvailable(false);

            System.out.println(
                    vehicle.getVehicleId()
                            + " rented successfully by "
                            + customer.getName() + "."
            );

            System.out.println(
                    "Rental charge: $"
                            + vehicle.calculateCharge(days)
            );
        }

        public void returnVehicle(Vehicle vehicle) {

            for (Rental rental : rentals) {
                if (rental.vehicle == vehicle && rental.active) {
                    rental.returnVehicle();
                    return;
                }
            }

            System.out.println(
                    vehicle.getVehicleId() + " has no active rental."
            );
        }
    }

    public static void main(String[] args) {

        RentalSystem system = new RentalSystem();

        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");

        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");

        system.rentVehicle(sedanA, customer1, 3);

        system.rentVehicle(sedanA, customer2, 2);

        system.returnVehicle(sedanA);

        system.rentVehicle(suvB, customer3, 5);
    }
}