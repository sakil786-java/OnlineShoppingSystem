package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import conn.DBConnect;
import dao.ProductDAO;
import entity.Product;

public class MainClass {

	public static void main(String[] args) throws IOException {
		boolean x = true;
		while (x) {
			System.out.println("*******Online Shoping Center*******");
			System.out.println("----------------------");
			System.out.println("Choose option!!!");
			System.out.println("1. ADMIN VIEW");
			System.out.println("2. Customer VIEW");
			System.out.println("3. EXIT ");

			Scanner sc1 = new Scanner(System.in);
			System.out.println("Enter No: ");
			int no1 = sc1.nextInt();
			switch (no1) {
			case 1:
				boolean f = true;
				System.out.println("*******Online Shoping Center*******");
				while (f) {
					System.out.println("----------------------");
					System.out.println("ADMIN VIEW");
					System.out.println("1. Insert Product");
					System.out.println("2. Update Product");
					System.out.println("3. Delete Product");
					System.out.println("4. View All Product");
					System.out.println("5. Exit");
					System.out.println("----------------------");
					Scanner sc = new Scanner(System.in);
					System.out.println("Enter No : ");
					int no = sc.nextInt();

					ProductDAO dao = new ProductDAO(DBConnect.getConn());

					switch (no) {
					case 1:
						System.out.println("Enter Id: ");
						int id = sc.nextInt();
						System.out.println("Enter Name : ");
						String name = sc.next();
						System.out.println("Enter Price : ");
						double price = sc.nextDouble();

						Product p = new Product();
						p.setId(id);
						p.setName(name);
						p.setPrice(price);

						boolean s1 = dao.insertProduct(p);

						if (s1) {
							System.out.println("Product Added Successfully !!!");
						} else {
							System.out.println("Something wrong..");
						}
						break;

					case 2:
						System.out.println("Enter Id: ");
						int id2 = sc.nextInt();
						System.out.println("Enter Name: ");
						String name2 = sc.next();
						System.out.println("Enter Price: ");
						double price2 = sc.nextDouble();

						Product p2 = new Product();
						p2.setId(id2);
						p2.setName(name2);
						p2.setPrice(price2);

						boolean s2 = dao.updateProduct(p2);

						if (s2) {
							System.out.println("Product list Edited Sucessfully..");
						} else {
							System.out.println("User Is not Available");
						}

						break;
					case 3:

						System.out.println("Enter Id: ");
						int id3 = sc.nextInt();

						boolean s3 = dao.deleteProduct(id3);

						if (s3) {
							System.out.println("Product Delete Sucessfully..");
						} else {
							System.out.println("User Is not Available");
						}

						break;
					case 4:

						List<Product> list = dao.getAllProduct();

						if (list.isEmpty()) {
							System.out.println("Product is Not Available");
						} else {

							for (Product con : list) {
								System.out.println("Id: " + con.getId());
								System.out.println("Name: " + con.getName());
								System.out.println("Price: " + con.getPrice());
								System.out.println("---------------------");
							}
						}
						break;
					case 5:
						f = false;
						System.out.println("Thank u..Outside of admin view");
						break;

					default:
						System.out.println("Please Enter Correct No..");
						break;
					}

				}

				break;

			case 2:
				boolean g = true;
				System.out.println("*******Online Shoping Center*******");
				while (g) {
					System.out.println("----------------------");
					System.out.println("Customer VIEW");
					System.out.println("1. Purchase Product");
					System.out.println("2. View All Product");
					System.out.println("3. Bill");
					System.out.println("4. Exit");
					System.out.println("----------------------");
					Scanner sc = new Scanner(System.in);
					System.out.println("Enter No: ");
					int no = sc.nextInt();

					ProductDAO dao = new ProductDAO(DBConnect.getConn());
					// Edit
					switch (no) {
					case 1:
						String billPath = "E:\\OSProject\\src\\Bill.txt";
						System.out.println("Enter customer name: ");
						String customerName = sc.next();

						System.out.println("how many product: ");
						int number = sc.nextInt();
						List<Object> data = new LinkedList<>();
						try (FileWriter fWriter = new FileWriter(billPath)) {
							fWriter.write("Customer Name: " + customerName);
							fWriter.write("\n");
							for (int i = 0; i < number; i++) {
								System.out.println("for purchase Enter product Id :");
								int id = sc.nextInt();

								List<Product> list1 = dao.purchaseProduct(id);

								if (list1.isEmpty()) {
									System.out.println("Product is Not Available");
								} else {

									for (Product con : list1) {
										int id1 = con.getId();
										String name = con.getName();
										Double price = con.getPrice();
										data.add("Product ID: " + id1 + ", Product Name: " + name + ", Product Price: "
												+ price + " \n");
										// data.add("");

									}
								}

							}
							fWriter.write(data.toString());
						}
						System.out.println("Thanks for Shopping bill will generate shortly!!! ");
						break;

					case 2:

						List<Product> list = dao.getAllProduct();

						if (list.isEmpty()) {
							System.out.println("Product is Not Available");
						} else {

							for (Product con : list) {
								System.out.println("Id: " + con.getId());
								System.out.println("Name: " + con.getName());
								System.out.println("Price: " + con.getPrice());

							}
							System.out.println("---------------------");
						}
						break;

					case 3:

						String billPath1 = "E:\\OSProject\\src\\Bill.txt";
						File file = new File(billPath1);
						BufferedReader brBufferedReader = new BufferedReader(new FileReader(file));
						String string;
						if ((string = brBufferedReader.readLine()) == null) {
							System.out.println("No transaction so ,Bill is not generate yet");
						} else {
							System.out.println("**** Bill ****!!!!.");
							while ((string = brBufferedReader.readLine()) != null) {
								System.out.println(string);
							}

						}

						break;
					case 4:
						g = false;
						System.out.println("Thank u.");
						break;
					default:
						System.out.println("Please Enter Correct No..");
						break;

					}

				}
				break;

			case 3:
				x = false;
				System.out.println("Thank u.");
				break;
			default:
				System.out.println("Please Enter Correct No..");
				break;

			}

		}

	}
}