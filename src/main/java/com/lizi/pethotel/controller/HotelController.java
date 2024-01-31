package com.lizi.pethotel.controller;

import com.lizi.pethotel.model.Pet;
import com.lizi.pethotel.model.Tutor;
import com.lizi.pethotel.service.PetService;
import com.lizi.pethotel.service.TutorService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    TutorService tutorService;

    @Autowired
    PetService petService;

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
        tutorService.criar(tutor);
        return "redirect:/listagem-tutor";
    }

    @GetMapping("/listagem-tutor")
    private String listarTutor(Model model) {
        model.addAttribute("listaTutor", tutorService.listarTodos());
        return "listagemTutor";
    }

    @GetMapping("/atualizar-tutor")
    public String atualizarTutorBuscar(@RequestParam String id, Model model) {
        Integer idTutor = Integer.parseInt(id);
        Tutor tutorEncontrado = new Tutor();
        tutorEncontrado = tutorService.buscarPorId(idTutor);
        model.addAttribute("tutor", tutorEncontrado);
        return "atualizarTutor";
    }

    @PostMapping("/atualizar-tutor")
    public String atualizarTutor(@RequestParam String id, @Valid @ModelAttribute Tutor tutor) {
        Integer idTutor = Integer.parseInt(id);
        tutorService.atualizar(idTutor, tutor);
        return "redirect:/listagem-tutor";
    }

    @GetMapping("/apagar-tutor")
    public String apagarTutor(@RequestParam String id) {
        Integer idTutor = Integer.parseInt(id);
        tutorService.excluir(idTutor);
        return "redirect:/listagem-tutor";
    }

    @GetMapping("/cadastro-pet")
    private String cadastrarPet(Model model, @RequestParam String id) {
        Integer idTutor = Integer.parseInt(id);
        Tutor tutorEncontrado = new Tutor();
        tutorEncontrado = tutorService.buscarPorId(idTutor);
        model.addAttribute("tutor", tutorEncontrado);
        model.addAttribute("pet", new Pet());
        return "cadastroPet";
    }

    @PostMapping("/gravar-pet")
    private String gravarPet(@ModelAttribute Tutor tutor, @Valid @ModelAttribute Pet pet, Model model, @RequestParam String id) {
        pet.setTutor(tutor);
        petService.criar(pet);
        return "redirect:/listagem-tutor";
    }

    @GetMapping("/listagem-petTutor")
    private String mostrarPetsTutor(Model model, @RequestParam String id) {
        Integer idTutor = Integer.parseInt(id);
        Tutor tutorEncontrado = new Tutor();
        tutorEncontrado = tutorService.buscarPorId(idTutor);
        model.addAttribute("tutor", tutorEncontrado);
        model.addAttribute("listaPetTutor", petService.listar(idTutor));
        return "listagemPetTutor";
    }

    @GetMapping("/listagem-pet")
    private String listarPets(Model model) {
        model.addAttribute("listaPet", petService.listarTodos());
        return "listagemPet";

    }
    
    @GetMapping("/atualizar-pet")
    public String atualizarPetBuscar(@RequestParam String id, Model model) {
        Integer idPet = Integer.parseInt(id);
        Pet petEncontrado = new Pet();
        petEncontrado = petService.buscarPorId(idPet);
        model.addAttribute("pet", petEncontrado);
        return "atualizarPet";
    }

    @PostMapping("/atualizar-pet")
    public String atualizarPet(@RequestParam String id,@Valid @ModelAttribute Pet pet) {
        Integer idPet = Integer.parseInt(id);
        petService.atualizar(idPet, pet);
        return "redirect:/listagem-pet";
    }

    @GetMapping("/apagar-pet")
    public String apagarPet(@RequestParam String id) {
        Integer idPet = Integer.parseInt(id);
        petService.excluir(idPet);
        return "redirect:/";
    }

}
