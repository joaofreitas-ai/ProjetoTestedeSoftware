package br.com.test;

import br.com.main.model.*;
import br.com.main.model.enums.*;
import br.com.main.service.*;
import br.com.main.util.GeradorAleatorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecomendadorServiceTest {

    private CatalogoFilmesAPI catalogo;
    private HistoricoUsuarioRepository historico;
    private NotificadorPush notificador;
    private GeradorAleatorio gerador;

    private RecomendadorService service;
    private PerfilCinefilo perfil;
    private Usuario usuario;

    @BeforeEach
    void setUp() {

        catalogo = mock(CatalogoFilmesAPI.class);
        historico = mock(HistoricoUsuarioRepository.class);
        notificador = mock(NotificadorPush.class);
        gerador = mock(GeradorAleatorio.class);

        service = new RecomendadorService(
                catalogo,
                new FiltroFilmes(),
                new CalculadoraScore(),
                gerador,
                historico,
                notificador
        );

        perfil = new PerfilCinefilo();
        perfil.setPeso(Genero.DRAMA, 1.0);
        perfil.setDuracaoMin(90);
        perfil.setDuracaoMax(120);
        perfil.setIdiomas(Idioma.INGLES);
        perfil.setClassificacaoMax(ClassificacaoEtaria.DEZOITO);

        usuario = new Usuario("Joao", 25, perfil);
    }

    @Test
    void deve_RetornarTopN_Quando_QuantidadeSolicitada() {
        Filme filme = criarFilme("F01", 100);

        when(catalogo.buscarTodos()).thenReturn(List.of(filme));

        List<Recomendacao> resultado = service.recomendar(usuario, 1);

        assertEquals(1, resultado.size());
    }

    @Test
    void deve_OrdenarPorScoreDecrescente() {
        Filme melhor = criarFilme("F01", 100);
        Filme pior = criarFilme("F02", 10);

        when(catalogo.buscarTodos()).thenReturn(List.of(pior, melhor));

        List<Recomendacao> resultado = service.recomendar(usuario, 2);

        assertEquals("F01", resultado.get(0).getFilme().getId());
    }

    @Test
    void deve_DesempatarPorPopularidade() {
        Filme maisPopular = criarFilme("F01", 100);
        Filme menosPopular = criarFilme("F02", 10);

        when(catalogo.buscarTodos()).thenReturn(List.of(maisPopular, menosPopular));

        List<Recomendacao> resultado = service.recomendar(usuario, 2);

        assertEquals("F01", resultado.get(0).getFilme().getId());
    }

    @Test
    void deve_RetornarVazio_Quando_CatalogoVazio() {
        when(catalogo.buscarTodos()).thenReturn(List.of());

        List<Recomendacao> resultado = service.recomendar(usuario, 5);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deve_TratarErroAPI_Quando_Excecao() {
        when(catalogo.buscarTodos()).thenThrow(new RuntimeException());

        List<Recomendacao> resultado = service.recomendar(usuario, 5);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deve_ChamarHistorico_AposRecomendacao() {
        Filme filme = criarFilme("F01", 100);

        when(catalogo.buscarTodos()).thenReturn(List.of(filme));

        service.recomendar(usuario, 1);

        verify(historico).registrarRecomendacao(eq(usuario), anyList());
    }

    @Test
    void deve_ChamarNotificador_Quando_Ativo() {

        usuario.setNotificacoesAtivas(true);

        Filme filme = criarFilme("F01", 100);

        when(catalogo.buscarTodos()).thenReturn(List.of(filme));

        service.recomendar(usuario, 1);

        verify(notificador).enviar(eq(usuario), anyList());
    }

    @Test
    void deve_NaoChamarNotificador_Quando_Desligado() {

        usuario.setNotificacoesAtivas(false);

        Filme filme = criarFilme("F01", 100);

        when(catalogo.buscarTodos()).thenReturn(List.of(filme));

        service.recomendar(usuario, 1);

        verify(notificador, never()).enviar(any(), any());
    }

    @Test
    void deve_RetornarFilmeAleatorio() {
        Filme filme = criarFilme("F01", 100);

        when(catalogo.buscarTodos()).thenReturn(List.of(filme));
        when(gerador.escolher(anyList())).thenReturn(filme);

        Recomendacao resultado = service.recomendarAleatorio(usuario);

        assertNotNull(resultado);
        assertEquals("F01", resultado.getFilme().getId());
    }

    @Test
    @Tag("integracao")
    void deve_ExecutarPipelineCompleto() {
        Filme f1 = criarFilme("F01", 100);
        Filme f2 = criarFilme("F02", 50);

        when(catalogo.buscarTodos()).thenReturn(List.of(f1, f2));

        List<Recomendacao> resultado = service.recomendar(usuario, 2);

        assertEquals(2, resultado.size());
    }

    private Filme criarFilme(String id, int popularidade) {
        return new Filme(
                id,
                "Filme",
                2020,
                100,
                List.of(Genero.DRAMA),
                ClassificacaoEtaria.DOZE,
                Idioma.INGLES,
                popularidade
        );
    }

    @Test
    void deve_CapturarListaEnviadaParaHistorico() {

        Filme filme = criarFilme("F01", 100);

        when(catalogo.buscarTodos()).thenReturn(List.of(filme));

        service.recomendar(usuario, 1);

        ArgumentCaptor<List<Recomendacao>> captor = ArgumentCaptor.forClass(List.class);

        verify(historico).registrarRecomendacao(eq(usuario), captor.capture());

        List<Recomendacao> capturado = captor.getValue();

        assertEquals(1, capturado.size());
    }
}