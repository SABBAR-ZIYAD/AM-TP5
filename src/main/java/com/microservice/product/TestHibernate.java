package com.microservice.product;

import com.microservice.product.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestHibernate {
    public static void main(String[] args) {
        try {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
            System.out.println("Contexte Spring initialisé avec succès !");
            context.close();
        } catch (Exception e) {
            System.err.println("[INFO] Erreur lors de l'initialisation du contexte (" + e.getMessage() + ").");
            System.err.println("Assurez-vous que le serveur MySQL est démarré ou utilisez les tests unitaires H2 ('mvn test').");
        }
    }
}
