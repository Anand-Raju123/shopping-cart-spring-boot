package com.ecom.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.model.UserDtls;
import com.ecom.repository.CategoryRepository;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		seedUsers();
		seedCategoriesAndProducts();
	}

	private void seedUsers() {
		if (!userRepository.existsByEmail("admin@gmail.com")) {
			UserDtls admin = new UserDtls();
			admin.setName("Admin User");
			admin.setEmail("admin@gmail.com");
			admin.setMobileNumber("9876543210");
			admin.setPassword(passwordEncoder.encode("admin"));
			admin.setRole("ROLE_ADMIN");
			admin.setIsEnable(true);
			admin.setAccountNonLocked(true);
			admin.setFailedAttempt(0);
			admin.setProfileImage("default.jpg");
			admin.setAddress("Admin HQ, Tech Park");
			admin.setCity("Metropolis");
			admin.setState("State Capital");
			admin.setPincode("500001");
			userRepository.save(admin);
		}

		if (!userRepository.existsByEmail("user@gmail.com")) {
			UserDtls user = new UserDtls();
			user.setName("John Doe");
			user.setEmail("user@gmail.com");
			user.setMobileNumber("9123456789");
			user.setPassword(passwordEncoder.encode("user"));
			user.setRole("ROLE_USER");
			user.setIsEnable(true);
			user.setAccountNonLocked(true);
			user.setFailedAttempt(0);
			user.setProfileImage("default.jpg");
			user.setAddress("123 Elm Street");
			user.setCity("Springfield");
			user.setState("Illinois");
			user.setPincode("62701");
			userRepository.save(user);
		}
	}

	private void seedCategoriesAndProducts() {
		if (categoryRepository.count() == 0) {
			Category c1 = createCategory("Electronics", "electronics.jpg", true);
			Category c2 = createCategory("Fashion", "fashion.jpg", true);
			Category c3 = createCategory("Home & Kitchen", "home.jpg", true);
			Category c4 = createCategory("Books & Gaming", "books.jpg", true);
			Category c5 = createCategory("Beauty & Care", "beauty.jpg", true);

			categoryRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));

			if (productRepository.count() == 0) {
				List<Product> products = Arrays.asList(
					createProduct("Ultra Wireless Noise-Cancelling Headphones", 
						"Experience crystal clear spatial sound with deep bass and 40-hour battery life. Lightweight ergonomic design with memory foam earcups.", 
						"Electronics", 14999.0, 15, 25, "headphone.jpg", true),
					
					createProduct("NextGen Smartwatch Ultra 2", 
						"Stunning AMOLED display, 100+ workout modes, continuous heart rate monitoring, SPO2 tracking, and IP68 water resistance.", 
						"Electronics", 8999.0, 20, 30, "smartwatch.jpg", true),
					
					createProduct("ProBook Slim Laptop i7 16GB", 
						"Ultra-fast performance with Intel Core i7 13th Gen, 16GB DDR5 RAM, 1TB NVMe SSD, and 14-inch 2.8K OLED Display.", 
						"Electronics", 74999.0, 10, 12, "hp laptop.jpg", true),
					
					createProduct("iPhone 14 Pro Max 256GB", 
						"Dynamic Island, 48MP Main Camera, A16 Bionic Chip, All-Day Battery Life, and Ceramic Shield front cover.", 
						"Electronics", 119999.0, 12, 10, "iphone 14.jpg", true),
					
					createProduct("Ultra HD Curved Gaming Monitor 27-inch", 
						"165Hz refresh rate, 1ms response time, HDR400, AMD FreeSync Premium, frameless ergonomic design.", 
						"Electronics", 24999.0, 8, 15, "monitor.jpg", true),
					
					createProduct("Classic Leather Biker Jacket", 
						"Premium genuine leather jacket with quilted lining, heavy-duty metallic zippers, and tailored modern fit.", 
						"Fashion", 5999.0, 18, 40, "jacket.jpg", true),
					
					createProduct("Urban Performance Running Sneakers", 
						"Breathable mesh upper with cushioned responsive foam midsole for ultimate running comfort and traction grip.", 
						"Fashion", 3499.0, 25, 20, "sneakers.jpg", true),
					
					createProduct("Men's Classic Slim Fit Blue Cotton Shirt", 
						"100% Breathable Premium Cotton, Spread Collar, Buttoned Cuffs, Versatile Casual & Formal Wear.", 
						"Fashion", 1999.0, 30, 25, "blue shirt.jfif", true),
					
					createProduct("Men's Urban Denim Blue Jeans", 
						"Stretchable Durable Denim, Straight Leg Slim Fit, 5-Pocket Design, Deep Blue Vintage Wash.", 
						"Fashion", 2799.0, 22, 20, "jeans blue.jfif", true),
					
					createProduct("Women's Designer Embroidered Anarkali Kurti", 
						"Handcrafted Thread Embroidery, Rayon Fabric, Flowy Anarkali Silhouette for Festive & Ethnic Wear.", 
						"Fashion", 3299.0, 15, 30, "kruti.jfif", true),
					
					createProduct("Automatic Espresso & Coffee Machine", 
						"15-bar Italian pressure pump, integrated milk frother, touchscreen controls for cappuccino, latte, and espresso.", 
						"Home & Kitchen", 18999.0, 12, 15, "coffee_maker.jpg", true),
					
					createProduct("Ergonomic Mesh Executive Gaming Chair", 
						"4D adjustable armrests, lumbar support system, breathable mesh back, 135-degree recline mechanism.", 
						"Home & Kitchen", 12499.0, 8, 18, "gaming_chair.jpg", true),
					
					createProduct("Multi-Function Heavy Duty Mixer Grinder 750W", 
						"3 Stainless Steel Jars, 750W Copper Motor, 3-Speed Control with Pulse, Overload Protection.", 
						"Home & Kitchen", 4499.0, 16, 20, "grinder.jpg", true),
					
					createProduct("Double Door Frost Free Refrigerator 340L", 
						"Digital Inverter Technology, Convertible 5-in-1 modes, Twin Cooling Plus, Energy Star Efficiency.", 
						"Home & Kitchen", 36999.0, 6, 10, "fridge.png", true),
					
					createProduct("Cyberpunk RPG Deluxe Edition (PS5 / PC)", 
						"Immersive open-world futuristic RPG with ray tracing graphics, expanded story DLC, and collector artbook.", 
						"Books & Gaming", 3999.0, 50, 10, "game.jpg", true)
				);

				productRepository.saveAll(products);
			}
		}
	}

	private Category createCategory(String name, String imageName, Boolean isActive) {
		Category category = new Category();
		category.setName(name);
		category.setImageName(imageName);
		category.setIsActive(isActive);
		return category;
	}

	private Product createProduct(String title, String description, String category, Double price, Integer stock, Integer discount, String image, Boolean isActive) {
		Product product = new Product();
		product.setTitle(title);
		product.setDescription(description);
		product.setCategory(category);
		product.setPrice(price);
		product.setStock(stock);
		product.setDiscount(discount);
		double discountAmount = price * (discount / 100.0);
		product.setDiscountPrice(price - discountAmount);
		product.setImage(image);
		product.setIsActive(isActive);
		return product;
	}
}
