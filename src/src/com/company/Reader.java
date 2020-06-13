package src.com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {



    public static ArrayList<String> read(File file) {


        ArrayList<String> result = new ArrayList<>();



        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while (br.ready()) {
                result.add(br.readLine());
            }

             } catch (IOException e) {

            e.printStackTrace();

        }
        return result;
    }

    public static ArrayList<Link> readLinks (ArrayList<String> result){
        ArrayList<Link> links = new ArrayList<>();
        int i = 0;
        for ( i = 1; i < result.size(); i++) {

            if (result.get(i).equals("-1")) {

                break;
            }

            Link link = new Link();

            String[] temp = result.get(i).split(" ");

            link.setStartNode(Integer.parseInt(temp[0]));
            link.setEndNode(Integer.parseInt(temp[1]));
            link.setNumberOfModules(Integer.parseInt(temp[2]));
            link.setModuleCost(Integer.parseInt(temp[3]));
            link.setLinkModule(Integer.parseInt(temp[4]));

            links.add(link);

        }
        i = i + 4;

        int y = Integer.parseInt(result.get(i - 2));

        return links;
    }

    public static ArrayList<Demand> readDemands( ArrayList<String> result ) {
        ArrayList<Demand> demands = new ArrayList<>();
        for(int w=0;w<result.size();w++){
            if(result.get(w).equals("-1")){
                int i=w+2;

                int y = Integer.parseInt(result.get(i));
                i=i+2;
                for (int a = 0; a < y; a++) {

                    Demand demand = new Demand();
                    String[] temp = result.get(i).split(" ");
                    demand.setDemandStartNode(Integer.parseInt(temp[0]));
                    demand.setDemandEndNode(Integer.parseInt(temp[1]));
                    demand.setVolume(Integer.parseInt(temp[2]));
                    demand.setID(a);
                    int numberOfLinks=0;


                    i++;
                    int z = Integer.parseInt(result.get(i));
                     ArrayList<Path> paths = new ArrayList<Path>();
                    for (int b = 0; b < z; b++) {

                        i++;
                        // System.out.println("path" + result.get(i));
                        temp = result.get(i).split(" ");
                        Path path = new Path();
                        path.setID(b);
                        // path.setLinks(new int[temp.length-1]);
                        int[] l = new int[temp.length - 1];
                        for (int c = 0; c < temp.length - 1; c++) {
                            l[c] = Integer.parseInt(temp[c + 1]);
                            if (numberOfLinks<l[c])
                                numberOfLinks= l[c];

                        }
                        path.setLinks(l);
                        paths.add(path);
                        demand.setPaths(paths);
                        demand.setNumberOfLinks(numberOfLinks);



                    }
                    i = i + 2;
                    demand.setPaths(paths);
                    demands.add(demand);
                }}
        }
        return demands;
    }
}
