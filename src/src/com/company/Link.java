package src.com.company;

public class Link {

    private int startNode;
    private int endNode;
    private int numberOfModules;
    private int moduleCost;
    private int linkModule;


    public int getStartNode() {
        return startNode;

    }

    public void setStartNode(int newStart) {
        this.startNode = newStart;
    }

    public int getEndNode() {
        return endNode;

    }

    public void setEndNode(int newEnd) {
        this.endNode = newEnd;
    }
    public int getNumberOfModules() {
        return numberOfModules;

    }

    public void setNumberOfModules(int newNumberOfModules) {
        this.numberOfModules = newNumberOfModules;
    }
    public int getModuleCost() {
        return moduleCost;

    }

    public void setModuleCost(int newCost) {
        this.moduleCost = newCost;
    }
    public int getLinkModule() {
        return linkModule;

    }

    public void setLinkModule(int newLinkModule) {
        this.linkModule = newLinkModule;
    }
}