public class IsWinner {
    boolean winnerDetermined = false;

    public boolean isWinner(String[] str) {//метод определения победителя. Возвращает true, если все символы по линии равны, но не равны тире

        try {
            if (      //Проверка на заполнение строк, столбцов и диагоналей
                    !str[0].equals("-") && str[0].equals(str[1]) && str[0].equals(str[2]) ||
                            !str[3].equals("-") && str[3].equals(str[4]) && str[3].equals(str[5]) ||
                            !str[6].equals("-") && str[6].equals(str[7]) && str[6].equals(str[8]) ||
                            !str[0].equals("-") && str[0].equals(str[3]) && str[0].equals(str[6]) ||
                            !str[1].equals("-") && str[1].equals(str[4]) && str[1].equals(str[7]) ||
                            !str[2].equals("-") && str[2].equals(str[5]) && str[2].equals(str[8]) ||
                            !str[0].equals("-") && str[0].equals(str[4]) && str[0].equals(str[8]) ||
                            !str[2].equals("-") && str[2].equals(str[4]) && str[2].equals(str[6])
            ) {

                System.out.println("Есть победитель");
                winnerDetermined = true;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return winnerDetermined;


    }

}
