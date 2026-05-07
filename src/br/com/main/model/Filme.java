package br.com.main.model;

import br.com.main.model.enums.ClassificacaoEtaria;
import br.com.main.model.enums.Genero;
import br.com.main.model.enums.Idioma;

import java.util.List;

public class Filme {
    private final String id;
    private final String titulo;
    private final int ano;
    private final int duracao;
    private final List<Genero> listaGeneros;
    private final ClassificacaoEtaria classificacaoEtaria;
    private final Idioma idioma;
    private final int popularidade;

    public Filme(String id, String titulo, int ano, int duracao, List<Genero> listaGeneros,
                 ClassificacaoEtaria classificacaoEtaria, Idioma idioma, int popularidade) {
        this.titulo = titulo;
        this.ano = ano;
        this.duracao = duracao;
        this.listaGeneros = List.copyOf(listaGeneros);
        this.classificacaoEtaria = classificacaoEtaria;
        this.idioma = idioma;
        this.popularidade = popularidade;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    @Override

    //compara o objeto atual (o que chamou o .equals) com o outro objeto
    public boolean equals(Object o) {
        if (this == o) { //verifica se são literalmente o msm objeto
            return true;
        }
        if (!(o instanceof Filme)){ //verifica se o objeto é um filme
            return false;
        }
        Filme filme = (Filme) o; // casting de objeto pra Filme
        return id.equals(filme.id); // verifica se os IDS são iguais
    }

    @Override
    public int hashCode() {
        return id.hashCode(); //tranforma o id do filme em um int
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Genero> getListaGeneros() {
        return listaGeneros;
    }

    public int getAno() {
        return ano;
    }

    public int getDuracao() {
        return duracao;
    }

    public ClassificacaoEtaria getClassificacaoEtaria() {
        return classificacaoEtaria;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public int getPopularidade() {
        return popularidade;
    }
}
