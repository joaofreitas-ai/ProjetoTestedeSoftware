package br.com.test;

import br.com.main.model.Filme;
import br.com.main.model.PerfilCinefilo;
import br.com.main.model.enums.ClassificacaoEtaria;
import br.com.main.model.enums.Genero;
import br.com.main.model.enums.Idioma;
import br.com.main.service.FiltroFilmes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FiltroFilmesTest {

    private PerfilCinefilo perfil;
    private FiltroFilmes filtro;

    @BeforeEach
    void setUp() {
        perfil = new PerfilCinefilo();
        filtro = new FiltroFilmes();

        perfil.setPeso(Genero.DRAMA, 1.0);
        perfil.setClassificacaoMax(ClassificacaoEtaria.DEZESSEIS);
        perfil.setIdiomas(Idioma.INGLES);
    }

    @Test
    void deve_RemoverFilmeJaAssistido() {

        //cria filme
        Filme filme = criarFilme();
        //adiciona filme ao historico
        perfil.setHistorico(filme);

        //filtra filme como ja assistido
        List<Filme> resultado = filtro.filtrar(perfil, List.of(filme));

        //verifica se a lista de resultados esta vazia
        assertTrue(resultado.isEmpty());
    }

    @Test
    void deve_RemoverFilmePorClassificacao() {

        Filme filme = new Filme("F01", "Teste", 2020, 100,
                List.of(Genero.DRAMA), ClassificacaoEtaria.DEZOITO, Idioma.INGLES, 50);

        List<Filme> resultado = filtro.filtrar(perfil, List.of(filme));

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deve_RemoverFilmePorIdioma() {

        Filme filme = new Filme("F01", "Teste", 2020, 100,
                List.of(Genero.DRAMA), ClassificacaoEtaria.DOZE, Idioma.ESPANHOL, 50);

        List<Filme> resultado = filtro.filtrar(perfil, List.of(filme));

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deve_RemoverFilmeGeneroPesoZero() {

        perfil.setPeso(Genero.DRAMA, 0.0);

        Filme filme = criarFilme();

        List<Filme> resultado = filtro.filtrar(perfil, List.of(filme));

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deve_RetornarListaVazia_QuandoCatalogoVazio() {

        List<Filme> resultado = filtro.filtrar(perfil, new ArrayList<>());

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    private Filme criarFilme() {
        return new Filme("F01", "Teste", 2020, 100,
                List.of(Genero.DRAMA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 50);
    }
}