package collection.writer;

import data.LabWork;

import java.io.FileNotFoundException;
import java.util.Vector;

public interface Writing {
    void writing(String file, Vector<LabWork> vector) throws FileNotFoundException;
}
