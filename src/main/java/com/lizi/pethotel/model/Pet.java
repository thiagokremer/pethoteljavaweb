package com.lizi.pethotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tutor")
    private Tutor tutor;

    @NotBlank(message="Obrigatório informar o nome")
    @Size(min=2, message="Informe ao menos 2 caracteres para o campo nome")
    private String nome;

    @NotBlank(message="Obrigatório informar o sexo")
    private String sexo;

    @NotNull(message="Obrigatório informar a idade")
    private Integer idade;

    @NotNull(message="Obrigatório informar o peso")
    private Integer peso;

    @NotNull(message="Obrigatório informar se é castrado")
    private String castrado;

    private String dataCuidados;

    private String cuidadosEspeciais;

    private String medicamentos;

}
