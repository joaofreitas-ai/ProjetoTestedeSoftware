package br.com.main;

import br.com.main.model.Filme;
import br.com.main.model.PerfilCinefilo;
import br.com.main.model.Recomendacao;
import br.com.main.model.Usuario;
import br.com.main.model.enums.ClassificacaoEtaria;
import br.com.main.model.enums.Genero;
import br.com.main.model.enums.Idioma;
import br.com.main.service.*;
import br.com.main.util.GeradorAleatorio;
import br.com.main.util.GeradorAleatorioImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PerfilCinefilo perfil = new PerfilCinefilo();

        perfil.setPeso(Genero.FICCAO_CIENTIFICA, 0.6);
        perfil.setPeso(Genero.DRAMA, 0.0);
        perfil.setPeso(Genero.ROMANCE, 0.7);
        perfil.setPeso(Genero.COMEDIA, 1.0);
        perfil.setPeso(Genero.TERROR,0.5 );
        perfil.setPeso(Genero.ACAO,0.2 );

        perfil.setDuracaoMin(90);
        perfil.setDuracaoMax(180);

        perfil.setClassificacaoMax(ClassificacaoEtaria.DEZOITO);

        perfil.setIdiomas(Idioma.PORTUGUES);
        perfil.setIdiomas(Idioma.INGLES);
        perfil.setIdiomas(Idioma.ESPANHOL);

        CatalogoMock catalogo = new CatalogoMock();

        perfil.setHistorico(catalogo.buscarPorId("F01"));
        perfil.setHistorico(catalogo.buscarPorId("F15"));
        perfil.setHistorico(catalogo.buscarPorId("F30"));

        perfil.setNotas(catalogo.buscarPorId("F02"), 3);
        perfil.setNotas(catalogo.buscarPorId("F16"), 1);
        perfil.setNotas(catalogo.buscarPorId("F07"), 4);

        Usuario usuario = new Usuario("Paulo", 34, perfil);


        System.out.println(usuario);

        CalculadoraScore calc = new CalculadoraScore();

        Filme filme = catalogo.buscarPorId("F02");

        double score = calc.calcularScore(perfil, filme);

        System.out.println("Score " + score);



        System.out.println("Score: " + score);

        FiltroFilmes filtro = new FiltroFilmes();

        List<Filme> filtrados = filtro.filtrar(perfil, catalogo.buscarTodos());

        System.out.println(filtrados.get(0).getTitulo());

        GeradorAleatorioImpl geradorAleatorio = new GeradorAleatorioImpl();

        HistoricoUsuarioRepository historico = new HistoricoUsuarioRepository() {
            @Override
            public void registrarRecomendacao(Usuario usuario, List<Recomendacao> recomendacoes) {
                System.out.println("Salvando histórico de " + usuario.getNome());
            }
        };

        NotificadorPush notificador = new NotificadorPush() {
            @Override
            public void enviar(Usuario usuario, List<Recomendacao> recomendacoes) {
                System.out.println("Enviando notificação para " + usuario.getNome());
            }
        };




     RecomendadorService service = new RecomendadorService(catalogo,filtro,calc, geradorAleatorio, historico,notificador );

        List<Recomendacao> lista = service.recomendar(usuario, 5);

        for (Recomendacao r : lista) {
            System.out.println(r.getFilme().getTitulo());
        }




    }
}
