import java.util.Scanner;
import java.io.*;
import java.util.HashMap;

public class UserDataProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, PrintWriter> fileWriters = new HashMap<>();

        while (true) {
            System.out.println("Введите данные (Фамилия Имя Отчество Дата_рождения Номер_телефона Пол):");
            String userData = scanner.nextLine();
            
            if (userData.isEmpty()) {
                break;  // Выход из цикла при пустой строке ввода
            }
            
            String[] parts = userData.split(" ");
            if (parts.length != 6) {
                System.out.println("Ошибка: Неправильное количество данных.");
                continue;
            }

            String lastName = parts[0];
            String fileName = lastName + ".txt";

            // Проверка наличия файла для записи, если нет - создаем
            if (!fileWriters.containsKey(fileName)) {
                try {
                    PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
                    fileWriters.put(fileName, writer);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Ошибка при создании файла " + fileName);
                    continue;
                }
            }

            // Записываем данные в файл
            try {
                PrintWriter writer = fileWriters.get(fileName);
                writer.println(userData);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Ошибка при записи в файл " + fileName);
            }
        }

        // Закрываем все открытые файлы
        for (PrintWriter writer : fileWriters.values()) {
            writer.close();
        }
    }
}
