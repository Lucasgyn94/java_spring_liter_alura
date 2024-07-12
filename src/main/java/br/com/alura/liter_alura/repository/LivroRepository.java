package br.com.alura.liter_alura.repository;

import br.com.alura.liter_alura.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    boolean existsByNome(String nome);

    @Query("SELECT DISTINCT l.idioma FROM Livro l ORDER BY l.idioma")
    List<String> bucasidiomas();

    @Query("SELECT l FROM Livro l WHERE idioma = :idiomaSelecionado")
    List<Livro> buscarPorIdioma(String idiomaSelecionado);

    Livro findByNome(String nome);

    List<Livro> findTop10ByOrderByQuantidadeDeDownloadsDesc();

    @Query("SELECT l FROM Livro l WHERE l.autor.nome ILIKE %:pesquisa%")
    List<Livro> encontrarLivrosPorAutor(String pesquisa);
}
