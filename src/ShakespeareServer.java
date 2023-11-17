import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ShakespeareServer {
    public static void main(String[] args) throws Exception {
        Socket clientSocket = null;

        // Запуск сервера
        ServerSocket server = new ServerSocket(8030);
        System.out.println("---Сервер запущен и ждёт подключений---");

        // Сервер постоянно ждёт подключения
        while (true) {
            try {
                // Принимаем подключения от клиентов
                clientSocket = server.accept();

                // Берём поток вывода клиента
                PrintStream ps = new PrintStream(clientSocket.getOutputStream());

                // Получаем все сонеты из файла
                List<String> sonnetsFromFile = Files.readAllLines(Paths.get("sonnets.txt"), Charset.defaultCharset());
                // Генерируем случайное число
                int randomSonnetNum = ThreadLocalRandom.current().nextInt(0, sonnetsFromFile.size());

                // Запись случайного сонета в выводной поток клиента
                ps.println(sonnetsFromFile.get(randomSonnetNum));
                // Затирание буфера клиента
                ps.flush();

                System.out.println("Сонет отправлен клиенту c IP: " + clientSocket.getInetAddress());

                // Закрытие подключения с клиентом
                clientSocket.close();
            }
            catch (IOException e) {
                System.out.println("Ошибка работы сервера: " + e);
                break;
            }
        }
    }
}