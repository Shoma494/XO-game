import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String enterData() throws IOException {
        String data = reader.readLine();

        return data;
    }


    public int enterMove() throws IOException { //метод считывания хода игрока

        int dataToInt = 0;
        while (true) {

            String data = reader.readLine();

            try {
                dataToInt = Integer.parseInt(data);
                if (dataToInt>=1&& dataToInt<=9) break;
                else {
                    System.out.println("Число должно быть от 1 до 9");
                    continue;}
            } catch (NumberFormatException e) {
                System.out.println("Нам тут не до шуток! Быстро вводи число от 1 до 9!");
            }
        }

        return dataToInt;

    }

    public void readerClose() throws IOException {
        reader.close();
    }
}
