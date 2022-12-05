package src;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Jogador {
    public int escolheJogada(){
        List<Integer> givenList = Arrays.asList(0, 1, 2, 3, 4);
        Random rand = new Random();
        return givenList.get(rand.nextInt(givenList.size()));
    }
}
