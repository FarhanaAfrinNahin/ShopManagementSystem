import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class GroceryStoreManagementSystem {
	private HashMap<String, String> managerLogin;
	private HashMap<String, Employee> employees;
	private ArrayList<Product> products;
	private ArrayList<Bill> bills;
	private int billNumberCounter;

	public GroceryStoreManagementSystem() {
		this.managerLogin = new HashMap<>();
		this.employees = new HashMap<>();
		this.products = new ArrayList<>();
		this.bills = new ArrayList<>();
		this.billNumberCounter = 1;
	}

	public void addManager(String username, String password) {
		managerLogin.put(username, password);
	}

	public boolean managerLogin(String username, String password) {
		return managerLogin.containsKey(username) && managerLogin.get(username).equals(password);
	}

	public void addEmployee(String name) {
		String password = generateRandomPassword();
		Employee employee = new Employee(name, password);
		employees.put(name, employee);
		System.out.println("Employee added successfully. Password: " + password);
	}

	public void editEmployee(String name, String newPassword) {
		if (employees.containsKey(name)) {
			Employee employee = employees.get(name);
			employee.setPassword(newPassword);
			System.out.println("Employee password updated successfully.");
		} else {
			System.out.println("Employee not found.");
		}
	}

	public void addProduct(String name, double price, double discount) {
		Product product = new Product(name, price, discount);
		products.add(product);
		System.out.println("Product added successfully.");
	}

	public void editProduct(String name, double newPrice, double newDiscount) {
		for (Product product : products) {
			if (product.getName().equals(name)) {
				product.setPrice(newPrice);
				product.setDiscount(newDiscount);
				System.out.println("Product updated successfully.");
				return;
			}
		}
		System.out.println("Product not found.");
	}

	public void controlSalary(String name, double newSalary) {
		if (employees.containsKey(name)) {
			System.out.println("Salary updated successfully.");
		} else {
			System.out.println("Employee not found.");
		}
	}

	public void employeeUpdatePassword(String name, String newPassword) {
		if (employees.containsKey(name)) {
			Employee employee = employees.get(name);
			employee.setPassword(newPassword);
			System.out.println("Password updated successfully.");
		} else {
			System.out.println("Employee not found.");
		}
	}

	public void addProductDetails(String name, String employeeName) {
		if (employees.containsKey(employeeName)) {
			System.out.println("Product details added successfully.");
		} else {
			System.out.println("Employee not found.");
		}
	}

	public void sellProduct(String productName, String customerName) {
		if (findProductByName(productName) != null) {
			Bill bill = new Bill(billNumberCounter++, customerName);
			Product product = findProductByName(productName);
			bill.addProduct(product);
			bills.add(bill);
			System.out.println("Product sold successfully. Bill number: " + bill.getBillNumber());
		} else {
			System.out.println("Product not found.");
		}
	}

	public void searchBillInformation(int billNumber) {
		for (Bill bill : bills) {
			if (bill.getBillNumber() == billNumber) {
				System.out.println("Bill Number: " + bill.getBillNumber());
				System.out.println("Customer Name: " + bill.getCustomerName());
				System.out.println("Products:");
				for (Product product : bill.getProducts()) {
					System.out.println("  - " + product.getName() + " - Price: $" + product.getPrice() + ", Discount: $"
							+ product.getDiscount());
				}
				System.out.println("Total Price: $" + bill.getTotalPrice());
				return;
			}
		}
		System.out.println("Bill not found.");
	}

	public void getProductInformation(String productName, String customerId) {
		if (findProductByName(productName) != null) {
			System.out.println("Product Information:");
			System.out.println("Name: " + findProductByName(productName).getName());
			System.out.println("Price: $" + findProductByName(productName).getPrice());
			System.out.println("Discount: $" + findProductByName(productName).getDiscount());
		} else {
			System.out.println("Product not found.");
		}
	}

	public void getProductPrice(String productName) {
		if (findProductByName(productName) != null) {
			System.out.println("Price of " + productName + ": $" + findProductByName(productName).getPrice());
		} else {
			System.out.println("Product not found.");
		}
	}

	public void getProductDiscount(String productName) {
		if (findProductByName(productName) != null) {
			System.out.println("Discount of " + productName + ": $" + findProductByName(productName).getDiscount());
		} else {
			System.out.println("Product not found.");
		}
	}

	private Product findProductByName(String name) {
		for (Product product : products) {
			if (product.getName().equals(name)) {
				return product;
			}
		}
		return null;
	}

	private String generateRandomPassword() {
		return "randompassword123";
	}

	public void saveDataToFile(String filename) {
		try {
			FileWriter writer = new FileWriter(filename);
			writer.write("Manager Login:\n");
			for (Map.Entry<String, String> entry : managerLogin.entrySet()) {
				writer.write(entry.getKey() + "," + entry.getValue() + "\n");
			}

			writer.write("\nEmployees:\n");
			for (Map.Entry<String, Employee> entry : employees.entrySet()) {
				writer.write(entry.getKey() + "," + entry.getValue().getPassword() + "\n");
			}

			writer.write("\nProducts:\n");
			for (Product product : products) {
				writer.write(product.getName() + "," + product.getPrice() + "," + product.getDiscount() + "\n");
			}

			writer.write("\nBills:\n");
			for (Bill bill : bills) {
				writer.write(bill.getBillNumber() + "," + bill.getCustomerName() + "," + bill.getTotalPrice() + "\n");
				for (Product product : bill.getProducts()) {
					writer.write(product.getName() + "," + product.getPrice() + "," + product.getDiscount() + "\n");
				}
				writer.write("END\n");
			}

			writer.close();
			System.out.println("Data saved to file: " + filename);
		} catch (IOException e) {
			System.out.println("Error saving data to file: " + e.getMessage());
		}
	}

	public void loadDataFromFile(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);

			String section = "";
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.startsWith("Manager Login:")) {
					section = "Manager Login";
				} else if (line.startsWith("Employees:")) {
					section = "Employees";
				} else if (line.startsWith("Products:")) {
					section = "Products";
				} else if (line.startsWith("Bills:")) {
					section = "Bills";
				} else if (line.equals("END")) {
					section = "";
				} else {
					switch (section) {
					case "Manager Login":
						String[] managerData = line.split(",");
						managerLogin.put(managerData[0], managerData[1]);
						break;
					case "Employees":
						String[] employeeData = line.split(",");
						Employee employee = new Employee(employeeData[0], employeeData[1]);
						employees.put(employeeData[0], employee);
						break;
					case "Products":
						String[] productData = line.split(",");
						Product product = new Product(productData[0], Double.parseDouble(productData[1]),
								Double.parseDouble(productData[2]));
						products.add(product);
						break;
					case "Bills":
						if (line.equals("")) {
							break;
						}
						String[] billData = line.split(",");
						int billNumber = Integer.parseInt(billData[0]);
						String customerName = billData[1];
						double totalPrice = Double.parseDouble(billData[2]);
						Bill bill = new Bill(billNumber, customerName);
						bill.setTotalPrice(totalPrice);
						while (scanner.hasNextLine()) {
							line = scanner.nextLine();
							if (line.equals("END")) {
								break;
							}
							String[] productDetails = line.split(",");
							Product billProduct = new Product(productDetails[0], Double.parseDouble(productDetails[1]),
									Double.parseDouble(productDetails[2]));
							bill.addProduct(billProduct);
						}
						bills.add(bill);
						break;
					}
				}
			}
			scanner.close();
			System.out.println("Data loaded from file: " + filename);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + filename);
		}
	}

	public void deleteProduct(String name) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getName().equals(name)) {
				products.remove(i);
				System.out.println("Product deleted successfully.");
				return;
			}
		}
		System.out.println("Product not found.");
	}
}