package br.com.alura.liter_alura.models;


import br.com.alura.liter_alura.dto.AutorDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
@Getter
@Setter
@ToString

public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private int dataDeNascimento;
    private int dataDeFalecimento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor(){}
    public Autor(AutorDto data) {
        this.nome = data.nomeDoAutor();
        this.dataDeNascimento = data.anoDeNascimento();
        this.dataDeFalecimento = data.anoDeFalecimento();
    }



}
