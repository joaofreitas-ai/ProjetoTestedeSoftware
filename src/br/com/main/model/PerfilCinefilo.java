package br.com.main.model;

import br.com.main.exception.DuracaoInvalidaException;
import br.com.main.exception.NotaInvalidaException;
import br.com.main.exception.PesoInvalidoException;
import br.com.main.model.enums.ClassificacaoEtaria;
import br.com.main.model.enums.Genero;
import br.com.main.model.enums.Idioma;

import java.util.*;

//perfil do usuario aqui vai conter suas informacoes, portanto as variaveis sao necessarias.

public class PerfilCinefilo {
    private int duracaoMax, duracaoMin;
    private ClassificacaoEtaria classificacaoMax;

    private Map<Genero, Double> pesosPorGenero = new HashMap<>();
    private Map<Filme, Integer> notasQueJaDeu = new HashMap<>();
    private Set<Filme> historico = new HashSet<>();
    private List<Idioma> listaIdiomas = new ArrayList<>();

    public void setPeso(Genero genero, double peso) {
        if (peso < 0.0 || peso > 1.0){
            throw new PesoInvalidoException("Peso deve estar entre 0.0 e 1.0");
        }else {
            pesosPorGenero.put(genero, peso);
        }
    }

    public void setDuracaoMin(int duracaoMin){
        if (duracaoMin > this.duracaoMax && this.duracaoMax != 0) {
            throw new DuracaoInvalidaException("Duracao invalida!");
        }
        this.duracaoMin = duracaoMin;
    }

    public void setDuracaoMax(int duracaoMax){
        this.duracaoMax = duracaoMax;
    }

    public void setClassificacaoMax(ClassificacaoEtaria classificacaoMax){
        this.classificacaoMax = classificacaoMax;

    }

    public void setIdiomas (Idioma idioma){
        listaIdiomas.add(idioma);
    }

    public void setHistorico(Filme filme){
        if (filme == null){
            throw new IllegalArgumentException("Filme não pode ser null");
        }else {
            historico.add(filme);
        }
    }

    public void setNotas(Filme filme, int nota){
        if (filme == null){
            throw new IllegalArgumentException("Filme não pode ser null");
        }if (nota < 1 || nota > 5){
            throw new NotaInvalidaException("Nota deve estar entre 1 e 5");
        } else {
            notasQueJaDeu.put(filme, nota);

        }

    }

    //metodo que verifica se o usuario ja viu aquele filme
    public boolean jaAssistiu(Filme filme) {
        return historico.contains(filme);
    }

    public int getDuracaoMax() {
        return duracaoMax;
    }

    public int getDuracaoMin() {
        return duracaoMin;
    }

    public ClassificacaoEtaria getClassificacaoMax() {
        return classificacaoMax;
    }

    //retorna o peso de todos os generos
    public Map<Genero, Double> getPesosPorGenero() {
        return pesosPorGenero;
    }

    public Map<Filme, Integer> getNotasQueJaDeu() {
        return notasQueJaDeu;
    }

    public Set<Filme> getHistorico() {
        return historico;
    }

    public List<Idioma> getListaIdiomas() {
        return listaIdiomas;
    }

    //retorna o peso de um genero ESPECIFICO, por exemplo o peso do genero DRAMA
    public double getPeso(Genero genero){
        return pesosPorGenero.getOrDefault(genero, 0.0);
    }
}
