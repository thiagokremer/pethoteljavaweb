package com.lizi.pethotel.service;

import com.lizi.pethotel.model.Pet;
import com.lizi.pethotel.repository.PetRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    //CRUD
    public Pet criar(Pet pet) {
        pet.setId(null);
        petRepository.save(pet);
        return pet;
    }

    public List<Pet> listarTodos() {
        return petRepository.findAll();
    }

    public List<Pet> listar(Integer idTutor) {
        return petRepository.findByTutorId(idTutor);
    }

    public Pet buscarPorId(Integer id) {
        return petRepository.findById(id).orElseThrow();
    }

    public void excluir(Integer id) {
        Pet petEncontrado = buscarPorId(id);
        petRepository.deleteById(petEncontrado.getId());
    }

    public Pet atualizar(Integer id, Pet petEnviado) {
        Pet petEncontrado = buscarPorId(id);
        petEncontrado.setNome(petEnviado.getNome());
        petEncontrado.setSexo(petEnviado.getSexo());
        petEncontrado.setIdade(petEnviado.getIdade());
        petEncontrado.setPeso(petEnviado.getPeso());
        petEncontrado.setCastrado(petEnviado.getCastrado());
        petEncontrado.setDataCuidados(petEnviado.getDataCuidados());
        petEncontrado.setCuidadosEspeciais(petEnviado.getCuidadosEspeciais());
        petEncontrado.setMedicamentos(petEnviado.getMedicamentos());
        petRepository.save(petEncontrado);
        return petEncontrado;
    }

}
