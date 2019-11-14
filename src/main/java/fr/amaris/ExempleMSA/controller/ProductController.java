package fr.amaris.ExempleMSA.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

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
	public ResponseEntity<Void>  ajouterProduit(@RequestBody Product pr)
	{
		Product pr_add=productDao.addProduct(pr);
		if(pr_add==null)
			return ResponseEntity.noContent().build();
		
		URI location=ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{index}").
				buildAndExpand(pr_add.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
