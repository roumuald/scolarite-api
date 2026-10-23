package com.tp.scolarite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tp.scolarite.entity.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{
    
}
