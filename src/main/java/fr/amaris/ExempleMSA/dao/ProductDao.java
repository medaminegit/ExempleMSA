package fr.amaris.ExempleMSA.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.amaris.ExempleMSA.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>{

	public Product findById(int Index);

	public List<Product> findAll();

	public Product save(Product pr);
	
	public List<Product>findByPriceGreaterThan(double MaxPrice);
	
	@Query("SELECT id , name,price FROM Product p where p.name like  CONCAT('%',:nom,'%')")
	public List<Product> findbyname(@Param("nom")String nom);

}