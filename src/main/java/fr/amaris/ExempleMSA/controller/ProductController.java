package fr.amaris.ExempleMSA.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.amaris.ExempleMSA.dao.ProductDao;
import fr.amaris.ExempleMSA.model.Product;

@RestController
public class ProductController {
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping(value = "/produits",method = RequestMethod.GET)
	public List<Product>afficherTousProduits() {
		return productDao.getAllProduct();
	}

	

	@GetMapping(value = "/produit/{index}")
	public Product afficheByIndex(@PathVariable int index) {
		return productDao.getProductByIndex(index);
	}
	
	@RequestMapping(value ="/addproduit",method = RequestMethod.POST)
	public void ajouterProduit(@RequestBody Product pr)
	{
		productDao.addProduct(pr);
	}
}
