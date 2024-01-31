package com.lizi.pethotel.service;

import com.lizi.pethotel.model.Tutor;
import com.lizi.pethotel.repository.TutorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    TutorRepository tutorRepository;

    //CRUD
    public List<Tutor> listarTodos() {
        return tutorRepository.findAll();
    }

    public Tutor buscarPorId(Integer id) {
        return tutorRepository.findById(id).orElseThrow();
    }

    public void excluir(Integer id) {
        Tutor tutorEncontrado = buscarPorId(id);
        tutorRepository.deleteById(tutorEncontrado.getId());
    }

    public Tutor criar(Tutor tut) {
        tut.setId(null);
        tutorRepository.save(tut);
        return tut;
    }
    
    public Tutor atualizar(Integer id, Tutor tutorEnviado){
    Tutor tutorEncontrado = buscarPorId(id);
    tutorEncontrado.setCpf(tutorEnviado.getCpf());
    tutorEncontrado.setNome(tutorEnviado.getNome());
    tutorEncontrado.setEndereco(tutorEnviado.getEndereco());
    tutorEncontrado.setContatos(tutorEnviado.getContatos());
    tutorEncontrado.setEmail(tutorEnviado.getEmail());
    tutorEncontrado.setTelefones(tutorEnviado.getTelefones());
    tutorRepository.save(tutorEncontrado);
    return tutorEncontrado;
    }

}
