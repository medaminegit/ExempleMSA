package fr.amaris.ExempleMSA.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import fr.amaris.ExempleMSA.exception.ProductNotFoundException;
import fr.amaris.ExempleMSA.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "gestion des produits")
public class ProductController {
	
	@Autowired
	private ProductDao productDao;
	
	@ApiOperation("Rechercher tous les produits")
	@RequestMapping(value = "/produits",method = RequestMethod.GET)
	public List<Product>afficherTousProduits() {
		return productDao.findAll();
	}

	
	@ApiOperation("Recuperer un produit par son index utiliser dans la version anterieur ou il y avait pas de connexion avec jpa")
	@GetMapping(value = "/produit/{index}")
	public Product afficheByIndex(@PathVariable int index) {
		return productDao.findById(index);
	}
	@ApiOperation("recuperer un produit pas son id")
	@RequestMapping(value = "/produits/{id}",method = RequestMethod.GET)
	public Product afficheById(@PathVariable int id) throws ProductNotFoundException
	{
		Product pr=productDao.findById(id);
		if(pr==null) throw new ProductNotFoundException("id du produit saisi en url n'existe pas");
		return pr;
	}
	@ApiOperation("Ajouter un produit")
	@RequestMapping(value ="/addproduit",method = RequestMethod.POST)
	public ResponseEntity<Void>  ajouterProduit(@Valid @RequestBody Product pr) throws ProductNotFoundException
	{
		System.out.println("in ajouter produit ");
		System.out.println("produit avant ajout "+pr.toString());
		Product pr_add=productDao.save(pr);
		System.out.println("produit apres ajout "+pr_add.toString());
		if(pr_add!=null) {
			System.out.println("in add product not null");
		URI location=ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{index}").buildAndExpand(pr_add.getId()).toUri();
		return ResponseEntity.created(location).build();
		}
		
		if(pr_add==null) throw new ProductNotFoundException("le produit n'est pas ajouté");
		{
			System.out.println("in add product null");
			return ResponseEntity.noContent().build();
		}
		}
	@ApiOperation("Rechercher tous les produits ayant le prix superieur à la valeur passer en entrer")
	@GetMapping(value="/produits/limit_prix/{price}")
	public List<Product> getMaxpricethan(@PathVariable double price)
	{
		return productDao.findByPriceGreaterThan(price);
	}
	@ApiOperation("recuperer les produits par leur nom")
	@GetMapping(value="/produits/search/{name}")
	public List<Product> searchByName(@PathVariable String name)
	{
		return productDao.findbyname(name);
	}
}
