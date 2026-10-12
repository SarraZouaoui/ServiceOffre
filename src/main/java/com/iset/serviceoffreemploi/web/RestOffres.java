
package com.iset.serviceoffreemploi.web;

import com.iset.serviceoffreemploi.dao.OffreRepository;
import com.iset.serviceoffreemploi.entities.Offre;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Offres")
public class RestOffres {

    private final OffreRepository offreRepository;

    public RestOffres(OffreRepository offreRepository) {
        this.offreRepository = offreRepository;
    }

    @GetMapping
    public List<Offre> getAll() {
        return offreRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offre> getById(@PathVariable Long id) {
        return offreRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Offre saveOffre(@RequestBody Offre offre) {
        offre.setCode(null);
        return offreRepository.save(offre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offre> updateOffre(
            @PathVariable Long id, @RequestBody Offre modifiee) {
        return offreRepository.findById(id)
                .map(offre -> {
                    offre.setIntitule(modifiee.getIntitule());
                    offre.setSpecialite(modifiee.getSpecialite());
                    offre.setSociete(modifiee.getSociete());
                    offre.setNbpostes(modifiee.getNbpostes());
                    offre.setPays(modifiee.getPays());
                    return ResponseEntity.ok(offreRepository.save(offre));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable Long id) {
        if (!offreRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        offreRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
