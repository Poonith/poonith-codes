package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class JDBCConnection {

   
    // Name of database file (contained in database folder)
    //public static final String DATABASE = "jdbc:sqlite:database/ctg.db";
    public static final String DATABASE = "jdbc:sqlite:database/climate.db";

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
        public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }
       

    
   //public static final String DATABASE = "jdbc:sqlite:database/ctg.db";
    // public static final String DATABASE = "jdbc:sqlite:database/climate.db";
    /**
     * Get all of the LGAs in the database.
     * @return
     *    Returns an ArrayList of LGA objects
     */

    public ArrayList<LGA> getLGAs2016() {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM LGA WHERE year='2016'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int code     = results.getInt("code");
                String name  = results.getString("name");

                // Create a LGA Object
                LGA lga = new LGA(code, name, 2016);

                // Add the lga object to the array
                lgas.add(lga);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }
    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
  
    private static final int Year = 0;
    private static final String Population = null;
    private static final double AvgTemp = 0;


        //public static final String DATABASE = "jdbc:sqlite:database/climate.db";
    
        /**
         * This creates a JDBC Object so we can keep talking to the database
         */
        
    
         public ArrayList<yearrange> getYearRangeArray() {
            // Create the ArrayList of LGA objects to return
            ArrayList<yearrange> yearRangeData = new ArrayList<yearrange>();
        
            // Setup the variable for the JDBC connection
            Connection connection = null;
        
            try {
                // Connect to JDBC data base
                connection = DriverManager.getConnection(JDBCConnection.DATABASE);
        
                // Prepare a new SQL Query & Set a timeout
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
        
                // The Query to get the minimum and maximum years
                String yearRangeQuery = "SELECT MIN(YEAR) , MAX(YEAR)  FROM COUNTRYTEMPOBSERVATION";
        
                // Get Result
                ResultSet results = statement.executeQuery(yearRangeQuery);
        
                // Get the first and last year
                while(results.next()) {
                int firstYear = results.getInt(1);
                int lastYear = results.getInt(2);
        
                // Queries to get the population and temperature for first and last years
                String firstYearDataQuery = "SELECT POPULATION, AVGTEMP FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + firstYear;
                String lastYearDataQuery = "SELECT POPULATION, AVGTEMP FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + lastYear;

        
                // Execute queries and fetch data for first and last year
                ResultSet firstYearDataResults = statement.executeQuery(firstYearDataQuery);
                firstYearDataResults.next(); 
                int firstYearPopulation = firstYearDataResults.getInt("POPULATION");
                double firstYearTemperature = firstYearDataResults.getDouble("AVGTEMP");
        
                ResultSet lastYearDataResults = statement.executeQuery(lastYearDataQuery);
                int lastYearPopulation = lastYearDataResults.getInt("POPULATION");
                double lastYearTemperature = lastYearDataResults.getDouble("AVGTEMP");
        
                // Add the data to the array
                yearRangeData.add(new yearrange(firstYear, String.valueOf(firstYearPopulation), firstYearTemperature));
                yearRangeData.add(new yearrange(lastYear, String.valueOf(lastYearPopulation), lastYearTemperature));
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        return yearRangeData;
     
    }
    int k;
    // TODO: Add your required methods here
    public ArrayList<Student> getStudentInfo() {
        ArrayList<Student> students = new ArrayList<Student>();
        Connection connection = null;
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM STUDENTINFO";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String ID     = results.getString("StudentID");
                String firstname  = results.getString("StudentFirstName");
                String lastname = results.getString("StudentLastName");

                // Create a LGA Object
                Student student = new Student(ID, firstname, lastname);

                // Add the lga object to the array
                students.add(student);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return students;
    }


//PERSONAS///
    public ArrayList<Persona> getPersonaInfo() {
        ArrayList<Persona> personas = new ArrayList<Persona>();
        Connection connection = null;
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM PERSONAS";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int ID     = results.getInt("PersonaID");
                String Name  = results.getString("Name");
                String Description = results.getString("Description");
                String Needs = results.getString("Needs");
                String Skills = results.getString("Skills");
                String PainPoints = results.getString("PainPoints");
                String ImgURL = results.getString("ImgURL");

                // Create a LGA Object
                Persona persona = new Persona(ID, Name, Description, Needs, Skills, PainPoints, ImgURL);

                // Add the lga object to the array
                personas.add(persona);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return personas;
    }


    public ArrayList<String> getCountry() {
        ArrayList<String> Countries = new ArrayList<String>();
        Connection connection = null;
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "Select COUNTRYNAME FROM COUNTRY";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String Country    = results.getString("COUNTRYNAME");

                // Add the lga object to the array
                Countries.add(Country);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return Countries;
    }

    public ArrayList<String> getYear() {
        ArrayList<String> Years = new ArrayList<String>();
        Connection connection = null;
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "Select DISTINCT YEAR FROM STATE";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String Year   = results.getString("YEAR");

                // Add the lga object to the array
                Years.add(Year);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return Years;
    }




    public ArrayList<String> getCountryname() {
        ArrayList<String> Countries = new ArrayList<String>();
        Connection connection = null;
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DISTINCT COUNTRY FROM COUNTRYTEMPOBSERVATION";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String Country    = results.getString("COUNTRY");

                // Add the lga object to the array
                Countries.add(Country);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return Countries;
    }

    public ArrayList<YearCount> getYearCount() {
        ArrayList<YearCount> yearCountData = new ArrayList<YearCount>();
    
        Connection connection = null;
    
        try {
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
    
            String yearCountQuery = "SELECT COUNT(DISTINCT Year) FROM COUNTRYTEMPOBSERVATION ";
            ResultSet results = statement.executeQuery(yearCountQuery);
    
           while (results.next()) {
                int count = results.getInt(1);
                YearCount yearCount = new YearCount(count);
                yearCountData.add(yearCount);

            }
    
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    
        return yearCountData;
    }
















    

    


    public ArrayList<String> getRegion() {
        ArrayList<String> Regions = new ArrayList<String>();
        Connection connection = null;
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "Select Distinct Region FROM REGION ";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String Region   = results.getString("Region");

                // Add the lga object to the array
                Regions.add(Region);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return Regions;
    }
   
    


    public ArrayList<TempPopSortData> getSortTempPopData(String sortCriterion, String sortOrder,String Countryname,String Year1,String Year2) {
        ArrayList<TempPopSortData> tempPopsortdata= new ArrayList<TempPopSortData>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String Query = "SELECT DISTINCT POPULATION, AVGTEMP, YEAR FROM COUNTRYTEMPOBSERVATION WHERE COUNTRY = '" + Countryname + "' AND YEAR BETWEEN " + Year1 + " AND " + Year2 + " ORDER BY " + sortCriterion + " " + sortOrder+ " ,YEAR ASC";

            ResultSet results = statement.executeQuery(Query);

           while (results.next()) {
                int Population = results.getInt("POPULATION");
                double AvgTemp = results.getDouble("AVGTEMP");
                int Year = results.getInt("YEAR");
                TempPopSortData temppopsortdata = new TempPopSortData(Population,AvgTemp,Year);
                tempPopsortdata.add(temppopsortdata);
            }

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return tempPopsortdata;
    }










        // public ArrayList<TempPopulation> getTempPopulation(String CountryName) {
        //     ArrayList<TempPopulation> tempPopulation= new ArrayList<>();

        //     Connection connection = null;

        //     try {
        //         connection = DriverManager.getConnection(JDBCConnection.DATABASE);
        //         Statement statement = connection.createStatement();
        //         statement.setQueryTimeout(30);

        //         String query = "SELECT * FROM Population WHERE CountryName = '" + CountryName + "'";


        //         ResultSet results = statement.executeQuery(query);

        //         while (results.next()) {
        //             for (int year = 1960; year <= 2013; year++) {
        //                 int population = results.getInt(String.valueOf(year));
        //                 TempPopulation tempPop = new TempPopulation(population);
        //                 tempPopulation.add(tempPop);
        //             }
        //         }

        //         statement.close();
        //     } catch (SQLException e) {
        //         System.err.println(e.getMessage());
        //     } finally {
        //         try {
        //             if (connection != null) {
        //                 connection.close();
        //             }
        //         } catch (SQLException e) {
        //             System.err.println(e.getMessage());
        //         }
        //     }

        //     return tempPopulation;
        // }



        public ArrayList<STempChange> getTempChange(String region, String SYear, String EYear, String country, String method) {
            ArrayList<STempChange> stempdata = new ArrayList<STempChange>();
        
            Connection connection = null;
        
            try {
                connection = DriverManager.getConnection(JDBCConnection.DATABASE);
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
        
                //String sDataQuery = "Select * FROM " + region + " WHERE (Year =" + Year + ") AND (Country LIKE '" + country + "') ";
                //String sDataQuery = "Select * FROM STATE WHERE (Year = 1890) AND (COUNTRY LIKE 'Australia')";
    
                String sDataQuery = "SELECT A." + region + ", A.SYEAR, A.SMAX, A.SMIN, A.SAVG, B.EYEAR, B.EMAX, B.EMIN, B.EAVG, (((B.E" + method + " - A.S" + method + ") / A.S" + method + ")* 100) AS PROPCHANGE FROM (SELECT DISTINCT " + region + ", YEAR AS SYEAR, MAXTEMP AS SMAX, MINTEMP AS SMIN, AVGTEMP AS SAVG FROM " + region + " WHERE YEAR = " + SYear +  " AND (COUNTRY LIKE '" + country + "')) AS A CROSS JOIN (SELECT DISTINCT " + region + ", YEAR AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM " + region + " WHERE YEAR = " + EYear + ") AS B WHERE A." + region + " = B." + region +  " ORDER BY abs(PROPCHANGE) ASC";
                    
                ResultSet results = statement.executeQuery(sDataQuery);
        
               while (results.next()) {
                    double sAvg = results.getDouble("SAVG");
                    double sMin = results.getDouble("SMIN");
                    double sMax = results.getDouble("SMAX");
                    double eAvg = results.getDouble("EAVG");
                    double eMin = results.getDouble("EMIN");
                    double eMax = results.getDouble("EMAX");
                    String rregion = "";
                    if (region.equals("City")) {
                        rregion = results.getString("City");
                    } else {
                         rregion = results.getString("STATE");
                    }
                    STempChange tempChange = new STempChange(rregion, sAvg, sMin, sMax, eAvg, eMin, eMax);
                    stempdata.add(tempChange);
    
                }
        
                statement.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        
            return stempdata;
    
        }   
        
        





        public ArrayList<STask3B> getTask3B(String region, String SYear, String EYear, String country, String method, String numResults, String comparison) {
            ArrayList<STask3B> sData = new ArrayList<STask3B>();
        
            Connection connection = null;
        
            try {
                connection = DriverManager.getConnection(JDBCConnection.DATABASE);
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);
        

               String sDataQuery = "";


            if (method.equals("Absolute")) {
                if (region.equals("Country")) {
                 if (comparison.equals("Temperature")) {
                    //QUERY FOR ABSOLUTE, COUNTRY, TEMPERATURE (NEEDS VARIABLES SYEAR, EYEAR, COUNTRY)
                    //ABSOLUTE IS METHOD, COUNTRY IS REGION, TEMPERATURE IS COMPARISON, NUMRESULTS IS NOT NEEDED

                    sDataQuery = "WITH FirstQuery AS (SELECT A.Country, A.SYEAR, A.SAVG, B.EYEAR, B.EAVG, ABS(B.EAVG - A.SAVG) AS NAVGCHANGE FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND COUNTRY LIKE '" + country + "') AS A CROSS JOIN (SELECT Country, year AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + " AND COUNTRY LIKE '" + country + "') AS B WHERE A.COUNTRY = B.COUNTRY) SELECT C.Country, C.SYEAR, C.SAVG, B.EYEAR, B.EAVG, ABS(B.EAVG - C.SAVG) AS AVGCHANGE, FirstQuery.NAVGCHANGE AS COMPCHANGE, ABS((B.EAVG - C.SAVG) - FirstQuery.NAVGCHANGE) as RANKING FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND SAVG > 0) AS C CROSS JOIN (SELECT DISTINCT Country, year AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + ") AS B INNER JOIN FirstQuery WHERE C.COUNTRY = B.COUNTRY ORDER BY ABS(AVGCHANGE - COMPCHANGE) ASC LIMIT " + numResults + ";";
                    


                            ResultSet results = statement.executeQuery(sDataQuery);
                            while (results.next()) {
                                   double sAvg = results.getDouble("SAVG");
                                   double eAvg = results.getDouble("EAVG");
                                   String rregion = results.getString("COUNTRY");
                                   Double avgChange = results.getDouble("AVGCHANGE");
                                   Double Ranking = results.getDouble("RANKING");


                                   STask3B taskData = new STask3B(rregion, sAvg, eAvg, avgChange, Ranking);
                                   sData.add(taskData);
                   
                               }
                } else if (comparison.equals("Population")){
                    //QUERY FOR ABSOLUTE, COUNTRY, POPULATION
                    sDataQuery = "WITH FirstQuery AS (SELECT A.Country, A.SYEAR, A.SPOP, B.EYEAR, B.EPOP, ABS(B.EPOP - A.SPOP) AS NAVGCHANGE FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, POPULATION AS SPOP, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND COUNTRY LIKE '"+ country + "') AS A CROSS JOIN (SELECT Country, year AS EYEAR, POPULATION AS EPOP FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + " AND COUNTRY LIKE '" + country + "') AS B WHERE A.COUNTRY = B.COUNTRY) SELECT C.Country, C.SYEAR, C.SPOP, B.EYEAR, B.EPOP, ABS(B.EPOP - C.SPOP) AS AVGCHANGE, FirstQuery.NAVGCHANGE AS COMPCHANGE, ABS((B.EPOP - C.SPOP) - FirstQuery.NAVGCHANGE) as RANKING FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, POPULATION AS SPOP, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND SPOP > 0) AS C CROSS JOIN (SELECT DISTINCT Country, year AS EYEAR, POPULATION AS EPOP FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + ") AS B INNER JOIN FirstQuery WHERE C.COUNTRY = B.COUNTRY ORDER BY ABS(AVGCHANGE - COMPCHANGE) ASC LIMIT " + numResults + ";"; 
                    ResultSet results = statement.executeQuery(sDataQuery);
                    while (results.next()) {
                           double sAvg = results.getDouble("SPOP");
                           double eAvg = results.getDouble("EPOP");
                           String rregion = results.getString("COUNTRY");
                           Double avgChange = results.getDouble("AVGCHANGE");
                           Double Ranking = results.getDouble("RANKING");
                          

                           STask3B taskData = new STask3B(rregion, sAvg, eAvg, avgChange, Ranking);
                           sData.add(taskData);
           
                       }
                } else {
                    //TODO BOTH

                }
            } else {
                //QUERY FOR ABSOLUTE, CITY/STATE, TEMPERATURE
               // sDataQuery = "WITH FirstQuery AS (SELECT A.Country, A.SYEAR, A.SAVG, B.EYEAR, B.EAVG, ABS(B.EAVG - A.SAVG) AS NAVGCHANGE FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND COUNTRY LIKE '" + country + "') AS A CROSS JOIN (SELECT COUNTRY, YEAR AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + " AND COUNTRY LIKE '" + country + "') AS B WHERE A.COUNTRY = B.COUNTRY) SELECT C.Country, C.SYEAR, C.SAVG, B.EYEAR, B.EAVG, ABS(B.EAVG - C.SAVG) AS AVGCHANGE, FirstQuery.NAVGCHANGE AS COMPCHANGE, ABS((B.EAVG - C.SAVG) - FirstQuery.NAVGCHANGE) AS RANKING, S." + region + " AS REGION, CTS1852.AVGTEMP AS RSAVGTEMP, CTS1873.AVGTEMP AS REAVGTEMP FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND SAVG > 0) AS C CROSS JOIN (SELECT DISTINCT COUNTRY, YEAR AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + ") AS B INNER JOIN FirstQuery ON C.COUNTRY = B.COUNTRY INNER JOIN (SELECT DISTINCT COUNTRY, " + region + " FROM " + region + ") AS S ON S.COUNTRY = C.COUNTRY LEFT JOIN COUNTRYTEMPOBSERVATION CTS1852 ON CTS1852.COUNTRY = S.COUNTRY AND CTS1852.YEAR = " + SYear + " LEFT JOIN COUNTRYTEMPOBSERVATION CTS1873 ON CTS1873.COUNTRY = S.COUNTRY AND CTS1873.YEAR = " + EYear + " ORDER BY ABS(((AVGCHANGE - COMPCHANGE) / AVGCHANGE) * 100) ASC LIMIT " + numResults + ";";
                sDataQuery = "WITH FirstQuery AS (SELECT A." + region + " AS REGION, A.SYEAR, A.SAVG, B.EYEAR, B.EAVG, ABS(B.EAVG - A.SAVG) AS NAVGCHANGE FROM (SELECT DISTINCT " + region + ", YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM " + region + " WHERE YEAR = " + SYear + " AND " + region + " LIKE '" + country + "') AS A CROSS JOIN (SELECT " + region + ", year AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM " + region + " WHERE YEAR = " + EYear + " AND " + region + " LIKE '" + country + "') AS B WHERE A." + region + " = B." + region + ") SELECT C." + region + ", C.SYEAR, C.SAVG, B.EYEAR, B.EAVG, ABS(B.EAVG - C.SAVG) AS AVGCHANGE, FirstQuery.NAVGCHANGE AS COMPCHANGE, ABS((B.EAVG - C.SAVG) - FirstQuery.NAVGCHANGE) as RANKING FROM (SELECT DISTINCT " + region + ", YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM " + region + " WHERE YEAR = " + SYear + " AND SAVG > 0) AS C CROSS JOIN (SELECT DISTINCT " + region + ", year AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM " + region + " WHERE YEAR = " + EYear + ") AS B INNER JOIN FirstQuery WHERE C." + region + " = B." + region + " and C.SAVG IS NOT NULL ORDER BY ABS(AVGCHANGE - COMPCHANGE) ASC LIMIT " + numResults + ";";
                
                ResultSet results = statement.executeQuery(sDataQuery);
                while (results.next()) {

                      //String rregion = results.getString("REGION");
                      String State = "";
                       Double avgChange = results.getDouble("AVGCHANGE");
                       Double Ranking = results.getDouble("RANKING");
                       if (region.equals("State")) {
                        State = results.getString("STATE");
                       } else {
                        State = results.getString("CITY");
                       }
                       
                       Double SSavg = results.getDouble("SAVG");
                        Double SEavg = results.getDouble("EAVG");

                       STask3B taskData = new STask3B(State, SSavg, SEavg, avgChange, Ranking);
                       sData.add(taskData);
            }
            }
            //BELOW HERE IS RELATIVE, QUERIES MUST BE CHANGED, ALSO NEED BOTH
            } else if (method.equals("Relative")){
                if (region.equals("Country")) {
                    if (comparison.equals("Temperature")) {
                        //QUERY FOR RELATIVE, COUNTRY, TEMPERATURE (NEEDS VARIABLES SYEAR, EYEAR, COUNTRY)
                        //relativE IS METHOD, COUNTRY IS REGION, TEMPERATURE IS COMPARISON, NUMRESULTS IS NOT NEEDED
    
                       // sDataQuery = "WITH FirstQuery AS (SELECT A.Country, A.SYEAR, A.SAVG, B.EYEAR, B.EAVG, ABS(B.EAVG - A.SAVG) AS NAVGCHANGE FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND COUNTRY LIKE '" + country + "') AS A CROSS JOIN (SELECT Country, year AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + " AND COUNTRY LIKE '" + country + "') AS B WHERE A.COUNTRY = B.COUNTRY) SELECT C.Country, C.SYEAR, C.SAVG, B.EYEAR, B.EAVG, ABS(B.EAVG - C.SAVG) AS AVGCHANGE, FirstQuery.NAVGCHANGE AS COMPCHANGE, ABS((B.EAVG - C.SAVG) - FirstQuery.NAVGCHANGE) as RANKING FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND SAVG > 0) AS C CROSS JOIN (SELECT DISTINCT Country, year AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + ") AS B INNER JOIN FirstQuery WHERE C.COUNTRY = B.COUNTRY ORDER BY ABS(AVGCHANGE - COMPCHANGE) ASC LIMIT " + numResults + ";";
                        sDataQuery = "WITH FirstQuery AS (SELECT A.Country, A.SYEAR, A.SAVG, B.EYEAR, B.EAVG, (((B.EAVG - A.SAVG) / A.SAVG) * 100) AS AVGCHANGE FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND COUNTRY LIKE '" + country + "') AS A CROSS JOIN (SELECT Country, year AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + " AND COUNTRY LIKE '" + country + "') AS B WHERE A.COUNTRY = B.COUNTRY) SELECT C.Country, C.SYEAR, C.SAVG, B.EYEAR, B.EAVG, (((B.EAVG - C.SAVG) / C.SAVG) * 100) AS AVGCHANGE, ABS((((B.EAVG - C.SAVG) / C.SAVG) * 100) - FirstQuery.AVGCHANGE) AS Ranking FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND SAVG > 0) AS C CROSS JOIN (SELECT DISTINCT Country, year AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + ") AS B INNER JOIN FirstQuery WHERE C.COUNTRY = B.COUNTRY AND RANKING IS NOT NULL ORDER BY RANKING ASC LIMIT " + numResults + ";";
    
    
                                ResultSet results = statement.executeQuery(sDataQuery);
                                while (results.next()) {
                                       double sAvg = results.getDouble("SAVG");
                                       double eAvg = results.getDouble("EAVG");
                                       String rregion = results.getString("COUNTRY");
                                       Double avgChange = results.getDouble("AVGCHANGE");
                                       Double Ranking = results.getDouble("RANKING");

    
                                       STask3B taskData = new STask3B(rregion, sAvg, eAvg, avgChange, Ranking);
                                       sData.add(taskData);
                       
                                   }
                    } else if (comparison.equals("Population")){
                        //QUERY FOR RELATIVE, COUNTRY, POPULATION
                        //TODO
                        //sDataQuery = "WITH FirstQuery AS (SELECT A.Country, A.SYEAR, A.SPOP, B.EYEAR, B.EPOP, ABS(B.EPOP - A.SPOP) AS NAVGCHANGE FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, POPULATION AS SPOP, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND COUNTRY LIKE '"+ country + "') AS A CROSS JOIN (SELECT Country, year AS EYEAR, POPULATION AS EPOP FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + " AND COUNTRY LIKE '" + country + "') AS B WHERE A.COUNTRY = B.COUNTRY) SELECT C.Country, C.SYEAR, C.SPOP, B.EYEAR, B.EPOP, ABS(B.EPOP - C.SPOP) AS AVGCHANGE, FirstQuery.NAVGCHANGE AS COMPCHANGE, ABS((B.EPOP - C.SPOP) - FirstQuery.NAVGCHANGE) as RANKING FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, POPULATION AS SPOP, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND SPOP > 0) AS C CROSS JOIN (SELECT DISTINCT Country, year AS EYEAR, POPULATION AS EPOP FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + ") AS B INNER JOIN FirstQuery WHERE C.COUNTRY = B.COUNTRY ORDER BY ABS(AVGCHANGE - COMPCHANGE) ASC LIMIT " + numResults + ";"; 
                        sDataQuery = "WITH FirstQuery AS (SELECT A.Country, A.SYEAR, A.SPOP, B.EYEAR, B.EPOP, (((B.EPOP - A.SPOP) / A.SPOP) * 100) AS AVGCHANGE FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, POPULATION AS SPOP, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND COUNTRY LIKE '" + country + "') AS A CROSS JOIN (SELECT Country, year AS EYEAR, POPULATION AS EPOP FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + " AND COUNTRY LIKE '" + country + "') AS B WHERE A.COUNTRY = B.COUNTRY) SELECT C.Country, C.SYEAR, C.SPOP, B.EYEAR, B.EPOP, ABS(((B.EPOP - C.SPOP) / C.SPOP) * 100) AS AVGCHANGE, ABS((((B.EPOP - C.SPOP) / C.SPOP) * 100) - FirstQuery.AVGCHANGE) AS Ranking FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, POPULATION AS SPOP, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND SPOP > 0) AS C CROSS JOIN (SELECT DISTINCT Country, year AS EYEAR, POPULATION AS EPOP FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + ") AS B INNER JOIN FirstQuery WHERE C.COUNTRY = B.COUNTRY AND RANKING IS NOT NULL ORDER BY ABS(RANKING) ASC LIMIT " + numResults + ";";
                        
                        ResultSet results = statement.executeQuery(sDataQuery);
                        while (results.next()) {
                               double sAvg = results.getDouble("SPOP");
                               double eAvg = results.getDouble("EPOP");
                               String rregion = results.getString("COUNTRY");
                               Double avgChange = results.getDouble("AVGCHANGE");
                               Double Ranking = results.getDouble("RANKING");

    
                               STask3B taskData = new STask3B(rregion, sAvg, eAvg, avgChange, Ranking);
                               sData.add(taskData);
               
                           }
                    } else {
                        //BOTH
    
                    }
                } else {
                    //QUERY FOR RELATIVE, CITY/STATE, TEMPERATURE 
                    //TODO: NEEDS TO BE FIXED WITH NEW METHOD OF STATE/CITY
                    //sDataQuery = "WITH FirstQuery AS (SELECT A.Country, A.SYEAR, A.SAVG, B.EYEAR, B.EAVG, ABS(B.EAVG - A.SAVG) AS NAVGCHANGE FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND COUNTRY LIKE '" + country + "') AS A CROSS JOIN (SELECT COUNTRY, YEAR AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + " AND COUNTRY LIKE '" + country + "') AS B WHERE A.COUNTRY = B.COUNTRY) SELECT C.Country, C.SYEAR, C.SAVG, B.EYEAR, B.EAVG, ABS(B.EAVG - C.SAVG) AS AVGCHANGE, FirstQuery.NAVGCHANGE AS COMPCHANGE, ABS((B.EAVG - C.SAVG) - FirstQuery.NAVGCHANGE) AS RANKING, S." + region + " AS REGION, CTS1852.AVGTEMP AS RSAVGTEMP, CTS1873.AVGTEMP AS REAVGTEMP FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND SAVG > 0) AS C CROSS JOIN (SELECT DISTINCT COUNTRY, YEAR AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + ") AS B INNER JOIN FirstQuery ON C.COUNTRY = B.COUNTRY INNER JOIN (SELECT DISTINCT COUNTRY, " + region + " FROM " + region + ") AS S ON S.COUNTRY = C.COUNTRY LEFT JOIN COUNTRYTEMPOBSERVATION CTS1852 ON CTS1852.COUNTRY = S.COUNTRY AND CTS1852.YEAR = " + SYear + " LEFT JOIN COUNTRYTEMPOBSERVATION CTS1873 ON CTS1873.COUNTRY = S.COUNTRY AND CTS1873.YEAR = " + EYear + " ORDER BY ABS(AVGCHANGE - COMPCHANGE) ASC LIMIT " + numResults + ";";
                    //sDataQuery = "WITH FirstQuery AS (SELECT A.Country, A.SYEAR, A.SAVG, B.EYEAR, B.EAVG, (((B.EAVG - A.SAVG) / A.SAVG) * 100) AS AVGCHANGE FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND COUNTRY LIKE '" + country + "') AS A CROSS JOIN (SELECT Country, year AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR =  "+ EYear + " AND COUNTRY LIKE '" + country + "') AS B WHERE A.COUNTRY = B.COUNTRY) SELECT C.Country, C.SYEAR, C.SAVG, B.EYEAR, B.EAVG, S." + region + " AS REGION, (((B.EAVG - C.SAVG) / C.SAVG) * 100) AS AVGCHANGE, FIRSTQUERY.AVGCHANGE AS COMPCHANGE, (((B.EAVG - C.SAVG) / C.SAVG) * 100) - FIRSTQUERY.AVGCHANGE AS ORDEREDBY FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND SAVG > 0) AS C CROSS JOIN (SELECT DISTINCT Country, year AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + ") AS B INNER JOIN FirstQuery ON C.COUNTRY = B.COUNTRY INNER JOIN (SELECT DISTINCT COUNTRY, " + region + " FROM " + region + ") AS S ON S.COUNTRY = C.COUNTRY LEFT JOIN COUNTRYTEMPOBSERVATION CTS1852 ON CTS1852.COUNTRY = S.COUNTRY AND CTS1852.YEAR = " + SYear + " LEFT JOIN COUNTRYTEMPOBSERVATION CTS1873 ON CTS1873.COUNTRY = S.COUNTRY AND CTS1873.YEAR = " + EYear + " ORDER BY ABS(ORDEREDBY) ASC LIMIT " + numResults + ";";
                    //sDataQuery = "WITH FirstQuery AS (SELECT A." + region + " AS REGION, A.SYEAR, A.SAVG, B.EYEAR, B.EAVG, (((B.EAVG - A.SAVG) / A.SAVG) * 100) AS AVGCHANGE FROM (SELECT DISTINCT " + region + ", YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM " + region + " WHERE YEAR = " + SYear + " AND " + region + " LIKE '" + country + "') AS A CROSS JOIN (SELECT " + region + ", year AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM " + region + " WHERE YEAR = " + EYear + " AND " + region + " LIKE '" + country + "') AS B WHERE A." + region + " = B." + region + ") SELECT C." + region + ", C.SYEAR, C.SAVG, B.EYEAR, B.EAVG, (((B.EAVG - C.SAVG) / C.SAVG) * 100) AS AVGCHANGE, ABS((((B.EAVG - C.SAVG) / C.SAVG) * 100) - FirstQuery.AVGCHANGE) AS Ranking FROM (SELECT DISTINCT " + region + ", YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM " + region + " WHERE YEAR = " + SYear + " AND SAVG > 0) AS C CROSS JOIN (SELECT DISTINCT " + region + ", year AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM " + region + " WHERE YEAR = " + EYear + ") AS B LIMIT " + numResults + ";";
                    sDataQuery = "WITH FirstQuery AS (SELECT A." + region + " AS REGION, A.SYEAR, A.SAVG, B.EYEAR, B.EAVG, (((B.EAVG - A.SAVG) / A.SAVG) * 100) AS NAVGCHANGE FROM (SELECT DISTINCT " + region + ", YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM " + region + " WHERE YEAR = " + SYear + " AND " + region + " LIKE '" + country + "') AS A CROSS JOIN (SELECT " + region + ", year AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM " + region + " WHERE YEAR = " + EYear + " AND " + region + " LIKE '" + country + "') AS B WHERE A." + region + " = B." + region + ") SELECT C." + region + ", C.SYEAR, C.SAVG, B.EYEAR, B.EAVG, (((B.EAVG - C.SAVG) / C.SAVG) * 100) AS AVGCHANGE, FirstQuery.NAVGCHANGE AS COMPCHANGE, ABS((((B.EAVG - C.SAVG) / C.SAVG) * 100) - FirstQuery.NAVGCHANGE) as RANKING FROM (SELECT DISTINCT " + region + ", YEAR AS SYEAR, AVGTEMP AS SAVG, COUNTRY FROM " + region + " WHERE YEAR = " + SYear + " AND SAVG > 0) AS C CROSS JOIN (SELECT DISTINCT " + region + ", year AS EYEAR, MAXTEMP AS EMAX, MINTEMP AS EMIN, AVGTEMP AS EAVG FROM " + region + " WHERE YEAR = " + EYear + ") AS B INNER JOIN FirstQuery WHERE C." + region + " = B." + region + " and C.SAVG IS NOT NULL ORDER BY ABS(AVGCHANGE - COMPCHANGE) ASC LIMIT " + numResults + ";";
                    ResultSet results = statement.executeQuery(sDataQuery);
                    while (results.next()) {
                            String State = "";
                           Double avgChange = results.getDouble("AVGCHANGE");
                           Double Ranking = results.getDouble("RANKING");
                           //String State = results.getString("REGION");
                           Double SSavg = results.getDouble("SAVG");
                           if (region.equals("State")) {
                            State = results.getString("STATE");
                           } else {
                            State = results.getString("CITY");
                           }
                            Double SEavg = results.getDouble("EAVG");
    
                           STask3B taskData = new STask3B(State, SSavg, SEavg, avgChange, Ranking);
                           sData.add(taskData);
                }
                }

            }
        
          
               
                    
              //  ResultSet results = statement.executeQuery(sDataQuery);
        
              // while (results.next()) {
                 //   double sAvg = results.getDouble("SAVG");
                //    double eAvg = results.getDouble("EAVG");
               //     String rregion = "";
               //     if (region.equals("City")) {
               //         rregion = results.getString("City");
               //     } else {
               //          rregion = results.getString("STATE");
               //     }
               //     STask3B taskData = new STask3B(rregion, sAvg, eAvg, avgChange, Ranking);
               //     sData.add(taskData);
    
              //  }
        
                statement.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        
            return sData;
        }


   public ArrayList<SBoth3B> getBoth(String region, String SYear, String EYear, String country, String method, String numResults, String comparison) {
        ArrayList<SBoth3B> sbData = new ArrayList<SBoth3B>();
                // Setup the variable for the JDBC connection
                Connection connection = null;

                try {
                    // Connect to JDBC data base
                    connection = DriverManager.getConnection(DATABASE);
        
                    // Prepare a new SQL Query & Set a timeout
                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(30);
                    String query = "";
                    // The Query
                    if (method.equals("Absolute")) {
                    query = "WITH FirstQuery AS (SELECT A.Country, A.SYEAR, A.SPOP, B.EYEAR, B.EPOP, A.SAVG, B.EAVG, ABS(B.EPOP - A.SPOP) AS NAVGCHANGE, ABS(B.EAVG - A.SAVG) AS TAVGCHANGE FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, POPULATION AS SPOP, COUNTRY, AVGTEMP AS SAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND COUNTRY LIKE '" + country + "') AS A CROSS JOIN (SELECT Country, year AS EYEAR, POPULATION AS EPOP, AVGTEMP AS EAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + " AND COUNTRY LIKE '" + country + "') AS B WHERE A.COUNTRY = B.COUNTRY) SELECT C.Country, C.SYEAR, C.SPOP, B.EYEAR, B.EPOP, C.SAVG, B.EEAVG, ABS(B.EPOP - C.SPOP) AS POPAVGCHANGE, ABS(B.EEAVG - C.SAVG) AS TEMPAVGCHANGE, FirstQuery.NAVGCHANGE AS COMPCHANGE, FirstQuery.TAVGCHANGE AS TCOMPCHANGE, ABS((B.EPOP - C.SPOP) - FirstQuery.NAVGCHANGE) as POPRANKING, ABS((B.EEAVG - C.SAVG) - FirstQuery.TAVGCHANGE) as TEMPRANKING FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, POPULATION AS SPOP, COUNTRY, AVGTEMP AS SAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND SPOP > 0) AS C CROSS JOIN (SELECT DISTINCT Country, year AS EYEAR, POPULATION AS EPOP, AVGTEMP AS EEAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + ") AS B INNER JOIN FirstQuery WHERE C.COUNTRY = B.COUNTRY ORDER BY ABS(POPRANKING), abs (tempranking) ASC LIMIT " + numResults + ";";
                    } else {
                    query = "WITH FirstQuery AS (SELECT A.Country, A.SYEAR, A.SPOP, B.EYEAR, B.EPOP, A.SAVG, B.EAVG, ((B.EPOP - A.SPOP)/ A.SPOP) * 100 AS NAVGCHANGE, ((B.EAVG - A.SAVG) / A.SAVG) * 100 AS TAVGCHANGE FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, POPULATION AS SPOP, COUNTRY, AVGTEMP AS SAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND COUNTRY LIKE '" + country + "') AS A CROSS JOIN (SELECT Country, year AS EYEAR, POPULATION AS EPOP, AVGTEMP AS EAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + " AND COUNTRY LIKE '" + country + "') AS B WHERE A.COUNTRY = B.COUNTRY) SELECT C.Country, C.SYEAR, C.SPOP, B.EYEAR, B.EPOP, C.SAVG, B.EEAVG, ((B.EPOP - C.SPOP)/ C.SPOP) * 100 AS POPAVGCHANGE, ((B.EEAVG - C.SAVG) / C.SAVG) * 100 AS TEMPAVGCHANGE, FirstQuery.NAVGCHANGE AS COMPCHANGE, FirstQuery.TAVGCHANGE AS TCOMPCHANGE, ((((B.EPOP - C.SPOP)/C.SPOP)* 100) - FirstQuery.NAVGCHANGE) as POPRANKING, ((((B.EEAVG - C.SAVG)/ C.SAVG) * 100) - FirstQuery.TAVGCHANGE) as TEMPRANKING FROM (SELECT DISTINCT COUNTRY, YEAR AS SYEAR, POPULATION AS SPOP, COUNTRY, AVGTEMP AS SAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + SYear + " AND SPOP > 0) AS C CROSS JOIN (SELECT DISTINCT Country, year AS EYEAR, POPULATION AS EPOP, AVGTEMP AS EEAVG FROM COUNTRYTEMPOBSERVATION WHERE YEAR = " + EYear + ") AS B INNER JOIN FirstQuery WHERE C.COUNTRY = B.COUNTRY AND POPAVGCHANGE IS NOT NULL ORDER BY ABS(POPRANKING), abs (tempranking) ASC LIMIT " + numResults + ";";
                    }
                    // Get Result
                    ResultSet results = statement.executeQuery(query);
        
                    // Process all of the results
                    while (results.next()) {
                        String cname = results.getString("COUNTRY");
                        double SAVG = results.getDouble("SAVG");
                        double EAVG = results.getDouble("EEAVG");
                        double SPOP = results.getDouble("SPOP");
                        double EPOP = results.getDouble("EPOP");
                        double TAC = results.getDouble("TEMPAVGCHANGE");
                        double PAC = results.getDouble("POPAVGCHANGE");
                        double TRANKING = results.getDouble("TEMPRANKING");
                        double PRANKING = results.getDouble("POPRANKING");
                        SBoth3B taskdata = new SBoth3B(cname, SAVG, EAVG, SPOP, EPOP, TAC, TRANKING, PAC, PRANKING);
                        sbData.add(taskdata);

                    }
        
                    // Close the statement because we are done with it
                    statement.close();
                } catch (SQLException e) {
                    // If there is an error, lets just pring the error
                    System.err.println(e.getMessage());
                } finally {
                    // Safety code to cleanup
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        // connection close failed.
                        System.err.println(e.getMessage());
                    }
                }
        return sbData;

    
   }
       

        // public ArrayList<Data> getdata(String Year1, String Year2,String CountryName,String sortCriterion,String sortOrder) {
        //     ArrayList<Data> stempdata = new ArrayList<Data>();
        
        //     Connection connection = null;
        
        //     try {
        //         connection = DriverManager.getConnection(JDBCConnection.DATABASE);
        //         Statement statement = connection.createStatement();
        //         statement.setQueryTimeout(30);
        
        //         //String sDataQuery = "Select * FROM " + region + " WHERE (Year =" + Year + ") AND (Country LIKE '" + country + "') ";
        //         //String sDataQuery = "Select * FROM STATE WHERE (Year = 1890) AND (COUNTRY LIKE 'Australia')";
    
        //         String sDataQuery ="SELECT p.*, cto.Population AS cto_Population, cto.AvgTemp, cto.Region FROM Population p  LEFT JOIN CountryTempObservation cto ON p.CountryName = cto."+ CountryName + "AND cto.Year BETWEEN + " + Year1 + " AND " + Year2 +" ORDER BY cto." + sortCriterion + " " + sortOrder;
                    
        //         ResultSet results = statement.executeQuery(sDataQuery);
        
        //        while (results.next()) {
        //         int Population = results.getInt("cto_Population");
        //         double AvgTemp = results.getDouble("cto_AvgTemp");
        //         String Region = results.getString("cto.Region");
        //         String Country = results.getString("p.CountryName");
        //         for (int year = 1960; year <= 2013; year++) {
        //             int population = results.getInt(String.valueOf(year));
        //             Data data = new Data(Population,AvgTemp,Region,Country,population);
        //             stempdata.add(data);
    
        //         }
        //     }
        //         statement.close();
            
        //     } catch (SQLException e) {
        //         System.err.println(e.getMessage());
        //     } finally {
        //         try {
        //             if (connection != null) {
        //                 connection.close();
        //             }
        //         } catch (SQLException e) {
        //             System.err.println(e.getMessage());
        //         }
            // }
        
            // return stempdata;











            public ArrayList<STask3A> getTask3A(String region, String SYear, String yeartime, String minPopulation,String maxPopulation,String minAverage,String maxAverage) {
                ArrayList<STask3A> sub3Adata = new ArrayList<STask3A>();
            
                Connection connection = null;
            
                try {
                    connection = DriverManager.getConnection(JDBCConnection.DATABASE);
                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(30);
            
                   
                    String sDataQuery = "SELECT c." + region + ", c.Avgtemp FROM " + region  + " c JOIN COUNTRYTEMPOBSERVATION co ON c.Country = co.Country  WHERE c.Year BETWEEN " + SYear + " AND " + (Integer.parseInt(SYear) + Integer.parseInt(yeartime))  + " AND co.POPULATION BETWEEN " + minPopulation + " AND " + maxPopulation + " OR c.Avgtemp BETWEEN "+ minAverage+ " AND "+ maxAverage + " GROUP BY c.Country";
                   
                   
                    ResultSet results = statement.executeQuery(sDataQuery);
            
                    while (results.next()) {
                        double AvgTemp = results.getDouble("Avgtemp");

                        String rregion = "";
                    if (region.equals("City")) {
                        rregion = results.getString("City");
                    } else if (region.equals("State")) {
                         rregion = results.getString("STATE");
                    } 
                    STask3A task3AData = new STask3A(rregion, AvgTemp);
                    sub3Adata.add(task3AData);
    
                }
        
                statement.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
                
            
                return  sub3Adata;








                




            }
        }
            
        
    

