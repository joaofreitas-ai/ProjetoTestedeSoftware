package br.com.main.util;

import java.util.List;
import java.util.Random;

//define um contrato para escolher algo aleatoriamente da lista
public interface GeradorAleatorio {
    Random random = new Random();

    //metodo generico funciona para qualquer tipo
    <T> T escolher(List<T> lista);


}
