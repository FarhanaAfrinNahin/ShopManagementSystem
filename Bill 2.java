import java.util.ArrayList;
import java.util.List;

class Bill {
	private int billNumber;
	private String customerName;
	private List<Product> products;
	private double totalPrice;

	public Bill(int billNumber, String customerName) {
		this.billNumber = billNumber;
		this.customerName = customerName;
		this.products = new ArrayList<>();
		this.totalPrice = 0;
	}

	public int getBillNumber() {
		return billNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public List<Product> getProducts() {
		return products;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void addProduct(Product product) {
		products.add(product);
		totalPrice += product.getPrice() - product.getDiscount();
	}

	public void setTotalPrice(double totalPrice2) {
		this.totalPrice = totalPrice;
		
	}

}