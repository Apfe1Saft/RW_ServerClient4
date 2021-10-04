package Commands;

import data.LabWork;

import java.io.Serializable;

public class Acommand implements Serializable {
    private static final long serialVersionUID = 32L;
    private final String comname;
    private final LabWork labWork;
    public Acommand(String name , LabWork labWork){
        this.comname =  name;
        this.labWork = labWork;
    }
    public Acommand(String name ){
        this.comname =  name;
        this.labWork = new LabWork();
    }

    public LabWork getLabWork() {
        return labWork;
    }

    public String getComname() {
        return comname;
    }
}
