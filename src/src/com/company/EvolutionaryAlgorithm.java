package src.com.company;





import java.util.*;
import java.util.stream.Collectors;


import static java.lang.Character.getNumericValue;

public class EvolutionaryAlgorithm {
    private int numberOfMutations;

    /*
        private int sizeOfInitialPopulation;
        private double pMutation;
        private double pCrossover;
        private long endTime;
        private int endNumberOfGeneration;
        private int maxNumberOfGenerationWithoutImprovement;

        //private long seed;
        private double percentOfBestSolutions;
        private ArrayList<Link> links;
        private ArrayList<Demand>demands;
        private Random random;
        */
    private ArrayList<Solution> bestSolutionFromEveryGeneration;

    public ArrayList<Solution> getBestSolutionsFromEveryGeneration() {
        return bestSolutionFromEveryGeneration;
    }


    public EvolutionaryAlgorithm(int numberOfMutations, ArrayList<Solution> bestSolutionFromEveryGeneration) {//( int sizeOfInitialPopulation, double pMutation, double pCrossover, long endTime, int endNumberOfGeneration, int endNumberOfMutation, int maxNumberOfGenerationWithoutImprovement, long seed, double percentOfBestSolutions, ArrayList<Link >links, ArrayList<Demand>demands, ArrayList<Solution> bestSolutionsFromEveryGeneration){
  /*  this.sizeOfInitialPopulation= sizeOfInitialPopulation;
    this.pMutation=pMutation;
    this.pCrossover=pCrossover;
    this.endTime=endTime;
    this.endNumberOfGeneration=endNumberOfGeneration;
    this.numberOfMutations=numberOfMutations;
    this.maxNumberOfGenerationWithoutImprovement=maxNumberOfGenerationWithoutImprovement;
    this.links=links;
    this.demands=demands;
    this.random = new Random(seed);*/
        this.numberOfMutations = numberOfMutations;

        this.bestSolutionFromEveryGeneration = bestSolutionFromEveryGeneration;

    }






    public  Solution DDAP( int sizeOfInitialPopulation, long seed, ArrayList<Demand> demands, ArrayList<Link>links, double percentOfBestSolutions, double pCrossover, double pMutation, double endTime, int maxNumberOfGenerations, int maxNumberOfGenerationWithoutImprovement, int maxNumberOfMutation , ArrayList<Solution> bestSolutionFromEveryGeneration){
        List<Solution> population = getInitialSolutions(sizeOfInitialPopulation,seed,demands, links);
//ArrayList<Solution> bestSolutionFromEveryGeneration= new ArrayList<>();
        Solution bestSolution = new Solution();

        int numberOfGeneration=0;
        int numberOfGenerationWithoutImprovement=0;
       // int numberOfMutations=0;


        int bestCost =Integer.MAX_VALUE;

        int cost=0;
//        System.out.println("start");
        endTime = System.currentTimeMillis() + endTime * 1000;
        while ((stopCriterion(endTime,maxNumberOfGenerations, maxNumberOfGenerationWithoutImprovement, maxNumberOfMutation,numberOfGeneration, numberOfGenerationWithoutImprovement ))) {
            numberOfGeneration++;
           // System.out.println(numberOfGeneration+" gneracjaaaaaaaa");

            Solution bestSolutionOfGeneration = new Solution();
            int bestCostOfGeneration =Integer.MAX_VALUE;
            for (Solution solution : population) {
                cost=0;
                for (int a = 0; a < solution.getVolumeAllocatedOnLinks().size(); a++) {

                    cost =cost+solution.getVolumeAllocatedOnLinks().get(a)/(links.get(a).getLinkModule())*links.get(a).getModuleCost() ;///solution.getVolumeAllocatedOnLinks().get(a) * links.get(a).getModuleCost();

                }

                solution.setCost(cost);
             //   System.out.println(cost+ "   solutions cost");
                if (cost < bestCostOfGeneration) {
                    bestCostOfGeneration = cost;
                    bestSolutionOfGeneration = solution;

                }


            }
            bestSolutionFromEveryGeneration.add(bestSolutionOfGeneration);
            if (bestCostOfGeneration < bestCost) {
                bestCost = bestCostOfGeneration;
                bestSolution = bestSolutionOfGeneration;
                numberOfGenerationWithoutImprovement = 0;
            } else {
                numberOfGenerationWithoutImprovement++;
            }
            population= getBestDDAP(population, percentOfBestSolutions);
            population= crossover( population, seed, pCrossover, demands, links);
            population = mutation(population, pMutation, seed, demands, links);



        }

        return bestSolution;
    }




