package collection.loader;

/**
 * {@param Exception class which control Difficulty data
 */
public class DifficultyExeption extends Exception{
    public DifficultyExeption() {
        super("ВВеденное значение difficulty не является Enum и заменено на VERY_EAST");
    }
}
