package com.lizi.pethotel.controller;

import com.lizi.pethotel.model.Pet;
import com.lizi.pethotel.service.PetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pet")
public class PetAPIController {

    @Autowired
    PetService petService;

    @PostMapping("/adicionar")
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
        var novoPet = petService.criar(pet);
        return new ResponseEntity<>(novoPet, HttpStatus.CREATED);

    }

    @GetMapping("/buscar/{idTutor}")
    public ResponseEntity<List> pesquisar(@PathVariable Integer idTutor) {
        List<Pet> petEncontrado = petService.listar(idTutor);
        return new ResponseEntity<>(petEncontrado, HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List> listar() {
        List<Pet> listagem = petService.listarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Pet> excluir(@PathVariable Integer id) {
        petService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Pet> atualizarPet(@PathVariable Integer id, @RequestBody Pet pet) {
        var petEditado = petService.atualizar(id, pet);
        return new ResponseEntity<>(petEditado, HttpStatus.OK);
    }

}