    public static List<Solution> getBestDDAP(List<Solution> allSolutions, double percentOfBestSolutions) {

        int subListEnd = (int) Math.round(allSolutions.size() * (percentOfBestSolutions / 100));
        List<Solution> list0 = allSolutions.stream()
                .sorted(Comparator.comparing(Solution::getCost))
                .collect(Collectors.toList());

        List<Solution> list = allSolutions.stream()
                .sorted(Comparator.comparing(Solution::getCost))
                .collect(Collectors.toList())
                .subList(0, (int) Math.round(allSolutions.size() * (percentOfBestSolutions / 100)));


        list.addAll(list0.subList(0, allSolutions.size() - subListEnd));

        return list;
    }


    public Solution DAP( int sizeOfInitialPopulation, long seed, ArrayList<Demand> demands, ArrayList<Link>links, double percentOfBestSolutions, double pCrossover, double pMutation,double endTime,int maxNumberOfGenerations, int maxNumberOfGenerationWithoutImprovement, int maxNumberOfMutation,  ArrayList<Solution> bestSolutionFromEveryGeneration){
        List<Solution> population= getInitialSolutions(sizeOfInitialPopulation,seed, demands , links);
        Solution bestSolution = new Solution();
        int maxValue= Integer.MAX_VALUE;
        int numberOfGeneration=0;
        int numberOfGenerationWithoutImprovement=0;

        endTime = System.currentTimeMillis() + endTime * 1000;
        while (stopCriterion(endTime,maxNumberOfGenerations, maxNumberOfGenerationWithoutImprovement, maxNumberOfMutation,numberOfGeneration, numberOfGenerationWithoutImprovement )) {
            numberOfGeneration++;
            Solution bestSolutionOfGeneration = new Solution();
            int maxValueOfGeneration= Integer.MAX_VALUE;

            for (Solution solution : population) {

                ArrayList<Integer> linksOverload = new ArrayList<>();
int cost=0;
                int n=0;
                for (int a = 0; a < solution.getVolumeAllocatedOnLinks().size(); a++) {
                    if(cost<(solution.getVolumeAllocatedOnLinks().get(a) -( links.get(a).getNumberOfModules()*links.get(a).getLinkModule())));
                    cost=(solution.getVolumeAllocatedOnLinks().get(a) -( links.get(a).getNumberOfModules()*links.get(a).getLinkModule()));
                    /*
                    if(solution.getVolumeAllocatedOnLinks().get(a) - links.get(a).getNumberOfModules()>0){
                        n++;
                    }
*/
                }
                solution.setCost(cost);
                solution.setNumberOfLinksWithExceededCapacity(n);
                if (cost < maxValue) {
                    maxValueOfGeneration = cost;
                    bestSolutionOfGeneration = solution;

                }
            }
            bestSolutionFromEveryGeneration.add(bestSolutionOfGeneration);
            if (maxValueOfGeneration < maxValue) {
                maxValue = maxValueOfGeneration;
                bestSolution = bestSolutionOfGeneration;
                numberOfGenerationWithoutImprovement = 0;
            } else {
                numberOfGenerationWithoutImprovement++;
            }
            population= getBestDAP(population, percentOfBestSolutions);
            population= crossover( population, seed, pCrossover, demands, links);
            population = mutation(population, pMutation, seed, demands, links);




        }



        return bestSolution;
    }

