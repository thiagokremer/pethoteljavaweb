package com.lizi.pethotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Tutor")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @CPF(message="CPF inválido")
    private String cpf;

    @NotBlank(message="Obrigatório informar o nome")
    @Size(min=2, message="Informe ao menos 2 caracteres para o campo nome")
    private String nome;

    private String endereco;

    private String contatos;

    @Email(message="E-mail inválido")
    private String email;

    @NotBlank(message="Obrigatório informar ao menos 1 telefone")
    private String telefones;

}
