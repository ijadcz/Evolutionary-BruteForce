package src.com.company;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {


        File file = new File("net4.txt");
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<Link> links = new ArrayList<Link>();
        ArrayList<Demand> demands = new ArrayList<Demand>();
        result = Reader.read(file);
        links = Reader.readLinks(result);
        demands = Reader.readDemands(result);

        File file2 = new File("net12_1.txt");
        ArrayList<String> result2 = new ArrayList<String>();
        ArrayList<Link> links2 = new ArrayList<Link>();
        ArrayList<Demand> demands2 = new ArrayList<Demand>();
        result2 = Reader.read(file2);
        links2 = Reader.readLinks(result2);
        demands2 = Reader.readDemands(result2);

        File file3 = new File("net12_2.txt");
        ArrayList<String> result3 = new ArrayList<String>();
        ArrayList<Link> links3 = new ArrayList<Link>();
        ArrayList<Demand> demands3 = new ArrayList<Demand>();
        result3 = Reader.read(file3);
        links3 = Reader.readLinks(result3);
        demands3 = Reader.readDemands(result3);

        Scanner scanner = new Scanner(System.in);

        int populationSize;
        double pMutation;
        double pCrossover;
        long seed;
        int numberOfGenerations=0;

        int numberOfGenerationsWithoutImprovement=0;
         int numberOfMutations=0;
         long time=0;
         int next =0;


        System.out.println("Size of initial population");
        populationSize= scanner.nextInt();
        System.out.println("Probability of mutation (0,1)");
        pMutation= scanner.nextFloat();
        System.out.println("Probability of crossover (0,1)");
        pCrossover= scanner.nextDouble();
        System.out.println("seed");
        seed=scanner.nextLong();


        System.out.println("Choice of stop criterion");
        System.out.println("1: Time");
        System.out.println("2: Number of generations ");
        System.out.println("3: Number of mutations ");
        System.out.println("4: Number of generations without improvement");


        int choice = scanner.nextInt();

        switch (choice) {
            case 1:

                System.out.println("Type max Time");
                time=scanner.nextLong();
                numberOfGenerations=Integer.MAX_VALUE;
                numberOfMutations=Integer.MAX_VALUE;
                numberOfGenerationsWithoutImprovement=Integer.MAX_VALUE;
                break;
            case 2:

                System.out.println("Type max number of generations");
                numberOfGenerations=scanner.nextInt();
                time=Long.MAX_VALUE;
                numberOfMutations=Integer.MAX_VALUE;
                numberOfGenerationsWithoutImprovement=Integer.MAX_VALUE;
                break;
            case 3:

                System.out.println("Type max number of mutations");
                numberOfMutations=scanner.nextInt();
                time=Long.MAX_VALUE;
                numberOfGenerations=Integer.MAX_VALUE;
                numberOfGenerationsWithoutImprovement=Integer.MAX_VALUE;
                break;
            case 4:

                System.out.println("Type max number of generations without improvement");
                numberOfGenerationsWithoutImprovement=scanner.nextInt();
                time=Long.MAX_VALUE;
                numberOfMutations=Integer.MAX_VALUE;
                numberOfGenerations=Integer.MAX_VALUE;
                break;

        }




        Solution brutedap = BruteForce.DAP(demands, links);
        ArrayList<Solution> bruteALLDAP = BruteForce.allPossibleSolutions(demands, links);
        ;
        ArrayList<Solution> bestDAP1 = new ArrayList<>();
        ArrayList<Solution> bestDDAP1 = new ArrayList<>();
        ArrayList<Solution> bestDAP2 = new ArrayList<>();
        ArrayList<Solution> bestDDAP2 = new ArrayList<>();
        ArrayList<Solution> bestDAP3 = new ArrayList<>();
        ArrayList<Solution> bestDDAP3 = new ArrayList<>();

        System.out.println("Solution Brute Force DAP");
        SolutionWriter.solutionWriter(brutedap);
        System.out.println("Type 1 to get next solution");
        next=scanner.nextInt();

        Solution bruteDDAP = BruteForce.DDAP(demands, links);
        System.out.println("Solution Brute Force DDAP");
        SolutionWriter.solutionWriter(bruteDDAP);

        System.out.println("Type 1 to get next solution");
        next=scanner.nextInt();

        EvolutionaryAlgorithm evDAP1 = new EvolutionaryAlgorithm(0, bestDAP1);
        Solution DAP1 =evDAP1.DAP(populationSize,seed,demands,links,0.5,pCrossover,pMutation, time, numberOfGenerations, numberOfGenerationsWithoutImprovement,numberOfMutations, bestDAP1);

        System.out.println("Solution Evolutionary DAP dla pliku net4.txt");
        SolutionWriter.solutionWriter(DAP1);
        System.out.println("Type 1 to get next solution");
        next=scanner.nextInt();
        EvolutionaryAlgorithm evDDAP1 = new EvolutionaryAlgorithm(0, bestDDAP1);
        Solution DDAP1 =evDDAP1.DDAP(populationSize,seed,demands,links,0.5,pCrossover,pMutation, time, numberOfGenerations, numberOfGenerationsWithoutImprovement,numberOfMutations, bestDDAP1);
        System.out.println("Solution Evolutionary DDAP dla pliku net4.txt");
        SolutionWriter.solutionWriter(DDAP1);
        System.out.println("Type 1 to get next solution");
        next=scanner.nextInt();
        EvolutionaryAlgorithm evDAP2 = new EvolutionaryAlgorithm(0, bestDAP2);
        Solution DAP2 =evDAP2.DAP(populationSize,seed,demands2,links2,0.5,pCrossover,pMutation, time, numberOfGenerations, numberOfGenerationsWithoutImprovement,numberOfMutations, bestDAP2);
        System.out.println("Solution Evolutionary DAP dla pliku net12_1.txt");
        SolutionWriter.solutionWriter(DAP2);
        System.out.println("Type 1 to get next solution");
        next=scanner.nextInt();
        EvolutionaryAlgorithm evDDAP2 = new EvolutionaryAlgorithm(0, bestDDAP2);
        Solution DDAP2 =evDDAP2.DDAP(populationSize,seed,demands2,links2,0.5,pCrossover,pMutation, time, numberOfGenerations, numberOfGenerationsWithoutImprovement,numberOfMutations, bestDDAP2);
        System.out.println("Solution Evolutionary DDAP dla pliku net12_1.txt");
        SolutionWriter.solutionWriter(DDAP2);
        System.out.println("Type 1 to get next solution");
        next=scanner.nextInt();
        EvolutionaryAlgorithm evDAP3 = new EvolutionaryAlgorithm(0, bestDAP3);
        Solution DAP3 =evDAP3.DAP(populationSize,seed,demands3,links3,0.5,pCrossover,pMutation, time, numberOfGenerations, numberOfGenerationsWithoutImprovement,numberOfMutations, bestDAP3);
        System.out.println("Solution Evolutrionary DAP dla pliku net12_2.txt");
        SolutionWriter.solutionWriter(DAP3);
        System.out.println("Type 1 to get next solution");
        next=scanner.nextInt();
        EvolutionaryAlgorithm evDDAP3 = new EvolutionaryAlgorithm(0, bestDDAP3);
        Solution DDAP3 =evDDAP3.DDAP(populationSize,seed,demands3,links3,0.5,pCrossover,pMutation, time, numberOfGenerations, numberOfGenerationsWithoutImprovement,numberOfMutations, bestDDAP3);
        System.out.println("Solution Evolutionary DDAP dla pliku net12_2.txt");
        SolutionWriter.solutionWriter(DDAP3);
        System.out.println("Type 1 to get next solution");
        next=scanner.nextInt();




        /*

        ArrayList<Solution> bestFromGenerations = new ArrayList<>();
        System.out.println(demands.size()+"demaaaaaaaaaaaaaaaaaaaa");
        System.out.println(links.size()+"linkssssss");

        Solution brute= BruteForce.DDAP(demands, links);

        EvolutionaryAlgorithm ev = new EvolutionaryAlgorithm(0, bestFromGenerations);
        Solution solution = ev.DAP(10000, 300, demands, links, 0.5, 0.7, 0.3, 10000, 20000, 20, 4000, bestFromGenerations);

        System.out.println(brute.getCost()+" brute cost");
        System.out.println(solution.getCost()+" ev cost");
        /*
        for(int a =0; a<solution.getVolumeAllocatedOnLinks().size(); a++){
            System.out.println(solution.getVolumeAllocatedOnLinks().get(a));


        }*/
    }}
