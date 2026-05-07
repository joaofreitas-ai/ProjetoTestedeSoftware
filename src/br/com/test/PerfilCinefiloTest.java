package br.com.test;

import br.com.main.exception.DuracaoInvalidaException;
import br.com.main.exception.NotaInvalidaException;
import br.com.main.exception.PesoInvalidoException;
import br.com.main.model.Filme;
import br.com.main.model.PerfilCinefilo;
import br.com.main.model.enums.ClassificacaoEtaria;
import br.com.main.model.enums.Genero;
import br.com.main.model.enums.Idioma;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PerfilCinefiloTest {

    private PerfilCinefilo perfil;
    private Filme filme;

    @BeforeEach
    void setUp() {
        perfil = new PerfilCinefilo();

        filme = new Filme(
                "F01",
                "Teste",
                2020,
                120,
                List.of(Genero.DRAMA),
                ClassificacaoEtaria.DOZE,
                Idioma.INGLES,
                80
        );
    }

    @Test
    @DisplayName("deve_CriarPerfil_Quando_PesosValidos")
    void deve_CriarPerfil_Quando_PesosValidos() {


        perfil.setPeso(Genero.DRAMA, 0.8);
        perfil.setPeso(Genero.COMEDIA, 0.5);

        assertEquals(0.8, perfil.getPesosPorGenero().get(Genero.DRAMA));
        assertEquals(0.5, perfil.getPesosPorGenero().get(Genero.COMEDIA));
    }

    @Test
    @DisplayName("deve_LancarExcecao_Quando_PesoForaDosLimites")
    void deve_LancarExcecao_Quando_PesoForaDoIntervalo() {
        assertThrows(PesoInvalidoException.class, () -> perfil.setPeso(Genero.DRAMA, -0.1));
        assertThrows(PesoInvalidoException.class, () -> perfil.setPeso(Genero.DRAMA, 1.5));
    }

    @Test
    @DisplayName("deve_LancarExcecao_Quando_DuracaoMinMaiorQueMax")
    void deve_LancarExcecao_Quando_DuracaoMinMaiorQueMax() {
        perfil.setDuracaoMax(100);

        assertThrows(DuracaoInvalidaException.class, () -> perfil.setDuracaoMin(120));
    }

    @Test
    @DisplayName("deve_LancarExcecao_Quando_NotaForaDoIntervalo")
    void deve_LancarExcecao_Quando_NotaForaDoIntervalo() {

        assertThrows(NotaInvalidaException.class, () -> perfil.setNotas(filme, 6));

        assertThrows(NotaInvalidaException.class, () -> perfil.setNotas(filme, 0));
    }

    @Test
    @DisplayName("deve_AdicionarFilmeAoHistorico_Quando_MarcadoComoAssistido")
    void deve_AdicionarFilmeAoHistorico_Quando_MarcadoComoAssistido() {
        perfil.setHistorico(filme);

        assertTrue(perfil.getHistorico().contains(filme));
    }
}