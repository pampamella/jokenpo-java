package src;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String args[]){
        try {
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Servidor iniciado na porta 5000");
            Socket cliente = server.accept();
            System.out.println("Cliente conectado do IP "+ cliente.getInetAddress().getHostAddress());

            Jogador JogadorJava = new Jogador();
            Jogo jogo = new Jogo();
            List<String> resultadosArray = new ArrayList<>();
            int contador = 0;

            while(contador < 15){
                Scanner entrada = new Scanner(cliente.getInputStream());    // recebe jogada python
                OutputStream saida = cliente.getOutputStream();
                int jogadaPython = Integer.parseInt(entrada.nextLine());

                int jogada = JogadorJava.escolheJogada();
                String jogadaJava = Integer.toString(jogada);
                saida.write(jogadaJava.getBytes(StandardCharsets.UTF_8));   //envia jogada java


                System.out.println("  Partida " + (contador+1) + "\n");
                System.out.println("Jogada Python: " + jogadaPython);
                System.out.println("Jogada Java: " + jogadaJava + "\n");

                String resultadoPartida = jogo.verificaPartida(jogadaPython, jogada);  //verifica vencedor da partida
                resultadosArray.add(resultadoPartida);                                 // guarda resultados das partidas

                contador++;
            }
            jogo.resumoPartidas(resultadosArray);

            server.close();

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}