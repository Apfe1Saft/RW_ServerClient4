package collection.loader;

import data.*;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@param class works with Vector : loading from File, transform and contain
 */
public class Loader implements Reading {
    private final String file;
    private final Vector <LabWork> vector = new Vector<>( 0);
    private LocalDate VectorDate = LocalDate.now();
    private Long ID = 0L;
    public Loader(String file){
        this.file = file;
    }
    // вывод вектора в формате строки
    public Vector<LabWork> ShowVector(){
        return vector;
    }
    /**
     * {@return Vector which was read from File
     */
    public void setID(){ID++;}
    public Long getID() {
        setID();
        return ID-1;
    }
    public LocalDate ShowVectorDate(){
        return VectorDate;
    }
    /**
     * {@return time when Vector was read
     */
    @Override
    // чтение данных из файла data.xml
    public void reading() throws IOException {
        try {
            Scanner scanner = new Scanner(Paths.get(file));
            long id = 0L;
            Difficulty difficulty = Difficulty.VERY_EASY;
            LocalDate creationDate = LocalDate.now();
            String name;
            long minimalPoint ;
            int maximumPoint ;
            Discipline discipline ;
            Coordinates coordinates ;
            if (scanner.hasNext()) {
                String line = scanner.nextLine();
                while (!line.contains("LabWork") && scanner.hasNext()) {
                    if (line.contains("<VectorDate>")) {
                        VectorDate = LocalDate.parse(xmlCheckerMedium(line));
                    }
                    line = scanner.nextLine();
                }
                while (scanner.hasNext() && !line.contains("</LabWork>")) {
                    line = scanner.nextLine();
                    name = "";
                    minimalPoint = 0L;
                    maximumPoint = 0;
                    discipline = new Discipline();
                    coordinates = new Coordinates(0.0, 0);
                    if (!line.contains("</LabWork>")) {
                        id++;
                        String WorkName = xmlChecker(line);
                        //java.lang.System.out.println(" WorkName =" + WorkName);
                        while (scanner.hasNext() && !line.contains("/" + WorkName)) {
                            line = scanner.nextLine();
                            line = line.replace(" ", "");
                            String data = xmlChecker(line);

                            switch (data) {
                                case "creationDate":
                                    creationDate = LocalDate.parse(xmlCheckerMedium(line));
                                    break;
                                case "name": {
                                    name = xmlCheckerMedium(line);
                                    //java.lang.System.out.println("name =" + name);
                                }

                                break;
                                case "coordinates": {
                                    while (!line.contains("/coordinates")) {
                                        if (line.contains("x")) {
                                            line = xmlCheckerMedium(line);
                                            //java.lang.System.out.println(line);
                                            coordinates.setX(Double.parseDouble(line));
                                        }
                                        if (line.contains("y")) {
                                            line = xmlCheckerMedium(line);
                                            coordinates.setY(Integer.parseInt(line));
                                            //java.lang.System.out.println(line);
                                        }
                                        line = scanner.nextLine();
                                    }

                                }

                                break;
                                case "minimalPoint": {
                                    line = xmlCheckerMedium(line);
                                    minimalPoint = Long.parseLong(line);
                                    //java.lang.System.out.println(line);


                                }

                                break;
                                case "maximumPoint": {
                                    line = xmlCheckerMedium(line);
                                    maximumPoint = Integer.parseInt(line);
                                    //java.lang.System.out.println(line);
                                }

                                break;
                                case "difficulty": {
                                    switch (xmlCheckerMedium(line)) {
                                        case "VERY_EASY":
                                            difficulty = Difficulty.VERY_EASY;
                                            break;
                                        case "NORMAL":
                                            difficulty = Difficulty.NORMAL;
                                            break;
                                        case "IMPOSSIBLE":
                                            difficulty = Difficulty.IMPOSSIBLE;
                                            break;
                                        case "INSANE":
                                            difficulty = Difficulty.INSANE;
                                            break;
                                        case "TERRIBLE":
                                            difficulty = Difficulty.TERRIBLE;
                                            break;
                                    }
                                    //java.lang.System.out.println("difficulty =" + difficulty);
                                }

                                break;
                                case "discipline": {

                                    while (!line.contains("/discipline")) {
                                        if (line.contains("name")) {
                                            discipline.setName(xmlCheckerMedium(line));
                                            //java.lang.System.out.println(discipline.getName());
                                        }
                                        if (line.contains("practiceHours")) {
                                            line = xmlCheckerMedium(line);
                                            discipline.setPracticeHours(Long.parseLong(line));
                                            //java.lang.System.out.println(discipline.getPracticeHours());
                                        }
                                        line = scanner.nextLine();
                                    }

                                }
                                break;
                                case "id": {
                                    line = xmlCheckerMedium(line);
                                    id = Long.parseLong(line);
                                }

                                break;
                                default:
                            }


                        }
                        vector.add(Creator(id, name, coordinates, creationDate, minimalPoint, maximumPoint, difficulty, discipline));
                    }
                }
            }
        } catch (NoSuchFileException e) {
            System.out.println("Не обнаружен файл для хранения data.xml");
        }
    }
    // нахождение первого слова
    @Override
    public String  xmlChecker(String line) {
        Pattern pattern = Pattern.compile("<.+?>");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            line = line.substring(matcher.start(), matcher.end());
        }
        line =line.replace("<","");
        line =line.replace(">","");
        return line;
    }
    // нахождение второго слова
    @Override
    public String xmlCheckerMedium(String line) {
        Pattern pattern = Pattern.compile(">.+?<");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            line = line.substring(matcher.start(), matcher.end());
        }
        line =line.replace("<","");
        line =line.replace(">","");
        return line;
    }
    // создание Vector по данным из файла
    @Override
    public LabWork Creator(Long id, String name, Coordinates coordinates, LocalDate creationDate, Long minimalPoint, Integer maximumPoint, Difficulty difficulty, Discipline discipline) {
        return new LabWork(id,name,coordinates,creationDate,minimalPoint,maximumPoint,difficulty,discipline);
    }

}