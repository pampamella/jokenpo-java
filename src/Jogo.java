package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Jogo {
    private final int[][] matrizRecompensa = {
            {0, -1, 1, 1, -1},
            {1, 0, -1, -1, 1},
            {-1, 1, 0, 1, -1},
            {-1, 1, -1, 0, 1},
            {1, -1, 1, -1, 0}
    };

    public String verificaPartida(int jogadaPython, int jogadaJava) {
        int valor = matrizRecompensa[jogadaPython][jogadaJava];
        if (valor < 0) {
            System.out.println("Vitória do Java \n\n");
            return "Java Vencedor";
        } else if (valor > 0) {
            System.out.println("Vitória do Python \n\n");
            return "Python Vencedor";
        } else {
            System.out.println("Empate! \n\n");
            return "Empate";
        }
    }

    public void resumoPartidas(List<String> resultadosArray) {
        Map<String, Long> resumo =
                resultadosArray.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println("RESUMO");
        for (Map.Entry valor : resumo.entrySet()) {
            System.out.println(valor.getKey() + ": " + valor.getValue());
        }
    }
}
