import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Searching files by specific criteria.
 * Use the console to work with this program.
 */
public class Main {
    private static void searchFiles() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Path> filesWithExceed = new ArrayList<>();

        System.out.print("+---------------------------+\n" +
                         "| Поиск файлов по критериям |\n" +
                         "+---------------------------+\n\n");

        System.out.print("Введите путь: ");
        String path = reader.readLine();

        System.out.print("Введите минимальный размер файла в байтах: ");
        long minSize = Long.parseLong(reader.readLine());

        Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (attrs.size() >= minSize) {
                    filesWithExceed.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });

        if (!filesWithExceed.isEmpty()) {
            System.out.println("Найдены файлы:");
            for (Path currentPath : filesWithExceed) {
                System.out.println(currentPath);
            }
        } else {
            System.out.println("Файлы не найдены.");
        }
    }

    public static void main(String[] args) {
        try {
            searchFiles();
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода!");
            e.printStackTrace();
        }
    }
}
