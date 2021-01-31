package batch53.example;

import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import batch53.example.beans.Customer;

@Repository
public class CustomerDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Customer find(int id) {
		Customer c = new Customer();
		ListIterator<Customer> list = jdbcTemplate.query("SELECT * FROM CUSTOMER WHERE CUST_ID =?",
				new Object[] { id },
				(rs, rowNum) -> new Customer(rs.getInt("CUST_ID"), rs.getString("cust_name"), rs.getString("address")))
				.listIterator();
		if (list.hasNext()) {
			c = list.next();
		}
		return c;
	}
}
