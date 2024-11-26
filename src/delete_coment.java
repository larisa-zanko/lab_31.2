//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
import java.util.regex.*;

/* 4.2(дополнительно)
Программа на Java предназначена для удаления комментариев из Java-кода,
находящегося в текстовом файле. Она принимает путь к файлу с кодом в качестве аргумента
 командной строки, очищает его от комментариев и сохраняет результат в новый файл


Программа способна удалять коментарии с помощью   помощью  метода removeComments.
Метод removeComments  удаляет однострочные коментарии с помощью выражений://.*?(\r?\n|$).
А многосторонние файлы удаляет с помощью выражений:  /\\*.*?\\*/
/*Метод removeComments
Заменяет комментарии на пустую строку и возвращает очищенный код.
Сохраняет очищенный код в выходной файл с помощью writeFile.

С помощью метода readFile: програама Читает файл построчно, используя BufferedReader.
Собирает содержимое в StringBuilder и возвращает как строку.



Программы delete_coment  удаляет комментарии из файла java_code.java, причём оба файла находятся в одной папке.
Для реализации задачи необходимо вести в командную стоку:
cd "C:\Users\ВашеИмя\адресс\4.2_сoment_zanko\src"
javac delete_coment.java
java delete_coment java_code.java"

После выполнения программы в одном котологе с delete_coment.java и  java_code.java дожен появиться файл output.java,
где отображено содердимое java_code.java без коментариев.
  */
/*

Обновлённое задание: 

Программа на Java предназначена для удаления комментариев из Java-кода, находящегося в текстовом файле. 
Она принимает путь к файлу с кодом в качестве аргумента командной строки, очищает его от комментариев и сохраняет результат в новый файл с тем же именем, но с добавлением суффикса _output.
Удаление комментариев:
Программа должна уметь удалять однострочные комментарии, начинающиеся с //, с использованием регулярного выражения //.*?(\r?\n|$).
Многострочные комментарии, заключённые между /* и */, должны удаляться с помощью регулярного выражения /\\*.*?\\*/.
Методы программы:
removeComments(String code): Метод, который принимает строку с кодом, удаляет из неё все комментарии и возвращает очищенный код.
readFile(String filePath): Метод, который читает содержимое файла построчно с использованием BufferedReader, собирает его в StringBuilder и возвращает как строку.
writeFile(String filePath, String content): Метод, который сохраняет очищенный код в выходной файл.

*/

public class delete_coment {
public static void main(String[] args) {
        // Проверка наличия аргументов командной строки
        if (args.length < 1) {
            System.out.println("Укажите путь к файлу с Java кодом.");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = "output.java";

        try {
            // Чтение содержимого входного файла
            String code = readFile(inputFilePath);
            // Удаление комментариев
            String cleanedCode = removeComments(code);
            // Запись очищенного кода в выходной файл
            writeFile(outputFilePath, cleanedCode);
            System.out.println("Комментарии удалены. Очищенный код записан в " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Ошибка при обработке файла: " + e.getMessage());
        }
    }

    // Метод для чтения файла
    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n"); // Добавляем строку и перевод строки
            }
        }
        return content.toString();
    }

    // Метод для удаления комментариев
    private static String removeComments(String code) {
        // Регулярные выражения для удаления комментариев
        String singleLineComment = "//.*?(\\r?\\n|$)"; // Шаблон для однострочных комментариев
        String multiLineComment = "/\\*.*?\\*/"; // Шаблон для многострочных комментариев

        // Удаление однострочных и многострочных комментариев
        code = code.replaceAll(singleLineComment, ""); // Удалить однострочные комментарии
        code = code.replaceAll(multiLineComment, ""); // Удалить многострочные комментарии

        // Удаление лишних пробелов и пустых строк
        code = code.replaceAll("\\n\\s*\\n", "\n"); // Удалить пустые строки
        return code.trim(); // Удалить пробелы в начале и конце
    }

    // Метод для записи содержимого в файл
    private static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content); // Записываем очищенный код в файл
        }
    }
}
