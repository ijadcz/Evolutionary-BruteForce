package src.com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {

    private List<int[]> chromosome;
    private List<Integer> volumeAllocatedOnLinks;
    private int numberOfLinksWithExceededCapacity;
    private int cost;

    public void setNumberOfLinksWithExceededCapacity( int newNumber){
        this.numberOfLinksWithExceededCapacity=newNumber;
    }

    public int getNumberOfLinksWithExceededCapacity()
    {
        return numberOfLinksWithExceededCapacity;
    }
    public void setChromosome(List<int[]> newChromosome){
        this.chromosome=newChromosome;
    }
    public void setVolumeAllocatedOnLinks(List<Integer> newAllocation){
        this.volumeAllocatedOnLinks=newAllocation;
    }

    public List<int[]> getChromosome(){
        return chromosome;
    }
    public List<Integer> getVolumeAllocatedOnLinks(){
        return volumeAllocatedOnLinks;
    }

    public void setCost( int newCost){
        this.cost=newCost;
    }
    public int getCost(){
        return cost;
    }



    public static List<Integer> calculateVolumeAllocationOnLinks(List<int[]>chromosome, ArrayList<Demand> demands, ArrayList<Link> links){
        List<Integer> allocation=new ArrayList<>();
       // Demand demand = Collections.max(demands, Comparator.comparing(s-> s.getNumberOfLinks()));
        for (int y=0; y<links.size()-1;y++){
            allocation.add(0);
        }


        allocation.add(0);

        for (int a=0;a<chromosome.size();a++){

            for(int b=0;b<chromosome.get(a).length; b++){ // allocation for 1 demand number if paths

                int demandVolumeOnPath= chromosome.get(a)[b];//volume on paths\


                for(int j=0; j<demands.get(a).getPaths().get(b).getLinks().length;j++)
                    allocation.set(demands.get(a).getPaths().get(b).getLinks()[j]-1, allocation.get(demands.get(a).getPaths().get(b).getLinks()[j]-1)+demandVolumeOnPath);




            }



        }










        return allocation;
    }


}













