package br.com.main.service;

import br.com.main.model.Filme;
import br.com.main.model.enums.ClassificacaoEtaria;
import br.com.main.model.enums.Genero;
import br.com.main.model.enums.Idioma;

import java.util.List;

public class CatalogoMock implements CatalogoFilmesAPI{

    @Override

    //filmes harcoded
    public List<Filme> buscarTodos() {
        return List.of(

                    new Filme("F01", "Interestelar", 2014, 169, List.of(Genero.FICCAO_CIENTIFICA, Genero.DRAMA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 95),
                    new Filme("F02", "A Origem", 2010, 148, List.of(Genero.FICCAO_CIENTIFICA, Genero.ACAO), ClassificacaoEtaria.DOZE, Idioma.INGLES, 94),
                    new Filme("F03", "Matrix", 1999, 136, List.of(Genero.FICCAO_CIENTIFICA, Genero.ACAO), ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES, 96),
                    new Filme("F04", "Blade Runner 2049", 2017, 164, List.of(Genero.FICCAO_CIENTIFICA, Genero.DRAMA), ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES, 90),
                    new Filme("F05", "Duna", 2021, 155, List.of(Genero.FICCAO_CIENTIFICA, Genero.DRAMA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 92),
                    new Filme("F06", "A Chegada", 2016, 116, List.of(Genero.FICCAO_CIENTIFICA, Genero.DRAMA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 89),
                    new Filme("F07", "Ela", 2013, 126, List.of(Genero.DRAMA, Genero.ROMANCE, Genero.FICCAO_CIENTIFICA), ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES, 88),
                    new Filme("F08", "Clube da Luta", 1999, 139, List.of(Genero.DRAMA), ClassificacaoEtaria.DEZOITO, Idioma.INGLES, 93),
                    new Filme("F09", "Forrest Gump", 1994, 142, List.of(Genero.DRAMA, Genero.ROMANCE), ClassificacaoEtaria.DOZE, Idioma.INGLES, 97),
                    new Filme("F10", "O Poderoso Chefão", 1972, 175, List.of(Genero.DRAMA), ClassificacaoEtaria.DEZOITO, Idioma.INGLES, 98),
                    new Filme("F11", "O Cavaleiro das Trevas", 2008, 152, List.of(Genero.ACAO, Genero.DRAMA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 98),
                    new Filme("F12", "Vingadores: Ultimato", 2019, 181, List.of(Genero.ACAO, Genero.FICCAO_CIENTIFICA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 96),
                    new Filme("F13", "Gladiador", 2000, 155, List.of(Genero.ACAO, Genero.DRAMA), ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES, 91),
                    new Filme("F14", "Mad Max: Estrada da Fúria", 2015, 120, List.of(Genero.ACAO), ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES, 90),
                    new Filme("F15", "John Wick", 2014, 101, List.of(Genero.ACAO), ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES, 87),
                    new Filme("F16", "Whiplash", 2014, 106, List.of(Genero.DRAMA), ClassificacaoEtaria.DEZESSEIS, Idioma.INGLES, 94),
                    new Filme("F17", "O Lobo de Wall Street", 2013, 180, List.of(Genero.DRAMA), ClassificacaoEtaria.DEZOITO, Idioma.INGLES, 91),
                    new Filme("F18", "Click", 2006, 107, List.of(Genero.COMEDIA, Genero.DRAMA), ClassificacaoEtaria.DOZE, Idioma.INGLES, 75),
                    new Filme("F19", "Cidade de Deus", 2002, 130, List.of(Genero.DRAMA, Genero.ACAO), ClassificacaoEtaria.DEZOITO, Idioma.PORTUGUES, 97),
                    new Filme("F20", "Tropa de Elite", 2007, 115, List.of(Genero.ACAO, Genero.DRAMA), ClassificacaoEtaria.DEZOITO, Idioma.PORTUGUES, 89),
                    new Filme("F21", "Central do Brasil", 1998, 113, List.of(Genero.DRAMA), ClassificacaoEtaria.DOZE, Idioma.PORTUGUES, 91),
                    new Filme("F22", "Que Horas Ela Volta?", 2015, 112, List.of(Genero.DRAMA), ClassificacaoEtaria.DOZE, Idioma.PORTUGUES, 88),
                    new Filme("F23", "Minha Mãe é uma Peça", 2013, 85, List.of(Genero.COMEDIA), ClassificacaoEtaria.DOZE, Idioma.PORTUGUES, 82),
                    new Filme("F24", "Bacurau", 2019, 132, List.of(Genero.DRAMA, Genero.FICCAO_CIENTIFICA), ClassificacaoEtaria.DEZESSEIS, Idioma.PORTUGUES, 86),
                    new Filme("F25", "O Auto da Compadecida", 2000, 104, List.of(Genero.COMEDIA, Genero.DRAMA), ClassificacaoEtaria.DOZE, Idioma.PORTUGUES, 95),
                    new Filme("F26", "Parasita", 2019, 132, List.of(Genero.DRAMA), ClassificacaoEtaria.DEZESSEIS, Idioma.COREANO, 95),
                    new Filme("F27", "Intocáveis", 2011, 112, List.of(Genero.DRAMA, Genero.COMEDIA), ClassificacaoEtaria.DOZE, Idioma.FRANCES, 93),
                    new Filme("F28", "A Vida é Bela", 1997, 116, List.of(Genero.DRAMA, Genero.ROMANCE), ClassificacaoEtaria.DOZE, Idioma.ITALIANO, 96),
                    new Filme("F29", "O Tigre e o Dragão", 2000, 120, List.of(Genero.ACAO, Genero.DRAMA), ClassificacaoEtaria.DOZE, Idioma.CHINES, 90),
                    new Filme("F30", "Oldboy", 2003, 120, List.of(Genero.DRAMA, Genero.ACAO), ClassificacaoEtaria.DEZOITO, Idioma.COREANO, 92)

            );


        }

        //metodo para buscar um filme pelo seu id ex:(buscarPorId("F01") )
    public Filme buscarPorId(String id) {
        return buscarTodos().stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }
}

