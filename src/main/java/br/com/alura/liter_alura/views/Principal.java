package br.com.alura.liter_alura.views;

import br.com.alura.liter_alura.controllers.LivroController;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {

    private Scanner sc = new Scanner(System.in);

    private final LivroController livroController;

    public Principal(LivroController livroController) {
        this.livroController = livroController;
    }

    public void exibirMenu() {
        String menu = """
                **********************************************
                1 - Buscar livro pelo titulo
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em determinado ano
                5 - Listar livros em determinado idioma
                6 - Top 10 livros
                7 - Buscar autores por nome
                8 - Media de downloads por autor
                0 - Sair
                **********************************************
                """;
        var opcao = -1;
        while (opcao != 0) {
            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o titulo do livro para a busca: ");
                    var titulo = sc.nextLine();
                    livroController.buscarNovoLivro(titulo);
                    break;
                case 2:
                    livroController.buscarLivrosRegistrados();
                    break;
                case 3:
                    livroController.buscarAutoresRegistrados();
                    break;
                case 4:
                    System.out.println("Digite o ano para buscar autores vivos:");
                    var ano = sc.nextInt();
                    livroController.buscarAutoresVivosPorAno(ano);
                    break;
                case 5:
                    livroController.buscarIdiomasDeLivrosCadastrados();
                    System.out.println("Digite o idioma do livro que voce quer buscar: ");
                    var idioma = sc.nextLine();
                    livroController.buscarLivrosPorIdioma(idioma);
                    break;
                case 6:
                    livroController.buscarTop10();
                    break;
                case 7:
                    System.out.println("Digite o nome do autor para busca: ");
                    var nomeAutor = sc.nextLine();
                    livroController.buscarAutorPorNome(nomeAutor);
                    break;
                case 8:
                    System.out.println("De qual autor voce deseja obter a media de download?");
                    var autor = sc.nextLine();
                    livroController.mediaDeDownloadsPorAutor(autor);
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        }
    }
}