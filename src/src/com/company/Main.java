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
        int numberOfGenerations = 0;

        int numberOfGenerationsWithoutImprovement = 0;
        int numberOfMutations = 0;
        long time = 0;
        int next = 0;


        System.out.println("Size of initial population");
        populationSize = scanner.nextInt();
        System.out.println("Probability of mutation (0,1)");
        pMutation = scanner.nextFloat();
        System.out.println("Probability of crossover (0,1)");
        pCrossover = scanner.nextDouble();
        System.out.println("seed");
        seed = scanner.nextLong();


        System.out.println("Choice of stop criterion");
        System.out.println("1: Time");
        System.out.println("2: Number of generations ");
        System.out.println("3: Number of mutations ");
        System.out.println("4: Number of generations without improvement");


        int choice = scanner.nextInt();

        switch (choice) {
            case 1:

                System.out.println("Type max Time");
                time = scanner.nextLong();
                numberOfGenerations = Integer.MAX_VALUE;
                numberOfMutations = Integer.MAX_VALUE;
                numberOfGenerationsWithoutImprovement = Integer.MAX_VALUE;
                break;
            case 2:

                System.out.println("Type max number of generations");
                numberOfGenerations = scanner.nextInt();
                time = Long.MAX_VALUE;
                numberOfMutations = Integer.MAX_VALUE;
                numberOfGenerationsWithoutImprovement = Integer.MAX_VALUE;
                break;
            case 3:

                System.out.println("Type max number of mutations");
                numberOfMutations = scanner.nextInt();
                time = Long.MAX_VALUE;
                numberOfGenerations = Integer.MAX_VALUE;
                numberOfGenerationsWithoutImprovement = Integer.MAX_VALUE;
                break;
            case 4:

                System.out.println("Type max number of generations without improvement");
                numberOfGenerationsWithoutImprovement = scanner.nextInt();
                time = Long.MAX_VALUE;
                numberOfMutations = Integer.MAX_VALUE;
                numberOfGenerations = Integer.MAX_VALUE;
                break;

        }


        ArrayList<Solution> bruteALLDAP = BruteForce.allPossibleSolutions(demands, links);
        ArrayList<Solution> bruteALLDDAP = BruteForce.allPossibleSolutions(demands, links);
        Solution brutedap = BruteForce.DAP(demands, links, bruteALLDAP);
        ArrayList<Solution> bestDAP1 = new ArrayList<>();
        ArrayList<Solution> bestDDAP1 = new ArrayList<>();
        ArrayList<Solution> bestDAP2 = new ArrayList<>();
        ArrayList<Solution> bestDDAP2 = new ArrayList<>();
        ArrayList<Solution> bestDAP3 = new ArrayList<>();
        ArrayList<Solution> bestDDAP3 = new ArrayList<>();

        System.out.println("Solution Brute Force DAP");
        SolutionWriter.solutionWriter(brutedap);
        System.out.println("Type 1 to get next solution");
        next = scanner.nextInt();

        Solution bruteDDAP = BruteForce.DDAP(demands, links, bruteALLDDAP);
        System.out.println("Solution Brute Force DDAP");
        SolutionWriter.solutionWriter(bruteDDAP);

        System.out.println("Type 1 to get next solution");
        next = scanner.nextInt();

        long start = System.nanoTime();
        int n = 0;
        EvolutionaryAlgorithm evDAP1 = new EvolutionaryAlgorithm(0, bestDAP1, n);
        Solution DAP1 = evDAP1.DAP(populationSize, seed, demands, links, 0.5, pCrossover, pMutation, time, numberOfGenerations, numberOfGenerationsWithoutImprovement, numberOfMutations, bestDAP1);
        long end = System.nanoTime();

        System.out.println("Solution Evolutionary DAP dla pliku net4.txt");
        System.out.println("number of generations " + evDAP1.getNumberOfGenerations());
        System.out.println("Time ms " + (end - start) / 1000000);
        SolutionWriter.solutionWriter(DAP1);
        System.out.println("Type 1 to get next solution");
        next = scanner.nextInt();
        n = 0;
        start = System.nanoTime();

        EvolutionaryAlgorithm evDDAP1 = new EvolutionaryAlgorithm(0, bestDDAP1, n);
        Solution DDAP1 = evDDAP1.DDAP(populationSize, seed, demands, links, 0.5, pCrossover, pMutation, time, numberOfGenerations, numberOfGenerationsWithoutImprovement, numberOfMutations, bestDDAP1);
        end = System.nanoTime();
        System.out.println("Solution Evolutionary DDAP dla pliku net4.txt");
        System.out.println("number of generations " + evDDAP1.getNumberOfGenerations());
        System.out.println("Time ms " + (end - start) / 1000000);
        SolutionWriter.solutionWriter(DDAP1);
        System.out.println("Type 1 to get next solution");
        next = scanner.nextInt();
        n = 0;
        start = System.nanoTime();
        EvolutionaryAlgorithm evDAP2 = new EvolutionaryAlgorithm(0, bestDAP2, n);
        Solution DAP2 = evDAP2.DAP(populationSize, seed, demands2, links2, 0.5, pCrossover, pMutation, time, numberOfGenerations, numberOfGenerationsWithoutImprovement, numberOfMutations, bestDAP2);
        end = System.nanoTime();
        System.out.println("Solution Evolutionary DAP dla pliku net12_1.txt");
        System.out.println("number of generations " + evDAP2.getNumberOfGenerations());
        System.out.println("Time ms " + (end - start) / 1000000);
        SolutionWriter.solutionWriter(DAP2);
        System.out.println("Type 1 to get next solution");
        next = scanner.nextInt();
        n = 0;
        start = System.nanoTime();
        EvolutionaryAlgorithm evDDAP2 = new EvolutionaryAlgorithm(0, bestDDAP2, n);
        Solution DDAP2 = evDDAP2.DDAP(populationSize, seed, demands2, links2, 0.5, pCrossover, pMutation, time, numberOfGenerations, numberOfGenerationsWithoutImprovement, numberOfMutations, bestDDAP2);
        end = System.nanoTime();
        System.out.println("Solution Evolutionary DDAP dla pliku net12_1.txt");
        System.out.println("number of generations " + evDDAP2.getNumberOfGenerations());
        System.out.println("Time ms " + (end - start) / 1000000);
        SolutionWriter.solutionWriter(DDAP2);
        System.out.println("Type 1 to get next solution");
        next = scanner.nextInt();
        n = 0;
        start = System.nanoTime();
        EvolutionaryAlgorithm evDAP3 = new EvolutionaryAlgorithm(0, bestDAP3, n);
        Solution DAP3 = evDAP3.DAP(populationSize, seed, demands3, links3, 0.5, pCrossover, pMutation, time, numberOfGenerations, numberOfGenerationsWithoutImprovement, numberOfMutations, bestDAP3);
        end = System.nanoTime();
        System.out.println("Solution Evolutrionary DAP dla pliku net12_2.txt");
        System.out.println("number of generations " + evDAP3.getNumberOfGenerations());
        System.out.println("Time ms " + (end - start) / 1000000);
        SolutionWriter.solutionWriter(DAP3);
        System.out.println("Type 1 to get next solution");
        next = scanner.nextInt();
        n = 0;
        start = System.nanoTime();
        EvolutionaryAlgorithm evDDAP3 = new EvolutionaryAlgorithm(0, bestDDAP3, n);
        Solution DDAP3 = evDDAP3.DDAP(populationSize, seed, demands3, links3, 0.5, pCrossover, pMutation, time, numberOfGenerations, numberOfGenerationsWithoutImprovement, numberOfMutations, bestDDAP3);
        end = System.nanoTime();
        System.out.println("Solution Evolutionary DDAP dla pliku net12_2.txt");
        System.out.println("number of generations " + evDDAP3.getNumberOfGenerations());
        System.out.println("Time ms " + (end - start) / 1000000);
        SolutionWriter.solutionWriter(DDAP3);
        System.out.println("Type 1 to continue");
        next = scanner.nextInt();

        while (choice != 0) {

            System.out.println("Show best solutions from every generation for");
            System.out.println("1: Evolutionary DAP dla net4.txt");
            System.out.println("2: Evolutionary DDAP dla net4.txt");
            System.out.println("3: Evolutionary DAP dla net12_1.txt");
            System.out.println("4: Evolutionary DDAP dla net12_1.txt");
            System.out.println("5: Evolutionary DAP dla net12_2.txt");
            System.out.println("6: Evolutionary DDAP dla net12_2.txt");
            System.out.println("Show all possible solutions for");
            System.out.println("7: Brute Force DAP");
            System.out.println("8: Brute Force DDAP");
            System.out.println("0: EXIT");

            choice = scanner.nextInt();


            switch (choice) {

                case 1:
                    for (Solution s : evDAP1.getBestSolutionsFromEveryGeneration()) {
                        SolutionWriter.solutionWriter(s);
                    }
                default:

                case 2:

                    for (Solution s : evDDAP1.getBestSolutionsFromEveryGeneration()) {
                        SolutionWriter.solutionWriter(s);
                    }
                    break;

                case 3:

                    for (Solution s : evDAP2.getBestSolutionsFromEveryGeneration()) {
                        SolutionWriter.solutionWriter(s);
                    }
                    break;

                case 4:

                    for (Solution s : evDDAP2.getBestSolutionsFromEveryGeneration()) {
                        SolutionWriter.solutionWriter(s);
                    }
                    break;

                case 5:

                    for (Solution s : evDAP3.getBestSolutionsFromEveryGeneration()) {
                        SolutionWriter.solutionWriter(s);
                    }
                    break;

                case 6:

                    for (Solution s : evDDAP3.getBestSolutionsFromEveryGeneration()) {
                        SolutionWriter.solutionWriter(s);
                    }
                    break;
                case 7:

                    for (Solution s : bruteALLDAP) {
                        SolutionWriter.solutionWriter(s);
                    }
                    break;

                case 8:
                    for (Solution s : bruteALLDDAP) {
                        SolutionWriter.solutionWriter(s);
                    }
                    break;

                case 0:
                    break;


            }
        }


    }}