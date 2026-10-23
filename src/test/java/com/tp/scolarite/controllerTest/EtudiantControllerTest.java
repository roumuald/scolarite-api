package com.tp.scolarite.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tp.scolarite.controller.EtudiantController;
import com.tp.scolarite.entity.Etudiant;
import com.tp.scolarite.repository.EtudiantRepository;

@ExtendWith (MockitoExtension.class)
public class EtudiantControllerTest {

    @Mock EtudiantRepository repository;
    @InjectMocks EtudiantController controller;
    @Test
    void listerRetourneLesEtudiants() {
        when(repository.findAll())
        .thenReturn(List.of(new Etudiant ("Mballa", "Jean", "jean@mail.cm")));
        List<Etudiant> resultat = controller.lister();
        assertEquals(1, resultat.size());
        assertEquals("Mballa", resultat.get(0).getNom());
        assertEquals("Jean", resultat.get(0).getPrenom());
    }
}
