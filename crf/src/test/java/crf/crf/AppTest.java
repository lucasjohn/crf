package crf.crf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import crf.crf.helper.CityHelper;
import crf.crf.helper.Commons;
import crf.crf.pojo.City;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Test 1
     */
    public void testFileReadable()
    {
    	File file = new File("files/cidades.csv");
    	try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			assertTrue(br.read() != -1);
			br.close();
			fr.close();
    	} catch (FileNotFoundException e) {
    		fail("Cold not find file");
		} catch (IOException e) {
			fail("Could not read file");
		}
    }

    /**
     * Test 2
     */
    public void testValidFormat()
    {
    	File file = new File("files/cidades.csv");
    	try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String header = br.readLine();
			br.close();
			fr.close();
			String headerFields[] = header.split(",");
			city:
			for (Field field : City.class.getDeclaredFields()){
				for (String hField : headerFields){
					if (field.getName().toLowerCase().contentEquals(hField.toLowerCase())){
						continue city;
					}
				}
				fail("Malformed file - Columns do not match");
				break city;
			}
    	} catch (FileNotFoundException e) {
    		fail("Cold not find file");
		} catch (IOException e) {
			fail("Could not read file");
		}
    }

    /**
     * Test 3
     */
    public void testCount()
    {
    	File file = new File("files/cidades.csv");
    	try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			br.readLine();

			int counter = 0;
			List<City> cities = new ArrayList<City>();
			while (br.read()!=-1) {
				counter++;
				City city = null;
				String fields[] = br.readLine().split(",");
				
				assertEquals(10, fields.length);
				
				try {
					city = CityHelper.parseCity(fields);
				} catch (NumberFormatException e) {
		    		fail("Something went wrong creating the object");
				} catch (NullPointerException e) {
		    		fail("Something went wrong creating the object");
				}
				cities.add(city);
				
			}
			assertEquals(5565, counter);
			assertEquals(5565, cities.size());
			
			br.close();
			fr.close();
    	} catch (FileNotFoundException e) {
    		fail("Cold not find file");
		} catch (IOException e) {
			fail("Could not read file");
		}
    }

    /**
     * Test 3
     */
    public void testValidateCommand()
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.print(">");
    	//read input
		String args = "count distinct test";//br.readLine();
		
		assertTrue("Empty command",!args.trim().contentEquals(""));
		
		String commandArgs[] = args.split(" ");
		for (int i = 0; i < commandArgs.length; i++) {
			commandArgs[i] = commandArgs[i].trim();
		}
		assertTrue("Incorrect number of arguments",commandArgs.length > 1 && commandArgs.length <= 3);
		
		switch (commandArgs.length) {
		case 2:

			assertTrue("Wrong command",commandArgs[0].contentEquals("count")&&commandArgs[1].contentEquals("*"));
			break;

		case 3:

			if (commandArgs[0].contentEquals("count")){
				if (commandArgs[1].contentEquals("distinct")){
					//Execute count
				}else{
					Commons.printHelp();
					fail("Wrong command");
				}
				
			}else if (commandArgs[0].contentEquals("filter")){
				//Execute filter
			}else{
				Commons.printHelp();
				fail("Wrong command");
			}
			break;

		default:
			fail("Incorrect number of arguments");
			break;
		}
    }
}
