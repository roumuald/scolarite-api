package com.tp.scolarite.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tp.scolarite.entity.Etudiant;
import com.tp.scolarite.exception.EtudiantNotFoundException;
import com.tp.scolarite.repository.EtudiantRepository;

@RestController 
@RequestMapping ("/etudiants")
public class EtudiantController {

    private final EtudiantRepository repository;

    public EtudiantController(EtudiantRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Etudiant> lister() { return repository.findAll(); }

    @PostMapping
    public Etudiant creer(@RequestBody Etudiant etudiant) {
        return repository.save(etudiant);
    }

    @GetMapping("/{id}")
    public Etudiant getEtudiant(@PathVariable  Long id){
        return repository.findById(id).orElseThrow(() ->
                new EtudiantNotFoundException(id)
            );
    }

    
}
