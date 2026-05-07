package br.com.main.service;

import br.com.main.model.Filme;
import br.com.main.model.PerfilCinefilo;
import br.com.main.model.enums.Genero;

public class CalculadoraScore {

    private static final double PESO_GENERO = 0.50;
    private static final double PESO_DURACAO = 0.20;
    private static final double PESO_POPULARIDADE = 0.15;
    private static final double PESO_AFINIDADE = 0.15;


    //metodo principal... "junta todos os scores e  multiplica pelos pesos"
    public double calcularScore(PerfilCinefilo perfil, Filme filme){

        double scoreGenero = calcularGenero(perfil, filme);
        double scoreDuracao = calcularDuracao(perfil, filme);
        double scorePopularidade = filme.getPopularidade();
        double scoreAfinidade = calcularAfinidade(perfil, filme);

        return (scoreGenero * PESO_GENERO) +
                (scoreDuracao * PESO_DURACAO) +
                (scorePopularidade * PESO_POPULARIDADE) +
                (scoreAfinidade * PESO_AFINIDADE);
    }


    //calcula score genero
    private double calcularGenero(PerfilCinefilo perfil, Filme filme){
        double soma = 0.0;

        for (Genero g : filme.getListaGeneros()){
            soma += perfil.getPeso(g);
        }

        double media = soma / filme.getListaGeneros().size();

        return media * 100;
    }

    //calcula score duracao
    private double calcularDuracao(PerfilCinefilo perfil, Filme filme){

        int duracao = filme.getDuracao();

        if (duracao >= perfil.getDuracaoMin() &&
                duracao <= perfil.getDuracaoMax()){
            return 100;
        }

        int diferenca;

        if (duracao < perfil.getDuracaoMin()){
            diferenca = perfil.getDuracaoMin() - duracao;
        } else {
            diferenca = duracao - perfil.getDuracaoMax();
        }

        double score = 100 - (diferenca * 2);

        if (score < 0){
            score = 0;
        }

        return score;
    }

    //calcula score afinidade
    private double calcularAfinidade(PerfilCinefilo perfil, Filme filme){

        double somaNotas = 0;
        int count = 0;

        for (Filme f : perfil.getNotasQueJaDeu().keySet()){

            for (Genero g : f.getListaGeneros()){
                if (filme.getListaGeneros().contains(g)){
                    somaNotas += perfil.getNotasQueJaDeu().get(f);
                    count++;
                    break;
                }
            }
        }

        if (count == 0){
            return 0;
        }

        double media = somaNotas / count;

        return (media / 5.0) * 100;
    }
}