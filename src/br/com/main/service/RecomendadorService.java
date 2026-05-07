package br.com.main.service;

import br.com.main.model.*;
import br.com.main.util.GeradorAleatorio;

import java.util.ArrayList;
import java.util.List;

public class RecomendadorService {

    private CatalogoFilmesAPI catalogo;
    private FiltroFilmes filtro;
    private CalculadoraScore calculadora;
    private GeradorAleatorio geradorAleatorio;
    private HistoricoUsuarioRepository historico;
    private NotificadorPush notificador;

    //construtor para pegar todas as informacoes que cercam a recomendacao em si
    public RecomendadorService(CatalogoFilmesAPI catalogo,
                               FiltroFilmes filtro,
                               CalculadoraScore calculadora,
                               GeradorAleatorio geradorAleatorio,
                               HistoricoUsuarioRepository historico,
                               NotificadorPush notificador) {

        this.catalogo = catalogo;
        this.filtro = filtro;
        this.calculadora = calculadora;
        this.geradorAleatorio = geradorAleatorio;
        this.historico = historico;
        this.notificador = notificador;
    }

    public List<Recomendacao> recomendar(Usuario usuario, int topN) {

        //pega perfil do usuario e extrai as preferencias
        PerfilCinefilo perfil = usuario.getPerfil();
        //declara a lista de filmes
        List<Filme> filmes;

        //BUG CORRIGIDO AQUI: tratamento da API
        //busca os filmes, se der algum erro na API retorna lista vazia, (caiu aqui, nada executa)
        try {
            filmes = catalogo.buscarTodos();
        } catch (Exception e) {
            return new ArrayList<>();
        }

        //cria a lista de filtrados
        List<Filme> filtrados = filtro.filtrar(perfil, filmes);

        //cria lista de recomendacao
        List<Recomendacao> recomendacoes = new ArrayList<>();

        //loop principal, filtra os filmes e ve o quao bom é para o usuario com base no seu score
        for (Filme filme : filtrados) {
            double score = calculadora.calcularScore(perfil, filme);

            //cria a recomendacao
            recomendacoes.add(new Recomendacao(filme, score, "Baseado no seu perfil"));
        }

        // ordena a lista ja com desempate

        //compara duas recomendacoes
        recomendacoes.sort((r1, r2) -> {

            //compara o score das recomendacoes
            //se cmp negativo:(r1 vem antes); se cmp positivo: (r2 vem antes); se cmp 0: (empate)
            //invertido para pegar do maior para o menor score
            int cmp = Double.compare(r2.getScore(), r1.getScore());

            //se nao for empate retorna cmp
            if (cmp != 0) {
                return cmp;
            }

            //compara a popularidade (criterio de desempate)
            cmp = Integer.compare(
                    r2.getFilme().getPopularidade(),
                    r1.getFilme().getPopularidade());

            //se nao for empate retorna cmp
            if (cmp != 0) {
                return cmp;
            }

            //dando empate no score e popularidade, chamamos o gerador aleatorio que vai pegar um filme aleatorio
            //escolhe aleatoriamente entre o negativo ou positivo
            return geradorAleatorio.escolher(List.of(-1, 1));
        });

        //se tiver mais recomendacoes doq o topN, pega os filmes do 0 até topN
        if (recomendacoes.size() > topN) {
            recomendacoes = recomendacoes.subList(0, topN);
        }

        // salva o historico
        historico.registrarRecomendacao(usuario, recomendacoes);

        //BUG CORRIGIDO AQUI
        //manda as recomendacoes se elas estiverem ativas
        if (usuario.isNotificacoesAtivas()) {
            notificador.enviar(usuario, recomendacoes);
        }

        return recomendacoes;
    }


    //mesma coisa do recomendar, porem ao inves de devolver uma lista topN, devolve apenas 1 filme aleatorio da lista filtrada
    public Recomendacao recomendarAleatorio(Usuario usuario) {

        PerfilCinefilo perfil = usuario.getPerfil();
        List<Filme> filmes;

        //busca os filmes, se der algum erro na API retorna lista vazia, (caiu aqui, nada executa)
        try {
            filmes = catalogo.buscarTodos();
        } catch (Exception e) {
            return null;
        }

        List<Filme> filtrados = filtro.filtrar(perfil, filmes);

        if (filtrados.isEmpty()) {
            return null;
        }

        Filme filme = geradorAleatorio.escolher(filtrados);
        double score = calculadora.calcularScore(perfil, filme);

        return new Recomendacao(filme, score, "Sugestão surpresa para você");
    }
}