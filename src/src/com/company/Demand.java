package src.com.company;

import java.util.ArrayList;

public class Demand{

    private int startNode;
    private int endNode;
    private int volume;
    private ArrayList<Path> paths;
    private int ID;
    private int numberOfLinks;


    public int getDemandStartNode() {
        return startNode;

    }

    public void setDemandStartNode(int newStart) {
        this.startNode = newStart;
    }

    public int getDemandEndNode() {
        return endNode;

    }

    public void setDemandEndNode(int newEnd) {
        this.endNode = newEnd;
    }


    public void setPaths(ArrayList<Path> newPaths) {
        this.paths = newPaths;
    }

    public ArrayList<Path> getPaths() {
        return paths;

    }




    public void setVolume(int newVolume) {
        this.volume = newVolume;
    }


    public int getVolume() {
        return volume;

    }
    public void setID(int newID) {
        this.ID = newID;
    }


    public int getID() {
        return ID;

    }

    public int getNumberOfLinks() {
        return numberOfLinks;

    }

    public void setNumberOfLinks(int newNumber) {
        this.numberOfLinks = newNumber;
    }
}

