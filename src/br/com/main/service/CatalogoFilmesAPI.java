package br.com.main.service;

import br.com.main.model.Filme;

import java.util.List;

public interface CatalogoFilmesAPI {


    //metodo para buscar todos os filmes da classe CatalogoMock
    public List<Filme> buscarTodos();

}
