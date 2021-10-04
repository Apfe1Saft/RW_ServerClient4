package collection;


import collection.loader.Loader;
import collection.writer.Writer;
import data.LabWork;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Vector;

public class Collection {
    private Vector <LabWork> vector;
    private final String fileName;
    private LocalDate VectorDate ;
    private Long MaxId;

    public Collection(String file) throws IOException {
        fileName = file;
        fileLoading(file);
    }
    public long MaxID(){
        long Max = 0;
        for(LabWork i:vector){
            if(i.getId()>Max) Max = i.getId();
        }
        return Max;
    }

    @Override
    public String toString() {
        StringBuilder vecSt = new StringBuilder();
        for(LabWork i : vector){
            vecSt.append(i.toString()).append("\n");
        }
        return "Collection{\n" +
                "vector{\n" + vecSt + "},\n" +
                " fileName = '" + fileName + '\'' +
                ", VectorDate = " + VectorDate +
                ", MaxId = "+MaxId+
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public Vector<LabWork> getVector() {
        return vector;
    }

    public void setVector(Vector<LabWork> vector) {
        this.vector = vector;
    }

    private void fileLoading(String fileName) throws IOException {
        Loader loader = new Loader(fileName);
        loader.reading();
        setVector(loader.ShowVector());
        setVectorDate(loader.ShowVectorDate());
        this.MaxId =loader.getID();
    }

    public Long getMaxId() {
        return MaxID()+1;
    }

    public LocalDate getVectorDate() {
        return VectorDate;
    }

    public void setVectorDate(LocalDate vectorDate) {
        VectorDate = vectorDate;
    }

    public void fileSaving(){

        Writer writer = new Writer();
        writer.writing(fileName,getVector());
    }
}
