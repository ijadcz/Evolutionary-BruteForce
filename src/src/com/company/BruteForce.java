package src.com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Character.getNumericValue;

public class BruteForce {



    public static Solution DDAP(ArrayList<Demand> demands, ArrayList<Link> links, ArrayList<Solution> allPossible){
        ArrayList<Solution> solutions= allPossibleSolutions(demands, links);
        int cost;
        Solution bestSolution= new Solution();
        int bestCost =Integer.MAX_VALUE;

        for(Solution solution:solutions){

            cost=0;
            for(int a=0; a<solution.getVolumeAllocatedOnLinks().size();a++){

                cost = (int) (cost+ Math.ceil((float)solution.getVolumeAllocatedOnLinks().get(a)/(float)(links.get(a).getLinkModule()))*links.get(a).getModuleCost());
               // cost= cost+solution.getVolumeAllocatedOnLinks().get(a)*links.get(a).getModuleCost(); /// ??????? solution.getVolumeAllocatedOnLinks()/links.get(a).getLinkModule()*links.get(a).getModuleCost()

            }
                solution.setCost(cost);
            if(cost < bestCost){
                bestCost=cost;
                bestSolution=solution;

            }



            allPossible.add(solution);
        }


        return bestSolution;
    }



    public static Solution DAP(ArrayList<Demand> demands, ArrayList<Link> links, ArrayList<Solution> allPossible){
        ArrayList<Solution> solutions = allPossibleSolutions(demands, links);

        int maxValue= Integer.MAX_VALUE;
        Solution bestSolution= new Solution();

        for(Solution solution:solutions){

            ArrayList<Integer> linksOverload = new ArrayList<>();

            int cost;
            int n=0;
            for (int a = 0; a < solution.getVolumeAllocatedOnLinks().size(); a++) {
                 linksOverload.add(solution.getVolumeAllocatedOnLinks().get(a) -( links.get(a).getNumberOfModules()*links.get(a).getLinkModule()));



            }
            cost= Collections.max(linksOverload);
            solution.setCost(cost);
            if (cost<maxValue){
                maxValue=cost;
                bestSolution=solution;

            }
            allPossible.add(solution);







        }
        return bestSolution;



    }






    public static ArrayList<Solution> allPossibleSolutions(ArrayList<Demand> demands, ArrayList<Link> links){
        ArrayList<Solution> solutions= new ArrayList<>();
        List<List<int[]>> chromosomes;
        chromosomes= allPossibleChromosomes(demands);

        for (List<int[]> chromosome : chromosomes) {
            Solution solution = new Solution();
            solution.setChromosome(chromosome);
            solution.setVolumeAllocatedOnLinks(Solution.calculateVolumeAllocationOnLinks(chromosome, demands, links));
            solutions.add(solution);
        }


        return solutions;
    }






    private static List<List<int[]>> allPossibleChromosomes(ArrayList<Demand> demands){
        List<List<int[]>> list= new ArrayList<>();
        List<List<int[]>> chromosomes;

        for (Demand demand : demands) {
            ArrayList<int[]> demandCombinations = allCombinationsForDemand(demand.getVolume(), demand.getPaths().size());
            list.add(demandCombinations);

        }
        chromosomes=calculateCartesianProduct(list);



        return chromosomes;
    }



    private static List<List<int[]>> calculateCartesianProduct(List<List<int[]>> inputLists) {
        List<List<int[]>> cartesianProducts = new ArrayList<>();
        if (inputLists != null && inputLists.size() > 0) {
            // separating the list at 0th index
            List<int[]> initialList = inputLists.get(0);
            // recursive call
            List<List<int[]>> remainingLists = calculateCartesianProduct(inputLists.subList(1, inputLists.size()));
            // calculating the cartesian product
            initialList.forEach(element -> remainingLists.forEach(remainingList -> {
                ArrayList<int[]> cartesianProduct = new ArrayList<>();
                cartesianProduct.add(element);
                cartesianProduct.addAll(remainingList);
                cartesianProducts.add(cartesianProduct);
            }));
        } else {
            // Base Condition for Recursion (returning empty List as only element)
            cartesianProducts.add(new ArrayList<>());
        }
        return cartesianProducts;
    }


    private static ArrayList<int[]> allCombinationsForDemand(final int n, final int k) {
        ArrayList<String> res = new ArrayList<>();
        int[] input = new int[k + 1];
        Boolean mtc = Boolean.FALSE;
        int t = n;
        int h = 0;
        do {
            if (mtc) {
                if (t > 1) {
                    h = 0;
                }
                h++;
                t = input[h];
                input[h] = 0;
                input[1] = t - 1;
                input[h + 1]++;
            } else {

                input[1] = n;
                for (int i = 2; i <= k; i++) {
                    input[i] = 0;
                }
            }
            res.add(java.util.Arrays.toString(input));
            mtc = input[k] != n;
        } while (mtc);


        ArrayList<String> allCombinations = new ArrayList<>();
        for (int a = 0; a < res.size(); a++) {
            res.set(a, res.get(a).replaceAll(" ", ""));
            allCombinations.add(res.get(a));
        }

        ArrayList<String[]> ta = new ArrayList<>();
        for (int b = 0; b < allCombinations.size(); b++) {
            String[] temp = res.get(b).split(("[,\\]]"));
            ta.add(temp);
        }
        ArrayList<int[]> ok = new ArrayList<>();
        for (String[] strings : ta) {
            int[] temp = new int[strings.length - 1];
            for (int d = 0; d < strings.length - 1; d++) {
                temp[d] = Integer.parseInt(strings[d + 1]);

            }
            ok.add(temp);


        }
        return ok;
    }
}
