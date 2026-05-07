package br.com.test;

import br.com.main.model.Filme;
import br.com.main.model.enums.ClassificacaoEtaria;
import br.com.main.model.enums.Genero;
import br.com.main.model.enums.Idioma;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilmeTest {

    @Test
    void deve_CriarFilme_ComTodosAtributos() {

        Filme filme = new Filme(
                "F01",
                "Teste",
                2020,
                120,
                List.of(Genero.DRAMA),
                ClassificacaoEtaria.DOZE,
                Idioma.INGLES,
                80
        );

        assertEquals("F01", filme.getId());
        assertEquals("Teste", filme.getTitulo());
        assertEquals(2020, filme.getAno());
        assertEquals(120, filme.getDuracao());
        assertEquals(ClassificacaoEtaria.DOZE, filme.getClassificacaoEtaria());
        assertEquals(Idioma.INGLES, filme.getIdioma());
    }

    @Test
    void deve_ConsiderarFilmesIguais_QuandoMesmoId() {

        Filme f1 = new Filme("F01", "A", 2020, 100,
                List.of(Genero.DRAMA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 50);

        Filme f2 = new Filme("F01", "B", 2021, 110,
                List.of(Genero.COMEDIA), ClassificacaoEtaria.DEZ, Idioma.PORTUGUES, 60);

        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }
}