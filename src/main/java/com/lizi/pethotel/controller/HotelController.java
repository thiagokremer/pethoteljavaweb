package com.lizi.pethotel.controller;

import com.lizi.pethotel.model.Pet;
import com.lizi.pethotel.model.Tutor;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HotelController {

    private List<Tutor> listaTutor = new ArrayList<>();
    private List<Pet> listaPet = new ArrayList<>();

    //CRUD
    @GetMapping("/")
    private String inicio() {
        return "index";
    }

    @GetMapping("/cadastro-tutor")
    private String cadastrarTutor(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "cadastroTutor";
    }

    @PostMapping("/gravar-tutor")
    private String gravarTutor(@Valid @ModelAttribute Tutor tutor) {
        tutor.setId(listaTutor.size() + 1);
        listaTutor.add(tutor);
        return "redirect:/listagem-tutor";
    }

    @GetMapping("/listagem-tutor")
    private String listarTutor(Model model) {
        model.addAttribute("listaTutor", listaTutor);
        return "listagemTutor";
    }

    @GetMapping("/cadastro-pet")
    private String cadastrarPet(Model model, @RequestParam String id) {
        Integer idTutor = Integer.parseInt(id);
        Tutor tutorEncontrado = new Tutor();
        for (Tutor t : listaTutor) {
            if (t.getId() == idTutor) {
                tutorEncontrado = t;
                break;
            }
        }
        model.addAttribute("tutor", tutorEncontrado);
        model.addAttribute("pet", new Pet());
        return "cadastroPet";
    }

    @PostMapping("/gravar-pet")
    private String gravarPet(@ModelAttribute Tutor tutor, @Valid @ModelAttribute Pet pet, Model model, @RequestParam String id) {
              
        pet.setId(listaPet.size() + 1);
        pet.setTutor(tutor);
        listaPet.add(pet);
        return "redirect:/listagem-tutor";
    }

    @GetMapping("/listagem-petTutor")
    private String mostrarPetsTutor(Model model, @RequestParam String id) {
        Integer idTutor = Integer.parseInt(id);
        Tutor tutorEncontrado = new Tutor();
        for (Tutor t : listaTutor) {
            if (t.getId() == idTutor) {
                tutorEncontrado = t;
                break;
            }
        }
        List<Pet> petEncontrado = new ArrayList<>();
        for (Pet p : listaPet) {
            if (p.getTutor().getId() == idTutor) {
                petEncontrado.add(p);
            }
        }
        model.addAttribute("tutor", tutorEncontrado);
        model.addAttribute("pet", new Pet());
        model.addAttribute("pet", petEncontrado);
        model.addAttribute("listaPet", listaPet);
        return "listagemPetTutor";
    }

    @GetMapping("/listagem-pet")
    private String mostrarPets(Model model) {
        model.addAttribute("listaPet", listaPet);
        return "listagemPet";

    }

}
