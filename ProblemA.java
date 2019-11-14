package assg2p1;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import assg1p1.Station;

public class ProblemA {

	public ProblemA(String infile) {
		try {
			processInput(infile);

		} catch (IOException e) {
			System.out.println("Exception encountered: " + e);
		}
	}

	/**
	 * A helper method to process the input file.
	 * 
	 * @param infile the file containing the input
	 * @throws IOException
	 */

	// lidcombe = {Strathfield}
	// Strathfield = {Redfern, Hornsby, Sydenham}

	TreeMap<String, Station> stationList = new TreeMap<>();
	
	
	
	public void processInput(String infile) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(infile));
		
			
		
		String CsvSplitBy = " ";
		
		
		Station startingStations;
		Station destinationStations;
        while ((infile = in.readLine()) != null) {
			
			String stationInfo [] = infile.split(CsvSplitBy);

			if(stationList.containsKey(stationInfo[0])){
				startingStations = stationList.get(stationInfo[0]);
			} else {
				startingStations = new Station(stationInfo[0], 0.0, 0.0);	
			}

			if(stationList.containsKey(stationInfo[1])){
				destinationStations = stationList.get(stationInfo[1]);
			} else {
				destinationStations = new Station(stationInfo[1], 0.0, 0.0);
			}

			startingStations.addNeighbour(destinationStations, 0);
			stationList.put(startingStations.getName(), startingStations);
			stationList.put(destinationStations.getName(), destinationStations);
			

			
		}
		
        in.close();
	}

	/**
	 * Returns the number of routes between two stations for all pairs of stations.
	 * This implementation is using the Floyd Warshall all-paths algorithm.
	 */

	
	



	public HashMap<String, HashMap<String, Integer>> findNumberOfRoutes() {
		HashMap<String, HashMap<String, Integer>> allPaths = new HashMap<>();
		for(String stationNames: stationList.keySet()){
			HashMap<String, Integer> neighbour = new HashMap<>();
			for(String values: stationList.keySet()){
				if(stationList.get(stationNames).getAdjacentStations().containsKey(stationList.get(values))){
					neighbour.put(values,1);
				} else {
					neighbour.put(values, 0);
				}
				allPaths.put(stationNames, neighbour);
			}
		}


		for(String intermediateStations: stationList.keySet()){
            		for(String startingStation: stationList.keySet()){
                		for(String endingStation: stationList.keySet()){
					allPaths.get(startingStation).put(endingStation, allPaths.get(startingStation).get(endingStation)
					 + allPaths.get(startingStation).get(intermediateStations) 
					 * allPaths.get(intermediateStations).get(endingStation));
				}	
			}
		}

		ArrayList<String> adj = new ArrayList<>();
		for(String weightCheck: allPaths.keySet()){
			if(allPaths.get(weightCheck).get(weightCheck) > 1){
				adj.add(weightCheck);
				System.out.println(adj);

			}
		}
		
		for(String intermediateStations: adj){
            for(String startingStation: stationList.keySet()){
                for(String endingStation: stationList.keySet()){
					if(allPaths.get(startingStation).get(intermediateStations) != 0){
						if(allPaths.get(intermediateStations).get(endingStation) != 0){
							allPaths.get(startingStation).put(endingStation, -1);
						}
					}
				}	
			}
		}




		
		//System.out.println(allPaths);
		return allPaths;
	}
}
		 /* INSERT YOUR CODE HERE
		 */
	
		
	
	