    public List<Solution> mutation(List<Solution> population, double pMutation, long seed, ArrayList<Demand> demands, ArrayList<Link> links) {
        List<Solution> mutatedPopulation = new ArrayList<>();

        Random rand = new Random();
        // int numbOfMutations = 0;


        for (Solution s : population) {
            Solution newSolution = new Solution();
            if ((double) rand.nextInt(100) / 100 < pMutation) {
                this.numberOfMutations++;

                List<int[]> newChromosome = new ArrayList<>();
                for (int b = 0; b < s.getChromosome().size(); b++) {
                    int[] temp = new int[s.getChromosome().get(b).length];
                    for (int c = 0; c < s.getChromosome().get(b).length; c++) {
                        temp[c] = 0;

                    }
                    newChromosome.add(temp);
                }
                for (int a = 0; a < s.getChromosome().size(); a++) {

                    newChromosome.set(a, mutateGene(s.getChromosome().get(a), pMutation, seed));

                }
                newSolution.setChromosome(newChromosome);
                newSolution.setVolumeAllocatedOnLinks(Solution.calculateVolumeAllocationOnLinks(newSolution.getChromosome(), demands, links));
                mutatedPopulation.add(newSolution);

            } else
                mutatedPopulation.add(s);


        }
        return mutatedPopulation;

    }

    public static int[] mutateGene(int[] gene, double pMutation, long seed) {
        Random rand = new Random(seed);

        for (int a = 0; a < gene.length; a++) {
            int index1 = rand.nextInt(gene.length);
            int index2 = rand.nextInt(gene.length);

            if (gene[index1] > 0) {
                gene[index1]--;
                gene[index2]++;

            }

        }


        return gene;
    }


    public static List<Solution> crossover(List<Solution> population, long seed, double pCrossover, ArrayList<Demand> demands, ArrayList<Link> links) {
        List<Solution> children = new ArrayList<Solution>();
        Random random = new Random(seed);

        for (int a = 0; a < population.size() / 2; a++) {
            children.addAll(crossParents(population.remove(random.nextInt(population.size())), population.remove(random.nextInt(population.size())), pCrossover, seed, demands, links));

        }


        return children;
    }


    public static ArrayList<Solution> crossParents(Solution parent1, Solution parent2, double pCrossover, long seed, ArrayList<Demand> demands, ArrayList<Link> links) {
        ArrayList<Solution> children = new ArrayList<Solution>();
        Solution child1 = new Solution();
        Solution child2 = new Solution();
        List<int[]> chromosome1 = new ArrayList<>();
        List<int[]> chromosome2 = new ArrayList<>();

        Random rand = new Random(seed);
        double prob = (double) rand.nextInt(100) / 100;


        if (prob < pCrossover) {

            for (int a = 0; a < parent1.getChromosome().size(); a++) {
                int[] temp = new int[parent1.getChromosome().get(a).length];
                for (int b = 0; b < parent1.getChromosome().get(a).length; b++) {
                    temp[b] = 0;

                }
                chromosome1.add(temp);
                chromosome2.add(temp);
                prob = rand.nextFloat();


                if (prob < 0.5) {
                    chromosome1.set(a, parent2.getChromosome().get(a));
                    chromosome2.set(a, parent1.getChromosome().get(a));


                } else {
                    chromosome1.set(a, parent1.getChromosome().get(a));
                    chromosome2.set(a, parent2.getChromosome().get(a));

                }

            }
            child1.setChromosome(chromosome1);
            child2.setChromosome(chromosome2);
            child1.setVolumeAllocatedOnLinks(Solution.calculateVolumeAllocationOnLinks(child1.getChromosome(), demands, links));
            child2.setVolumeAllocatedOnLinks(Solution.calculateVolumeAllocationOnLinks(child2.getChromosome(), demands, links));

            children.add(child1);
            children.add(child2);


        } else {
            children.add(parent1);
            children.add(parent2);
        }


        return children;
    }


