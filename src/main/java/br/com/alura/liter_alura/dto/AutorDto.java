package br.com.alura.liter_alura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDto(
        @JsonAlias("name") String nomeDoAutor,
        @JsonAlias("birth_year") int anoDeNascimento,
        @JsonAlias("death_year") int anoDeFalecimento
) {
}
