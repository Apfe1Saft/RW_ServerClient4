package collection.loader;

import data.*;

import java.io.IOException;

interface Reading {
    void reading() throws IOException;
    String xmlChecker(String file);
    String xmlCheckerMedium(String file);
    LabWork Creator(Long id , String name
            , Coordinates coordinates , java.time.LocalDate creationDate,
                    Long minimalPoint , Integer maximumPoint, Difficulty difficulty , Discipline discipline);
}
