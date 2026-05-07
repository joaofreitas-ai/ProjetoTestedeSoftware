package br.com.main.service;

import br.com.main.model.Filme;
import br.com.main.model.PerfilCinefilo;
import br.com.main.model.enums.Genero;

import java.util.ArrayList;
import java.util.List;

public class FiltroFilmes {

    //metodo que vai adicionar os filmes a lista de filmes filtrados com base no metodo
    //deveRemover
    public List<Filme> filtrar(PerfilCinefilo perfil, List<Filme> filmes){
        List<Filme> resultado = new ArrayList<>();

        for (Filme filme : filmes){
            if(deveRemover(perfil, filme)){
                continue;
            }

            resultado.add(filme);
        }

        return resultado;
    }


    //filtra os filmes que devem ser removidos com base no perfilCinefilo
    private boolean deveRemover(PerfilCinefilo perfil, Filme filme) {

        //remove se ja foi assistido (com base no historico)
        if (perfil.jaAssistiu(filme)) {
            return true;
        }

        //remove com base na classificao etaria do perfilCinefilo
        //obs: ordinal retorna a posicao do enum, como se fosse um indice...
        if (filme.getClassificacaoEtaria().ordinal() > perfil.getClassificacaoMax().ordinal()) {
            return true;
        }

        //remove caso o idioma do filme nao esteja nos idiomas do perfilcinefilo
        if (!perfil.getListaIdiomas().contains(filme.getIdioma())) {
            return true;
        }

        //remove caso o peso do genero seja 0, ou seja, o usuario nao gosta
        for (Genero g : filme.getListaGeneros()) {
            if (perfil.getPeso(g) == 0.0) {
                return true;
            }
        }

        return false;
    }
}
