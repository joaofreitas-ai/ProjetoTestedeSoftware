package br.com.main.util;

import java.util.List;
import java.util.Random;


//implementa intereface gerador aleatorio
    public class GeradorAleatorioImpl implements GeradorAleatorio {

        private Random random = new Random();

        @Override
        public <T> T escolher(List<T> lista) {
            if (lista == null || lista.isEmpty()) {
                return null;
            }

            return lista.get(random.nextInt(lista.size()));
        }
    }

