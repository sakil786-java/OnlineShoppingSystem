package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Product;

public class ProductDAO {
	private Connection conn;

	public ProductDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean insertProduct(Product p) {
		boolean f = false;

		try {
			PreparedStatement ps = conn.prepareStatement("insert into Products(id,name,price) values(?,?,?)");
			ps.setInt(1, p.getId());
			ps.setString(2, p.getName());
			ps.setDouble(3, p.getPrice());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public boolean updateProduct(Product p) {
		boolean f = false;

		try {

			PreparedStatement ps = conn.prepareStatement("update Products set name=?,price=? where id=?");
			ps.setString(1, p.getName());
			ps.setDouble(2, p.getPrice());
			ps.setInt(3, p.getId());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public List<Product> purchaseProduct(int id) {
		List<Product> list = new ArrayList<Product>();

		Product obj = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from Products where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				obj = new Product();
				obj.setId(rs.getInt(1));
				obj.setName(rs.getString(2));
				obj.setPrice(rs.getDouble(3));
				list.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean deleteProduct(int id) {
		boolean f = false;

		try {
			PreparedStatement ps = conn.prepareStatement("delete from Products where id=?");
			ps.setInt(1, id);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public List<Product> getAllProduct() {
		List<Product> list = new ArrayList<Product>();
		Product obj = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from Products");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				obj = new Product();
				obj.setId(rs.getInt(1));
				obj.setName(rs.getString(2));
				obj.setPrice(rs.getDouble(3));
				list.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}