    public static List<Solution> getBestDAP(List<Solution> allSolutions, double percentOfBestSolutions) {

        int subListEnd = (int) Math.round(allSolutions.size() * (percentOfBestSolutions / 100));
        List<Solution> list0 = allSolutions.stream()
                .sorted(Comparator.comparing(Solution::getCost))
                .collect(Collectors.toList());

        List<Solution> list = allSolutions.stream()
                .sorted(Comparator.comparing(Solution::getCost))
                .collect(Collectors.toList())
                .subList(0, (int) Math.round(allSolutions.size() * (percentOfBestSolutions / 100)));


        list.addAll(list0.subList(0, allSolutions.size() - subListEnd));

        return list;
    }

    private boolean stopCriterion(double endTime, int maxNumberOfGenerations, int maxNumberOfGenerationWithoutImprovement, int maxNumberOfMutation, int currentGeneration, int currentNumberOfGenerationsWithoutImprovement) {

        if (System.currentTimeMillis() >= endTime) {
  //          System.out.println("end time");
            return false;
        }
        if (currentGeneration >= maxNumberOfGenerations) {
    //        System.out.println("too many generation");
            return false;
        }
        if (this.numberOfMutations >= maxNumberOfMutation) {
      //      System.out.println("mutationsssssssssss");
            return false;
        }
        if (currentNumberOfGenerationsWithoutImprovement >= maxNumberOfGenerationWithoutImprovement) {
        //    System.out.println("no improvement");

            return false;
        }
        return true;
    }

    public static ArrayList<Solution> getInitialSolutions(int sizeOfPopulation, long seed ,ArrayList<Demand> demands, ArrayList<Link>links){
        ArrayList<Solution> solutions= new ArrayList<>();
        List<List<int[]>> chromosomes=new ArrayList<>() ;
        chromosomes= getInitialPopulation(sizeOfPopulation, seed, demands);

        for(int a=0; a<chromosomes.size();a++){
            Solution solution =new Solution();
            solution.setChromosome(chromosomes.get(a));
            solution.setVolumeAllocatedOnLinks(Solution.calculateVolumeAllocationOnLinks(chromosomes.get(a),demands, links));
            solutions.add(solution);
        }


        return solutions;
    }


    public static List<List<int[]>> getInitialPopulation(int numberOfChromosomes, long seed, ArrayList<Demand> demands) {
        List<List<int[]>> lists = new ArrayList<>();
        Random random = new Random(seed);


        for (int a = 0; a < demands.size(); a++) {
            List<int[]> comb = allCombinationsForDemand(demands.get(a).getVolume(), demands.get(a).getPaths().size());
            lists.add(comb);


        }
        Set<List<int[]>> combinations = new HashSet<>();
        while (combinations.size() < numberOfChromosomes) {
            List<int[]> chromosome = new ArrayList<>();
            for (int b = 0; b < demands.size(); b++) {
                chromosome.add(lists.get(b).get(random.nextInt(lists.get(b).size())));


            }
            combinations.add(chromosome);


        }

        List<List<int[]>> finalCombinations = new ArrayList<>(combinations);

        return finalCombinations;
    }

    /*
    //ArrayList<Solution> allSolutions = allPossibleSolutions(demands);
    ArrayList<Solution> initialPopulation = new ArrayList<>();
    Random random =new Random(seed);

    for(int a= 0; a< numberOfChromosomes; a++){
        initialPopulation.add(allSolutions.get(random.nextInt(allSolutions.size())));


    }



    return initialPopulation;
}*/
    public static ArrayList<int[]> allCombinationsForDemand(final int n, final int k) {
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
            String[] temp = res.get(b).split((",|]"));
            ta.add(temp);
        }
        ArrayList<int[]> ok = new ArrayList<>();
        for (int c = 0; c < ta.size(); c++) {
            int[] temp = new int[ta.get(c).length - 1];
            for (int d = 0; d < ta.get(c).length - 1; d++) {
                temp[d] = Integer.parseInt(ta.get(c)[d + 1]);

            }
            ok.add(temp);


        }
        return ok;
    }}