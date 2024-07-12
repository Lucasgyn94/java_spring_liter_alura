package br.com.alura.liter_alura.service;

import br.com.alura.liter_alura.dto.AutorDto;
import br.com.alura.liter_alura.dto.LivroDto;
import br.com.alura.liter_alura.models.Autor;
import br.com.alura.liter_alura.models.Livro;
import br.com.alura.liter_alura.repository.AutorRepository;
import br.com.alura.liter_alura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;

@Service
public class LivroService {

    @Autowired
    private ConsumoApi consumoApi;

    @Autowired
    private ConverteDados converteDados;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    private final String ADDRESS = "https://gutendex.com/books?search=";


    public void buscarNovoLivro(String titulo) {
        var dados = consumoApi.consumo(ADDRESS + titulo.replace(" ", "%20"));
        salvarNoDb(dados);
    }

    private void salvarNoDb(String dados) {
        try {
            Livro livro = new Livro(converteDados.obterDados(dados, LivroDto.class));
            Autor autor = new Autor(converteDados.obterDados(dados, AutorDto.class));
            Autor autorDb;
            Livro livroDb;

            if (!autorRepository.existsByNome(autor.getNome())) {
                autorRepository.save(autor);
                autorDb = autor;
            } else {
                autorDb = autorRepository.findByNome(autor.getNome());
            }
            if (!livroRepository.existsByNome(livro.getNome())) {
                livro.setAutor(autorDb);
                livroRepository.save(livro);
                livroDb = livro;
            } else {
                livroDb = livroRepository.findByNome(livro.getNome());
            }
            System.out.println(livroDb);
        } catch (NullPointerException e) {
            System.out.println("\n\n*** Livro não encontrado ***\n\n");
        }
    }


    public void buscarLivrosRegistrados() {
        var livrosDb = livroRepository.findAll();
        livrosDb.stream()
                .sorted(Comparator.comparing(Livro::getNome))
                .forEach(System.out::println);
    }

    public void buscarAutoresRegistrados() {
        var autoresDb = autorRepository.findAll();
        if(!autoresDb.isEmpty()){
            System.out.println("\nAutores cadastrados no banco de dados:");
            autoresDb.forEach(System.out::println);
        }else{
            System.out.println("\nNenhum autor encontrado no banco de dados!");
        }
    }

    public void buscarAutoresVivosPorAno(int anoSelecionado) {

        var buscaAutoresVivosDb = autorRepository.buscarPorAnoDeFalecimento(anoSelecionado);
        if (!buscaAutoresVivosDb.isEmpty()) {
            System.out.println("\n\nAtores vivos no ano de: " + anoSelecionado);
            buscaAutoresVivosDb.forEach(System.out::println);
        } else {
            System.out.println("\nNenhum autor encontrado para esta data!");
        }
    }

    public void buscarLivrosPorIdioma(String idiomaSelecionado) {
        livroRepository.buscarPorIdioma(idiomaSelecionado)
                .forEach(System.out::println);
    }

    public void listarIdiomasDeLivrosCadastrados() {
        var idiomasCadastrados = livroRepository.bucasidiomas();
        System.out.println("\nIdiomas cadastrados no banco:");
        idiomasCadastrados.forEach(System.out::println);
    }

    public void buscarTop10() {
        var top10 = livroRepository.findTop10ByOrderByQuantidadeDeDownloadsDesc();
        top10.forEach(System.out::println);
    }

    public void buscarAutorPorNome(String nomeAutor) {
        var autor = autorRepository.encontrarPorNome(nomeAutor);
        if (!autor.isEmpty()) {
            autor.forEach(System.out::println);
        } else {
            System.out.println("Autor nao encontrado!");
        }
    }

    public void mediaDeDownloadsPorAutor(String nomeAutor) {
        var test = livroRepository.encontrarLivrosPorAutor(nomeAutor);
        DoubleSummaryStatistics media = test.stream()
                .mapToDouble(Livro::getQuantidadeDeDownloads)
                .summaryStatistics();
        System.out.println("Média de Downloads: "+ media.getAverage());
    }
}
