package fr.amaris.ExempleMSA.dao;

import java.util.List;
import java.util.Optional;

import fr.amaris.ExempleMSA.model.Product;

public interface ProductDao {

	public Product getProductByIndex(int Index);

	public List<Product> getAllProduct();

	public Product addProduct(Product pr);

}