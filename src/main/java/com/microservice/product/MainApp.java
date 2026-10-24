package com.microservice.product;

import com.microservice.product.config.AppConfig;
import com.microservice.product.entity.Product;
import com.microservice.product.service.ProductServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        try {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

            ProductServiceImpl productService = context.getBean(ProductServiceImpl.class);

            Product p = new Product("Microservice Gateway", 149.99);
            productService.addProduct(p);

            System.out.println("Produit ajouté avec succès ! Total en base : " + productService.getAllProducts().size());

            context.close();
        } catch (Exception e) {
            System.err.println("[INFO] Connexion MySQL non disponible (" + e.getMessage() + ").");
            System.err.println("Assurez-vous que le service MySQL est démarré sur le port 3306 avec la base 'product_db'.");
            System.err.println("Note : Vous pouvez valider l'ensemble du microservice sans MySQL via 'mvn test' (H2 in-memory).");
        }
    }
}
