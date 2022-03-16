import java.io.*;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {

        PlayingField playingField = new PlayingField();
        String[] cellNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuilder tuturial1 = new StringBuilder("Правила игры" + "\n" +
                "Каждый игрок поочередно вводит номер ячейки, от 1 до 9" +
                " в соответствии со схемой:");
        System.out.println(tuturial1);
        playingField.playingField(cellNumbers);
        ConsoleInput consoleInput = new ConsoleInput();
        System.out.println("Игрок 1, введите своё имя");
        Person plaer1 = new Person(consoleInput.enterData());
        System.out.println("Игрок 2, введите своё имя");
        Person plaer2 = new Person(consoleInput.enterData());

        String nameXMLfile= plaer1.name+"_VS_" + plaer2.name;
        System.out.println(nameXMLfile);

        ArrayList<String[]> steps=null;// создаем маасив, который будет описывать ходы игроков

        boolean playAgain = true;
        while (playAgain) {

            steps=new ArrayList();
            String[] winner=null;

            String dash = "-";
            String[] moves = {dash, dash, dash, dash, dash, dash, dash, dash, dash};

            IsWinner iswinner = new IsWinner();
            for (int i = 0; i < moves.length; i++) {
                playingField.playingField(moves);
                if (i % 2 == 0) {
                    String[] idAndMove=new String[2]; //массив, описывающий конкретный ход: ID и номер ячейки
                    System.out.println("Ход игрока " + plaer1.name);
                    while (true) {
                        int j = consoleInput.enterMove()-1;
                        if (moves[j].equals(dash)) {
                            moves[j] = "X";
                            idAndMove[0]="1";
                            idAndMove[1]=j+1+"";
                            steps.add(i, idAndMove); //инициализируем массив steps ходом под номером i
                            break;
                        }
                        else System.out.println("Ячейка уже занята. Введите номер другой ячейки");
                    }
                }
                if (iswinner.isWinner(moves)) {
                    playingField.playingField(moves);

                    System.out.println("Победил игрок " + plaer1.name);
                    String[] win ={"1", plaer1.name, "X"};
                    winner=win;
                    plaer1.wins();
                    plaer2.defeats();
                    break;
                }

                if (i % 2 == 1) {
                    String[] idAndMove=new String[2]; //массив, описывающий конкретный ход: ID и номер ячейки
                    System.out.println("Ход игрока " + plaer2.name);
                    while (true) {
                        int j = consoleInput.enterMove()-1;
                        if (moves[j].equals(dash)) {
                            moves[j] = "0";
                            idAndMove[0]="2";
                            idAndMove[1]=j+1+"";
                            steps.add(i, idAndMove); //инициализируем массив steps ходом под номером i
                            break;
                        }
                        else System.out.println("Ячейка уже занята. Введите номер другой ячейки");
                    }
                    if (iswinner.isWinner(moves)) {
                        playingField.playingField(moves);

                        System.out.println("Победил игрок " + plaer2.name);
                        String[] win ={"2", plaer2.name, "0"};
                        winner=win;
                        plaer2.wins();
                        plaer1.defeats();
                        break;
                    }
                }
                if (i == moves.length - 1) {
                    System.out.println("Ничья");
                    String[] win ={"Draw!"};
                    winner=win;
                    playingField.playingField(moves);
                    plaer1.drawResult();
                    plaer2.drawResult();
                }
            }
            String resultPlaer1 ="Игрок - " + plaer1.name+"||"+"игр - "+plaer1.getGames()+"||"+"побед - "+
                    plaer1.getWins()+"||"+"Поражений - "+plaer1.getDefeats()+"||"+"Ничьих - "+plaer1.getDefeats();

            String resultPlaer2 ="Игрок - " + plaer2.name+"||"+"игр - "+plaer2.getGames()+"||"+"побед - "+
                    plaer2.getWins()+"||"+"Поражений - "+plaer2.getDefeats()+"||"+"Ничьих - "+plaer2.getDefeats();

            try(FileWriter writer = new FileWriter("result.txt", true))
            {
                writer.write(resultPlaer1);
                writer.append('\n');
                writer.write(resultPlaer2);
                writer.append('\n');
                writer.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }

            ///////create XML file
            nameXMLfile=nameXMLfile+playingField.addTimeOfGame();
            new WriteXmlStAXCursor().createXMLfile(nameXMLfile, plaer1.name, plaer2.name, steps, winner);



            System.out.println("Сыграть ещё раз Y-да/ N- нет");
            String answer;
            while (true) {
                answer = consoleInput.enterData();
                if (answer.equals("Y")) {nameXMLfile= plaer1.name+"_VS_" + plaer2.name;
                    break;}
                if (answer.equals("N")) {
                    playAgain = false;
                    System.out.println("Игра закончена!");
                    break;
                } else {
                    System.out.println("Вниматетельнее вводите ответ!");
                    System.out.println("Сыграть ещё раз? Введите Y-да/ N- нет");
                }
            }

        }
        //////////////////////////////////////////////////
        consoleInput.readerClose();


    }


}




