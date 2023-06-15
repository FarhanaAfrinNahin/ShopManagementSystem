import java.util.*;

public class GroceryStoreManagementSystemDemo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		GroceryStoreManagementSystem system = new GroceryStoreManagementSystem();
		system.addManager("manager", "managerpassword");

		if (system.managerLogin("manager", "managerpassword")) {
			System.out.print("Enter employee name: ");
			String employeeName = scanner.nextLine();
			system.addEmployee(employeeName);

			System.out.print("Enter new password for employee: ");
			String newPassword = scanner.nextLine();
			system.editEmployee(employeeName, newPassword);

			System.out.print("Enter product name: ");
			String productName = scanner.nextLine();
			System.out.print("Enter price of the product: ");
			double price = scanner.nextDouble();
			System.out.print("Enter discount on the product: ");
			double discount = scanner.nextDouble();
			system.addProduct(productName, price, discount);

			System.out.print("Enter new price for the product: ");
			double newPrice = scanner.nextDouble();
			System.out.print("Enter new discount for the product: ");
			double newDiscount = scanner.nextDouble();
			system.editProduct(productName, newPrice, newDiscount);

			System.out.print("Enter product name to delete: ");
			String productToDelete = scanner.nextLine();
			system.deleteProduct(productToDelete);

			System.out.print("Enter employee name to control salary: ");
			String employeeToControlSalary = scanner.nextLine();
			System.out.print("Enter new salary for the employee: ");
			double newSalary = scanner.nextDouble();
			system.controlSalary(employeeToControlSalary, newSalary);
		}

		System.out.print("Enter employee name to update password: ");
		String employeeToUpdatePassword = scanner.nextLine();
		System.out.print("Enter new password for the employee: ");
		String newPassword = scanner.nextLine();
		system.employeeUpdatePassword(employeeToUpdatePassword, newPassword);

		System.out.print("Enter product name: ");
		String productName = scanner.nextLine();
		System.out.print("Enter employee name to add product details: ");
		String employeeName = scanner.nextLine();
		system.addProductDetails(productName, employeeName);

		System.out.print("Enter product name to sell: ");
		String productToSell = scanner.nextLine();
		System.out.print("Enter customer name: ");
		String customerName = scanner.nextLine();
		system.sellProduct(productToSell, customerName);

		System.out.print("Enter bill number to search: ");
		int billNumber = scanner.nextInt();
		system.searchBillInformation(billNumber);

		System.out.print("Enter product name to get information: ");
		String productInfoName = scanner.nextLine();
		System.out.print("Enter customer ID: ");
		String customerId = scanner.nextLine();
		system.getProductInformation(productInfoName, customerId);

		System.out.print("Enter product name to get price: ");
		String productPriceName = scanner.nextLine();
		system.getProductPrice(productPriceName);

		System.out.print("Enter product name to get discount: ");
		String productDiscountName = scanner.nextLine();
		system.getProductDiscount(productDiscountName);

		scanner.close();
	}
}
