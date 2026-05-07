package br.com.main.model;

import br.com.main.model.enums.Genero;

import java.util.Map;

public class Usuario {
    private String nome;
    private int idade;
    private PerfilCinefilo perfil;
    private boolean notificacoesAtivas;

    public Usuario(String nome, int idade, PerfilCinefilo perfil) {
        this.nome = nome;
        this.idade = idade;
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public PerfilCinefilo getPerfil() {
        return perfil;
    }

    //verifica o estado das notificacoes (ativadas ou desativadas)
    public boolean isNotificacoesAtivas() {
        return notificacoesAtivas;
    }

    //define se as notificacoes estao ativas ou nao
    public void setNotificacoesAtivas(boolean notificacoesAtivas) {
        this.notificacoesAtivas = notificacoesAtivas;
    }

    @Override
    public String toString() {

        String texto = "";

        texto += "Nome: " + nome + "\n";
        texto += "Idade: " + idade + "\n\n";

        texto += "Preferências de gênero (peso de 0.0 a 1.0):\n\n";
        for (Map.Entry<Genero, Double> entry : perfil.getPesosPorGenero().entrySet()) {
            texto += " - " + entry.getKey() + ": " + entry.getValue();
            if (entry.getValue() == 0.0) {
                texto += " (não gosta)";
            }
            texto += "\n";
        }

        texto += "\nDuração preferida: " + perfil.getDuracaoMin() + " a " +
                perfil.getDuracaoMax() + " minutos\n\n";

        texto += "Classificação máxima: " + perfil.getClassificacaoMax() + "\n\n";

        texto += "Idiomas aceitos: ";
        for (int i = 0; i < perfil.getListaIdiomas().size(); i++) {
            texto += perfil.getListaIdiomas().get(i);
            if (i < perfil.getListaIdiomas().size() - 1) {
                texto += ", ";
            }
        }
        texto += "\n\n";

        texto += "Já assistiu: [";
        int count = 0;
        for (Filme f : perfil.getHistorico()) {
            texto += "\"" + f.getTitulo() + "\"";
            count++;
            if (count < perfil.getHistorico().size()) {
                texto += ", ";
            }
        }
        texto += "]\n\n";

        texto += "Notas que já deu: {";
        count = 0;
        for (Map.Entry<Filme, Integer> entry : perfil.getNotasQueJaDeu().entrySet()) {
            texto += "\"" + entry.getKey().getTitulo() + "\": " + entry.getValue();
            count++;
            if (count < perfil.getNotasQueJaDeu().size()) {
                texto += ", ";
            }
        }
        texto += "}";

        return texto;
    }
}