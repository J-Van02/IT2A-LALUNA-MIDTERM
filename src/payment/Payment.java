package payment;

import java.util.Scanner;

public class Payment {

    private final config dbConfig = new config(); 

    public void addPayment() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Student ID: ");
        String studentId = sc.next();
        System.out.print("Amount: ");
        String amount = sc.next();
        System.out.print("Payment Date: ");
        String paymentDate = sc.next();
        System.out.print("Payment Method: ");
        String paymentMethod = sc.next();
        System.out.print("Status: ");
        String status = sc.next();

        String sql = "INSERT INTO Payment (student_id, amount, payment_date, payment_method, status) VALUES (?, ?, ?, ?, ?)";

        dbConfig.addRecord(sql, studentId, amount, paymentDate, paymentMethod, status);
    }

    private void viewPayment() {
        String paymentQuery = "SELECT * FROM Payment"; 
        String[] paymentHeaders = {"Payment ID", "Student ID", "Amount", "Payment Date", "Payment Method", "Status"};
        String[] paymentColumns = {"payment_id", "student_id", "amount", "payment_date", "payment_method", "status"};

        dbConfig.viewRecords(paymentQuery, paymentHeaders, paymentColumns);
    }

    private void updatePayment() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Payment ID to Update: ");
        int paymentIdToUpdate = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Enter new Student ID: ");
        String newStudentId = sc.nextLine();
        System.out.print("Enter new Amount: ");
        double newAmount = sc.nextDouble();
        sc.nextLine(); 
        System.out.print("Enter new Payment Date (YYYY-MM-DD): ");
        String newPaymentDate = sc.nextLine();
        System.out.print("Enter new Payment Method: ");
        String newPaymentMethod = sc.nextLine();
        System.out.print("Enter new Status: ");
        String newStatus = sc.nextLine();

        String sqlUpdate = "UPDATE Payment SET student_id = ?, amount = ?, payment_date = ?, payment_method = ?, status = ? WHERE payment_id = ?";

        dbConfig.updateRecord(sqlUpdate, newStudentId, newAmount, newPaymentDate, newPaymentMethod, newStatus, paymentIdToUpdate);
    }

    private void deletePayment() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Payment ID to Delete: ");
        int paymentIdToDelete = sc.nextInt();
        sc.nextLine(); 

        String sqlDelete = "DELETE FROM Payment WHERE payment_id = ?";

        dbConfig.deleteRecord(sqlDelete, paymentIdToDelete);
    }

    public static void main(String[] args) {
        Payment payment = new Payment(); 
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=====Payment System Menu=====");
            System.out.println("=====1. Add Payment====");
            System.out.println("=====2. View Payment===");
            System.out.println("=====3. Update Payment===");
            System.out.println("=====4. Delete Payment===");
            System.out.println("=====5. Exit=======");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice) {
                case 1:
                    payment.addPayment();
                    break;
                case 2:
                    payment.viewPayment();
                    break;
                case 3:
                    payment.updatePayment();
                    break;
                case 4:
                    payment.deletePayment();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}