package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageIndex implements Handler {
   
    
    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";
    

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Homepage</title>";

        // Add some CSS (external file)
       

        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";

        // Add the body
       // Add some CSS (external file)
html = html + "<body>";


        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav' style='background-color:#637c94'>
                <a href='/' style='background-color:#97b1c9; border-radius:8px;'>Homepage</a>
                <a href='mission.html' style='background-color:#728ca3; border-radius:8px;'>Our Mission</a>
                <a href='page2A.html' style='background-color:#728ca3; border-radius:8px;'>Country / Global Change</a>
                <a href='page2B.html' style='background-color:#728ca3; border-radius:8px;'>State / City Change</a>
                <a href='page3A.html' style='background-color:#728ca3; border-radius:8px;'>Advanced Temp Change</a>
                <a href='page3B.html' style='background-color:#728ca3; border-radius:8px;'>Sort by Similarity</a>
            </div>
        """;

        // Add header content block
        html += """
            <div class='header'>
                <div class='text-container'>
                    <h1> Your Portal to Climate Change Insights</h1>
                    <p>Welcome to the ultimate platform where the world of climate change data unfolds. We are dedicated to bringing the complexities of global climate straight to your screen, delivering you the most accurate, up-to-date, and comprehensive climate data.
                    This is the go-to platform for scientists, environmentalists, and anyone eager to understand the changing world around us. Dive into our extensive library of temperature statistics, climate trends, and historical weather patterns. Transform complex data into actionable insights and be part of the solution in addressing the global temperature crisis.</p>
                </div>
            </div>
        """;
        
       


        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
        html = html + """ 
       <h2>Welcome to the Climate Change Insights Portal!</h2>
        <p>Explore our comprehensive database of temperature records, 
        climate trends, and historic weather patterns.</p>
        """;

        html +="<br>   </br>";

        html = html + """ 
            <p><h2>The year range (first and last year) for available data (population and temperature) during these years are presented in the table below</h2></p>""";
    
           


      // Create a JDBC Connection
JDBCConnection jdbc = new JDBCConnection();

// Get the year range data


// Add HTML for the Year Range Data list
















// Add HTML for the table header

html += "<table>";
html += "<tr><th>Year</th><th>Population</th><th>Average Temperature</th></tr>";

ArrayList<yearrange> yearRangeData = jdbc.getYearRangeArray();
for (yearrange year : yearRangeData) {
    html += "<tr><td>" + year.getYear() + "</td><td>" + year.getPopulation() + "</td><td>" + year.getAvgTemp() + "</td></tr>";

}

html += "</table>";
html += "<style>"
      + "table {width: 100%; border-collapse: collapse;}"
      + "th, td {border: 1px solid #ddd; padding: 8px;}"
      + "tr:nth-child(even){background-color: #f2f2f2;}"
      + "th {background-color: #4CAF50; color: white;}"
      + "</style>";

      html += "<style>"
      + "td {background-color: #FFFFFF; color: black;}"
      + "</style>";

// Inside your PageIndex class

// Inside your PageIndex class
/*html+="<h1>        </h1>";
ArrayList<YearCount> yearCountData = jdbc.getYearCount();
for (YearCount yearCount : yearCountData) {
    
    html = html +  "<h3>" + " The total number of years of available data for both global population and temperature: "+ yearCount.getCount()   +  "</h3>";
}*/





html += "</table>";

ArrayList<YearCount> yearCountData = jdbc.getYearCount();
for (YearCount yearCount : yearCountData) {
    html += "<p> The total number of years of available data for both global population and temperature: " + "<b>"+yearCount.getCount()+"</b>" + "</p>";
}



        html = html + "</div>";
       

        // Footer
        html = html + """
            <div class='footer' style='background-color:#637c94;''>
                <p>Your Portal to Climate Change Insights - Created 2023 by Elizabeth Pollard & Devanshu Poonith</p>
            </div>
        """;
        
        html += "</body></html>";
        


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }
    





    public ArrayList<String> getYearRange() {
        // Create the ArrayList of LGA objects to return
        ArrayList<String> yearRangeData= new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String yearRangeQuery = "SELECT MIN(year) as firstYear, MAX(year) as lastYear FROM CountryTempObservation";
            
            // Get Result
            ResultSet results = statement.executeQuery(yearRangeQuery);

            // Process all of the results
            while (results.next()) {
                String name16  = results.getString("year");

                // Add the lga object to the array
                yearRangeData.add(name16);
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
        return yearRangeData;

    
}






public ArrayList<String> getYearCount() {
    // Create the ArrayList of LGA objects to return
    ArrayList<String> yearCountData= new ArrayList<String>();

    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(JDBCConnection.DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String yearCountQuery =  "SELECT COUNT DISTINCT Year FROM CountryTempObservation ";
        
        // Get Result
        ResultSet results = statement.executeQuery(yearCountQuery);

        // Process all of the results
        while (results.next()) {
            String name16  = results.getString("Year");

            // Add the lga object to the array
            yearCountData.add(name16);
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
    return yearCountData;


}


}