package br.com.alura.liter_alura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDto(@JsonAlias("title")String nomeDoLivro,
                       @JsonAlias("download_count") Integer quantidadeDeDownloads,
                       @JsonAlias("languages") List<String> idiomas
) {
}
