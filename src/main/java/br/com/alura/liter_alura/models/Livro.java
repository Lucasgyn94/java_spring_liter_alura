package br.com.alura.liter_alura.models;


import br.com.alura.liter_alura.dto.LivroDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "livros")
@Getter
@Setter
@ToString
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private String idioma;
    private Integer quantidadeDeDownloads;

    @ManyToOne
    private Autor autor;

    public Livro() {

    }

    public Livro(LivroDto dados) {
        this.nome = dados.nomeDoLivro();
        this.idioma = String.join(",",dados.idiomas());
        this.quantidadeDeDownloads = dados.quantidadeDeDownloads();

    }


    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idioma='" + idioma + '\'' +
                ", quantidadeDeDownloads=" + quantidadeDeDownloads +
                ", autorNome='" + autor.getNome() + '\'' +
                '}';
    }


}
