package com.tp.scolarite.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tp.scolarite.controller.EtudiantController;
import com.tp.scolarite.entity.Etudiant;
import com.tp.scolarite.exception.EtudiantNotFoundException;
import com.tp.scolarite.repository.EtudiantRepository;

@ExtendWith (MockitoExtension.class)
public class EtudiantControllerTest {

    @Mock EtudiantRepository repository;
    @InjectMocks EtudiantController controller;

    @Test
    void listerRetourneLesEtudiants() {
        when(repository.findAll())
        .thenReturn(List.of(new Etudiant ("Mballa", "Jean", "jean@mail.cm", "ASIC")));
        List<Etudiant> resultat = controller.lister();
        assertEquals(1, resultat.size());
        assertEquals("Mballa", resultat.get(0).getNom());
        assertEquals("Jean", resultat.get(0).getPrenom());
        assertEquals("ASIC", resultat.get(0).getFiliere());
    }


@Test
void getEtudiantIfExiste() {
    
    Long id = 1L;

    Etudiant etudiant = new Etudiant("Mballa","Jean","jean@mail.cm","ASIC");

    when(repository.findById(id)).thenReturn(Optional.of(etudiant));

    Etudiant resultat = controller.getEtudiant(id);

    // Assert
    assertNotNull(resultat);
    assertEquals("Mballa", resultat.getNom());
    assertEquals("Jean", resultat.getPrenom());
    assertEquals("ASIC", resultat.getFiliere());
    verify(repository).findById(id);
}


@Test
void getEtudiantIfNotExiste() {
    
    Long id = 99L;

    when(repository.findById(id)).thenReturn(Optional.empty());

    EtudiantNotFoundException exception = assertThrows(
        EtudiantNotFoundException.class,
        () -> controller.getEtudiant(id)
    );

    verify(repository).findById(id);
}

@Test
void creerEnregistreLEtudiant() {
 Etudiant etudiant = new Etudiant("Ngono", "Marie", "marie@mail.cm", "ASIC");
 when(repository.save(etudiant)).thenReturn(etudiant);
 Etudiant resultat = controller.creer(etudiant);
 assertEquals("Ngono", resultat.getNom());
 verify(repository).save(etudiant);
}

}
