import java.io.*;
import java.net.*;

public class ShakespeareClient {
    public static void main(String[] args) {
        Socket serverSocket = null;

        try {
            // Подключение к серверу
            serverSocket = new Socket( "localhost" , 8030);

            // Берём поток ввода сервера
            BufferedReader dis = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

            // Получение сообщения от сервера
            String sonnet = dis.readLine().translateEscapes();

            // Выводим полученный сонет Шекспира
            System.out.println(sonnet);
        }
        catch (IOException e) {
            System.out.println("Ошибка работы клиента: " + e);
        }
    }
}