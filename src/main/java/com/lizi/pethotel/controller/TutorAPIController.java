package com.lizi.pethotel.controller;

import com.lizi.pethotel.model.Tutor;
import com.lizi.pethotel.service.TutorService;
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
@RequestMapping("/tutor")
public class TutorAPIController {

    @Autowired
    TutorService tutorService;

    //CRUD
    @GetMapping("/listar")
    public ResponseEntity<List> listar() {
        List<Tutor> listagem = tutorService.listarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Tutor> addTutor(@RequestBody Tutor tut) {
        var novoTutor = tutorService.criar(tut);
        return new ResponseEntity<>(novoTutor, HttpStatus.CREATED);

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Tutor> pesquisar(@PathVariable Integer id) {
        Tutor tutorEncontrado = tutorService.buscarPorId(id);
        return new ResponseEntity<>(tutorEncontrado, HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Tutor> excluir(@PathVariable Integer id) {
        tutorService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Tutor> atualizarTutor(@PathVariable Integer id, @RequestBody Tutor tut) {
        var tutorEditado = tutorService.atualizar(id, tut);
        return new ResponseEntity<>(tutorEditado, HttpStatus.OK);
    }

}
