package br.com.alura.liter_alura.controllers;

import br.com.alura.liter_alura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LivroController {

    @Autowired
    private LivroService livroService;

    public void buscarNovoLivro(String titulo) {
        livroService.buscarNovoLivro(titulo);
    }

    public void buscarLivrosRegistrados() {
        livroService.buscarLivrosRegistrados();
    }

    public void buscarAutoresRegistrados() {
        livroService.buscarAutoresRegistrados();
    }

    public void buscarAutoresVivosPorAno(int anoSelecionado) {
        livroService.buscarAutoresVivosPorAno(anoSelecionado);
    }

    public void buscarLivrosPorIdioma(String idiomaSelecionado) {
        livroService.buscarLivrosPorIdioma(idiomaSelecionado);
    }

    public void buscarIdiomasDeLivrosCadastrados() {
        livroService.listarIdiomasDeLivrosCadastrados();
    }
    public void buscarTop10() {
        livroService.buscarTop10();
    }

    public void buscarAutorPorNome(String nomeAutor) {
        livroService.buscarAutorPorNome(nomeAutor);
    }

    public void mediaDeDownloadsPorAutor(String nomeAutor) {
        livroService.mediaDeDownloadsPorAutor(nomeAutor);
    }
}
