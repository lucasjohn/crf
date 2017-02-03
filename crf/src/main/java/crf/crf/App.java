package crf.crf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import crf.crf.helper.CityHelper;
import crf.crf.helper.Commons;
import crf.crf.pojo.City;

/**
 * @author Lucas Albuquerque
 *
 */
public class App 
{
	private static String filePath;
	static List<City> cities = new ArrayList<City>();
	
    public static void main( String[] args ) throws IOException
    {
    	if (args.length != 1){
    		System.err.println("Must provide the source path in ONE string without white spaces");
    		System.exit(0);
    	}
    	filePath = args[0];
    	run();
    }
    
    private static void run() throws IOException {
    	BufferedReader commandBufferedReader = new BufferedReader(new InputStreamReader(System.in));

    	// Validate file
    	File file = new File(filePath);
    	FileReader fileReader = null;
		BufferedReader fileBufferedReader = null;
    	try {
    		fileReader = new FileReader(file);
    		fileBufferedReader = new BufferedReader(fileReader);
			String header = fileBufferedReader.readLine();
			String headerFields[] = header.split(",");
			
			if (10 != headerFields.length){
	    		System.err.println("Malformed file - Columns length do not match");
	    		System.exit(0);
			}
			
			city:
			for (Field field : City.class.getDeclaredFields()){
				for (String hField : headerFields){
					if (field.getName().toLowerCase().contentEquals(hField.toLowerCase())){
						continue city;
					}
				}
	    		System.err.println("Malformed file - Columns names do not match");
				System.exit(0);
			}
			
			cities.clear();
			while (fileBufferedReader.read()!=-1) {
				City city = null;
				String fields[] = fileBufferedReader.readLine().split(",");
				
				try {
					city = CityHelper.parseCity(fields);
				} catch (NumberFormatException e) {
					System.err.println("Something went wrong creating the object from file. (Number format)");
				} catch (NullPointerException e) {
					System.err.println("Something went wrong creating the object from file. (Null Pointer)");
				}
				cities.add(city);
				
			}
			
    	} catch (FileNotFoundException e) {
    		System.err.println("Cold not find file");
    		System.exit(0);
		} catch (IOException e) {
    		System.err.println("Could not read file");
    		System.exit(0);
		}finally{
			fileBufferedReader.close();
			fileReader.close();
		}
    	
    	String args;
    	do{
    			System.out.print(">");
    			args = commandBufferedReader.readLine();
    			
    			if(!args.trim().contentEquals("")){
    				if (args.contentEquals("quit")){
    					commandBufferedReader.close();
    					System.exit(0);
    				}
    				if (args.contentEquals("-h")){
    					Commons.printHelp();
    					continue;
    				}
    				String commandArgs[] = args.split(" ");
    				
    				//Clear for double spaces
    				for (int i = 0; i < commandArgs.length; i++) {
    					commandArgs[i] = commandArgs[i].trim();
    				}
	    				
    				switch (commandArgs.length) {
    				case 2:

    					if (commandArgs[0].contentEquals("count")&&commandArgs[1].contentEquals("*")){
    						System.out.println(cities.size());
    					}else{
	    					System.err.println("Wrong command");
	    					System.out.println();
	    					Commons.printHelp();
    					}
    					break;

    				case 3:

    					if (commandArgs[0].contentEquals("count")){
    						if (commandArgs[1].contentEquals("distinct")){

    							Set<String> cl = new HashSet<String>();
    							boolean validField = false;
    							for (Field field : City.class.getDeclaredFields()){
									if (field.getName().toLowerCase().contentEquals(commandArgs[2].toLowerCase())){
										validField = true;
										break;
									}
    							}
    							
    							if (validField){
	    							
	    							for (City city : cities) {
										cl.add(city.getValue(commandArgs[2].toLowerCase()));
									}
	    							System.out.println(cl.size());
    							}else{
        	    					System.err.println("Field property not valid - Check the command");
    							}
    							
    						}else{
    	    					System.err.println("Wrong command");
    	    					System.out.println();
    	    					Commons.printHelp();
    						}
    						
    					}else if (commandArgs[0].contentEquals("filter")){
    						
    						List<City> filteredCities = new ArrayList<City>();
							boolean validField = false;
							for (Field field : City.class.getDeclaredFields()){
								if (field.getName().toLowerCase().contentEquals(commandArgs[1].toLowerCase())){
									validField = true;
									break;
								}
							}
							
							if (validField){
    							
    							for (City city : cities) {
									if(city.getValue(commandArgs[1].toLowerCase()) != null && city.getValue(commandArgs[1].toLowerCase()).toLowerCase().contains(commandArgs[2].toLowerCase())){
										filteredCities.add(city);
									}
								}
    							System.out.println("Number of registers found: " + filteredCities.size());
    							System.out.println("-------------------------------------------------------------");
    							StringBuilder header = new StringBuilder();
    							for (Field field : City.class.getDeclaredFields()){
									header.append(",").append(field.getName());
    							}
    							System.out.println(header.substring(1));
    							for (City city : filteredCities) {
									System.out.println(city);
								}
    							System.out.println("-------------------------------------------------------------");
    							System.out.println("Number of registers found: " + filteredCities.size());
							}else{
    	    					System.err.println("Field property not valid - Check the command");
							}
							
    					}else{
	    					System.err.println("Wrong command");
	    					System.out.println();
	    					Commons.printHelp();
    					}
    					break;

    				default:
    					System.err.print("Incorrect number of arguments");
    					System.out.println();
    					Commons.printHelp();
    					break;
    				}
    			}
    			
    			
    	}while (true);
    	
	}
}
