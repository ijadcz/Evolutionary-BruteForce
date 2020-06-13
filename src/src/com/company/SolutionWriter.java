package src.com.company;

import java.io.*;
import java.util.ArrayList;

public class SolutionWriter {

    public static void solutionWriter( Solution solution){

        System.out.println("Solution");

        System.out.println("Cost Function"+ solution.getCost());

        System.out.println("Volume allocated on links");
        for( int a=0; a< solution.getVolumeAllocatedOnLinks().size();a++ ){
            System.out.print(solution.getVolumeAllocatedOnLinks().get(a)+" ");

        }
        System.out.println("");

        for(int b=0; b<solution.getChromosome().size();b++){
            System.out.println("gene "+(b+1));
            for (int c=0; c< solution.getChromosome().get(b).length;c++){
                System.out.println(solution.getChromosome().get(b)[c]);

            }
        }







    }


/*

    public static void writeSolutionToFile(String fileName,Solution solution, ArrayList<Demand> demands, ArrayList<Link> links){

        String text = "";
        if (solution == null) {
            text = "No solution";
        } else {
            text = printSolution(solution);
        }
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".txt", true)))) {
            writer.println(text);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static  String printSolution(Solution solution) {

        String text=" ";

        for(int a=0; a<solution.getChromosome().size();a++){

            text+= "\n"+a+"demand gen ";
            for( int b=0; b< solution.getChromosome().get(a).length; b++){
                text= text+solution.getChromosome().get(a)[b];


            }


        }

        text+="\n"+"volume on links :";
        for(int c =0; c< solution.getVolumeAllocatedOnLinks().size();c++) {
            text += solution.getVolumeAllocatedOnLinks().get(c)+" ";
        }
        text+="\n function cost"+solution.getCost();











        return text;
    }


   */
}
