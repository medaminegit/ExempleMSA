package fr.amaris.ExempleMSA.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import fr.amaris.ExempleMSA.model.Product;

@Repository
public class ProductDaoImp implements ProductDao {

	
	private static List<Product> produits=new ArrayList<Product>();
	static {
		produits.add(new Product(1, "produit 1", 10.1));
		produits.add(new Product(2, "produit 2", 20.2));
		produits.add(new Product(3, "produit 3", 30.3));
		produits.add(new Product(4, "produit 4", 40.4));
	}
	
	@Override
	public Product getProductByIndex(int Index)
	{
		return produits.get(Index);
	}
	
	@Override
	public List<Product>getAllProduct(){
		return produits;
	}
	
	@Override
	public Product addProduct(Product pr)
	{
		produits.add(pr);
		return pr;
	}
}
