package br.com.main.service;

import br.com.main.model.Usuario;
import br.com.main.model.Recomendacao;

import java.util.List;

//contrato que diz: qualquer classe que implementar isso deve saber
// enviar recomendações para o usuario
public interface NotificadorPush {

    void enviar(Usuario usuario, List<Recomendacao> recomendacoes);
}