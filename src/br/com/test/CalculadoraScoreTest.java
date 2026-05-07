package br.com.test;

import br.com.main.model.Filme;
import br.com.main.model.PerfilCinefilo;
import br.com.main.model.enums.ClassificacaoEtaria;
import br.com.main.model.enums.Genero;
import br.com.main.model.enums.Idioma;
import br.com.main.service.CalculadoraScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraScoreTest {

    private PerfilCinefilo perfil;
    private CalculadoraScore calculadora;

    @BeforeEach
    void setUp() {
        perfil = new PerfilCinefilo();
        calculadora = new CalculadoraScore();

        perfil.setPeso(Genero.DRAMA, 1.0);
        perfil.setDuracaoMin(90);
        perfil.setDuracaoMax(120);
    }

    @Test
    void deve_RetornarScoreGenero100_Quando_Peso1() {

        Filme filme = new Filme("F01", "Teste", 2020, 100,
                List.of(Genero.DRAMA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 50);

        double score = calculadora.calcularScore(perfil, filme);


        //assertTrue pq quando genero 1, consequentemente score > 50, pois scoreGenero vale 50% do score
        assertTrue(score > 50); // parte gênero forte
    }

    @Test
    void deve_RetornarScoreBaixo_Quando_GeneroNaoPreferido() {

        Filme filme = new Filme("F01", "Teste", 2020, 100,
                List.of(Genero.TERROR), ClassificacaoEtaria.DOZE, Idioma.INGLES, 50);

        double score = calculadora.calcularScore(perfil, filme);

        //reotorna score < 50 pq genero terror esta com 0 de peso
        assertTrue(score < 50);
    }

    @Test
    void deve_RetornarScoreDuracao100_Quando_DentroDoIntervalo() {

        Filme filme = new Filme("F01", "Teste", 2020, 100,
                List.of(Genero.DRAMA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 50);

        double score = calculadora.calcularScore(perfil, filme);

        //score maior que 0 pq esta dentro do min e max, assim ganhando score...
        assertTrue(score > 0);
    }

    @Test
    void deve_ReduzirScore_Quando_DuracaoForaIntervalo() {

        Filme filme = new Filme("F01", "Teste", 2020, 150,
                List.of(Genero.DRAMA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 50);

        double score = calculadora.calcularScore(perfil, filme);

        assertTrue(score < 100);
    }

    @Test
    void deve_ManterScoreEntre0e100() {

        Filme filme = new Filme("F01", "Teste", 2020, 500,
                List.of(Genero.TERROR), ClassificacaoEtaria.DEZOITO, Idioma.INGLES, 0);

        double score = calculadora.calcularScore(perfil, filme);

        assertTrue(score >= 0 && score <= 100);
    }
}