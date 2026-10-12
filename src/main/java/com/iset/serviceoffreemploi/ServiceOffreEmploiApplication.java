
package com.iset.serviceoffreemploi;

import com.iset.serviceoffreemploi.dao.OffreRepository;
import com.iset.serviceoffreemploi.entities.Offre;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceOffreEmploiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOffreEmploiApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(OffreRepository repository) {
        return args -> {
            repository.save(new Offre("Web Design", "informatique", "AXA", 2, "France"));
            repository.save(new Offre("Developpeur", "informatique", "Talys", 3, "Tunisie"));
            repository.save(new Offre("Architecte", "informatique", "SIS", 2, "Allemagne"));
        };
    }
}