/*
        ArrayList<Demand> temo = new ArrayList<>();
        //temo.add(demands.get(3));

        List<List<int[]>> de = new ArrayList<>();
        for (int a=0; a<66;a++){
            List<int[]> aa = new ArrayList<>();
            int [] temp = new int[3];
            temp[0]=1;
            temp[1]=2;
            temp[2]=3;
            aa.add(temp);
            de.add(aa);
        }




*/
/*

        ArrayList<int[]> d1 = EvolutionaryAlgorithm.allCombinationsForDemand(demands.get(0).getVolume(), demands.get(0).getPaths().size());

        for (int z = 0; z < d1.size(); z++) {
            System.out.println("kombinacja");
            {
                 for(int y=0; y<d1.get(z).length;y++)
                System.out.println(d1.get(z)[y]);
                // }
            }


        }


List<List<int[]>> population = EvolutionaryAlgorithm.getInitialPopulation(1000, 2000, demands);

        System.out.println("size"+ population.size());

        for (int a=0; a<population.size(); a++) {
            System.out.println("new chromosome                 ");
            for (int b = 0; b < population.get(a).size(); b++) {
                System.out.println("demand" + b);
                for (int c = 0; c < population.get(a).get(b).length; c++) {
                    System.out.println(population.get(a).get(b)[c]);


                }


            }


        }}}
              // System.out.println(comb.size()+ "aaaaaaaaaaaaaaaaaaa");


/*
        for(int a=0; a<comb.size();a++){
            System.out.println("solution                                   ");
            for(int b=0; b<comb.get(a).size() ;b++){
                System.out.println("demand");
                System.out.println(comb.get(a).get(b));


            }


        }
        System.out.println(comb.size());

        Lists.cartesianProduct(ImmutableList.of(
                ImmutableList.of(1, 2),
                ImmutableList.of("A", "B", "C")));
        /*

        File file2 = new File("net12_1.txt");
        ArrayList<String> result2 = new ArrayList<String>();
        ArrayList<Link> links2 = new ArrayList<Link>();
        ArrayList<Demand> demands2 = new ArrayList<Demand>();
        result2 = Reader.read(file2);
        links2 = Reader.readLinks(result2);
        demands2 = Reader.readDemands(result2);

        File file3 = new File("net12_2.txt");
        ArrayList<String> result3 = new ArrayList<String>();
        ArrayList<Link> links3 = new ArrayList<Link>();
        ArrayList<Demand> demands3 = new ArrayList<Demand>();
        result3 = Reader.read(file3);
        links3 = Reader.readLinks(result3);
        demands3 = Reader.readDemands(result3);
        int a = 0;




*/
         /*
        ArrayList<Solution> initialPopulation= new ArrayList<>();
        initialPopulation=EvolutionaryAlgorithm.getInitialPopulation(10,200, demands);

        System.out.println("okkkkkkkkkkkkk"+initialPopulation.size());
*/
        /*

        for( int c=0; c<initialPopulation.size();c++){

            System.out.println("nowe rozwiązanie");
            for (int b=0; b<initialPopulation.get(c).getChromosome().size();b++){

                System.out.println("nowy gen");
                for (int d=0;d< initialPopulation.get(c).getChromosome().get(b).length;d++){

                    System.out.println(initialPopulation.get(c).getChromosome().get(b)[d]);

                }



            }
        } for (int b=0; b<links.size();b++){
            System.out.println(links.get(b).getStartNode()+" start");
            System.out.println(links.get(b).getEndNode()+" end");
            System.out.println(links.get(b).getNumberOfModules()+" number of mofules");
            System.out.println(links.get(b).getModuleCost()+" module cost");
            System.out.println(links.get(b).getLinkModule()+" link module");




        }





        //Solution evolution= new Solution();
        Solution brute= new Solution();
        ArrayList<Solution> bestFromGenerations= new ArrayList<>();
        brute=BruteForce.DAP(demands,links);

        EvolutionaryAlgorithm ev = new EvolutionaryAlgorithm(200, bestFromGenerations);//(50, 0.4,0.6,400000000,400, 200,100,1000, 0.6, links,demands, bestFromGenerations);

        Solution evolution =ev.DDAP(50, 30000, demands, links, 0.6,0.4, 0.6 ,70, 40, 30, 9000, bestFromGenerations);
        System.out.println(bestFromGenerations.size()+ "liczba generacji ");
        System.out.println(evolution.getVolumeAllocatedOnLinks().size()+"iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        for (int t=0; t<brute.getVolumeAllocatedOnLinks().size();t++){
            System.out.println(brute.getVolumeAllocatedOnLinks().get(t));

        }
        SolutionWriter sol = new SolutionWriter();
         for ( Solution s:bestFromGenerations) {

             System.out.println("aaaaaaaaaaaaaaaa");
             sol.writeSolutionToFile(("_solution_evo_dap"), s, demands, links);
         }

        System.out.println(evolution.getCost());
        /*
      brute=BruteForce.DDAP(demands,links);

        for (int t=0; t<brute.getVolumeAllocatedOnLinks().size();t++){
            System.out.println(brute.getVolumeAllocatedOnLinks().get(t));

        }
*/








