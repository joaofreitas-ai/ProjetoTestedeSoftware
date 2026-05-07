package br.com.main.service;

import br.com.main.model.Usuario;
import br.com.main.model.Recomendacao;

import java.util.List;


// contrato que diz: qualquer classe que implementar isso deve saber
// salvar as recomendacoes de um usuario
public interface HistoricoUsuarioRepository {

    void registrarRecomendacao(Usuario usuario, List<Recomendacao> recomendacoes);
}