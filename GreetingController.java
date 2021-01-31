package batch53.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import batch53.example.beans.Customer;

@Controller
@RequestMapping("/greet")
public class GreetingController {
	@Autowired
	CustomerDAO dao;

	@GetMapping
	public String sayHello(@RequestParam(name = "name") String custName,
			@RequestParam(name = "address", required = false, defaultValue = "0") String address,
			Model model) {
		Customer c = new Customer();
		c.setName(custName);
		c.setAddress(address);
		model.addAttribute("cust", c);
		return "details";
		
	}

	@GetMapping(path = "/find")
	public String findCustomer(@RequestParam(name = "id") String ids, Model model) {
		int id = Integer.parseInt(ids);
		Customer c = dao.find(id);
		model.addAttribute("cust", c);
		return "details";

	}

	@PostMapping
	public String sayHello(@ModelAttribute Customer cust, Model model) {

		model.addAttribute("cust", cust);

		return "details";
	}

//	@GetMapping(path = "/sayHello", produces = "application/json")
//	public List<Customer> checkCustomer() {
//		// code to read all Customer records from database
//		// based on my assumption that if you get multiple records from DB
//		List<Customer> custList=new ArrayList<>();
//		custList.add(new Customer("Nitin", "12001"));
//		custList.add(new Customer("Alina", "12001"));
//		custList.add(new Customer("Mirinda", "12001"));
//		return custList;
//	}


}
