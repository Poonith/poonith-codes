

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
public class PageST2A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2A.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";
html += "<link rel='stylesheet' type='text/css' href='common.css' />";
html += "<head>" + "<title>Subtask 2.1</title>";
html += "<style>" +
    "body {" +
    "  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;" +
    "  color: #333;" +
    "  background-color: #98b0c2b0;" +
    "}" +
    ".content {" +
    "  max-width: 1100px;" +
    "  margin: 20px auto;" +
    "  padding: 20px;" +
    "  background-color: #fff;" +
    "  border-radius: 5px;" +
    "  border: 2px solid CadetBlue;" +
    "}" +
    ".form-group {" +
    "  margin-bottom: 20px;" +
    "}" +
    "label {" +
    "  display: block;" +
    "  font-weight: bold;" +
    "  margin-bottom: 5px;" +
    "}" +
    "select, input {" +
    "  width: 100%;" +
    "  padding: 10px;" +
    "  border-radius: 4px;" +
    "  border: 2px solid CadetBlue;" +
    "  font-size: 16px;" +
    "  color: #333;" +
    "}" +
    ".btn-primary {" +
    "  color: #fff;" +
    "  background-color: #007bff;" +
    "  border-color: #007bff;" +
    "  padding: 10px 20px;" +
    "  text-align: center;" +
    "  display: inline-block;" +
    "  border-radius: 4px;" +
    "  cursor: pointer;" +
    "  font-size: 18px;" +
    "  margin-top: 10px;" +
    "}" +
    ".country-table {" +
    "  width: 70%;" +
    "  margin: 0 auto;" +
    "  border-collapse: collapse;" +
    "  font-family: Arial, sans-serif;" +
    "}" +
    ".country-table th, .country-table td {" +
    "  border: 1px solid #ddd;" +
    "  padding: 8px;" +
    "  text-align: center;" +
    "}" +
    ".country-table td {background-color: white;}" +
    ".country-table th {" +
    "  padding-top: 12px;" +
    "  padding-bottom: 12px;" +
    "  text-align: center;" +
    "  background-color: #4CAF50;" +
    "  color: white;" +
    "}" +
    "</style>";

      

        


        // Add some CSS (external file)
        
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav' style='background-color:#637c94'>
                <a href='/' style='background-color:#728ca3; border-radius:8px;'>Homepage</a>
                <a href='mission.html' style='background-color:#728ca3; border-radius:8px;'>Our Mission</a>
                <a href='page2A.html' style='background-color:#97b1c9; border-radius:8px;'>Country / Global Change</a>
                <a href='page2B.html' style='background-color:#728ca3; border-radius:8px;'>State / City Change</a>
                <a href='page3A.html' style='background-color:#728ca3; border-radius:8px;'>Advanced Temp Change</a>
                <a href='page3B.html' style='background-color:#728ca3; border-radius:8px;'>Sort by Similarity</a>
            </div>
        """;
     

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>Subtask 2.A</h1>
            </div>
        """;

        // Add Div for page Content
        html += "<div class='content'>";
      
     
     
        
        
        html+= "<div class='form-group'>";
        html += "<form action='/page2A.html' method='post'>";


        
        html += """
     
            <div class='container mt-5'>
                Please select your filters 
                <div class='form-group'>
                    <label for='ViewBy'>View By:</label>
                    <select class='form-control' id='ViewBy' name='ViewBy'>
                        <option value=''>--Please choose an option--</option>
                        <option value='Region'>Region</option>
                        <option value='Country'>Country</option>
                    </select>
                </div>
                <button type='submit' class='btn btn-primary btn-block'>Submit!</button>
            </div>
            </form>
       
        """;
        
             
     
                
                    
                      

String ViewBy = context.formParam("ViewBy");

if(ViewBy == null) {
    html += "";
} else if(ViewBy.equals("Region")) {
    html += outputRegions();
} else if(ViewBy.equals("Country")) {
    html += outputCountries();
}
        





html+="Please select a starting year and an ending year to view the changes in average temperature and population during that period.";

html = html + """
    <div class = 'form-group'>
    <form action='/page2A.html' method='post'>
                <label for='StartYear'> Choose a Starting Year: </label>
                <select id='SYear' name = 'SYear'>
                

""";
JDBCConnection jdbc = new JDBCConnection(); 
        ArrayList<String> Years = jdbc.getYear();

        for (int i=0; i<Years.size(); i++) {
            String year = Years.get(i);
            html = html + "<option value ='" + year + "'>" + year + "</option>";
        }

    html = html + "</select> </div";




html = html + """
        <div class = 'form-group'>
               <label for='EndYear'> Choose an End Year: </label>
                <select id='EYear' name = 'EYear'>
                
            """;
            
            for (int i=0; i<Years.size(); i++) {
                String year = Years.get(i);
                html = html + "<option value ='" + year + "'>" + year + "</option>";
            }
        html = html + "</select> </div>";

     



html+="""
   
        <label for='sortCriterion'> Sort by: </label>
        <select id='sortCriterion' name='sortCriterion'>
            <option value='AvgTemp'>Temperature</option>
            <option value='Population'>Population</option>
        </select>
   
        <label for='sortOrder'> Order: </label>
        <select id='sortOrder' name='sortOrder'>
            <option value='asc'>Ascending</option>
            <option value='desc'>Descending</option>
            </select>
            </div>
           
        """;
           

     
        ArrayList<String> Countries = jdbc.getCountryname();
       
        html += """
           
                <label for='Country'> Choose a Country: </label>
                <select id='Country' name='CountryName'>
        """;
            
        for (int i=0; i<Countries.size(); i++) {
            String country = Countries.get(i);
            html += "<option value='" + country + "'>" + country + "</option>";
        }

     
        
        html += """
            <br></br>
            </select>
            <div>
                <button type='submit' class='btn-primary'>submit</button>
            </div>
        </form>
        """;
            


        String SYear = context.formParam("SYear");
        String EYear = context.formParam("EYear");
        String sortCriterion = context.formParam("sortCriterion");
        String sortOrder = context.formParam("sortOrder");
        String CountryName = context.formParam("CountryName");




        if((sortCriterion == null) || (sortOrder == null )|| (SYear == null) || (EYear == null )|| (CountryName == null)) {
            html += "**Select your options!**";
        }else if(Integer.valueOf(SYear) > Integer.valueOf(EYear)) {
            html = html + "**Error: Start year must be before end year**";
        }
        else if(SYear == EYear) {
            html = html + "**Please Select different Year**";
        }
        else {
            html += outputPopTempSortData(sortCriterion, sortOrder, CountryName, SYear, EYear);
        }






        html+="</div>";
        
        // Footer
        html+="<br><br>";
        html += """
            <div class='footer' style='background-color:#637c94;''>
                <p>Your Portal to Climate Change Insights - Created 2023 by Elizabeth Pollard & Devanshu Poonith</p>
            </div>
        """;
      
        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        
        // Makes Javalin render the webpage
        context.html(html);
        

       

    }




   
    





    public String outputCountries() {
        int cols = 10; // Define the number of columns for your table
       
        String html = "<table class='country-table'>"; // Use the CSS class
        
        html += "<tr><th colspan='10'>Country name</th></tr>";

        JDBCConnection jdbc = new JDBCConnection(); 
        ArrayList<String> Countries = jdbc.getCountry();
    
        for (int i=0; i<Countries.size(); i++) {
            if (i % cols == 0) { // If 'i' is divisible by 'cols', start a new row
                if (i > 0) { // If not the first element, close the previous row
                    html += "</tr>";
                }
                html += "<tr>";
            }
            String country = Countries.get(i);
            html += "<td>" + country + "</td>";
        }
    
        // Close the last row if it wasn't closed in the loop
        if (Countries.size() % cols != 0) {
            html += "</tr>";
        }
    
        html += "</table>";
        return html;
    }
    








    public String outputRegions() {
        int cols = 10; // Define the number of columns for your table
       
        String html = "<table class='country-table'>"; // Use the CSS class
        html += "<tr><th colspan='10'>Region name</th></tr>";

        JDBCConnection jdbc = new JDBCConnection(); 
        ArrayList<String> Regions= jdbc.getRegion();
    
        for (int i=0; i<Regions.size(); i++) {
            if (i % cols == 0) { // If 'i' is divisible by 'cols', start a new row
                if (i > 0) { // If not the first element, close the previous row
                    html += "</tr>";
                }
                html += "<tr>";
            }
            String region = Regions.get(i);
            html += "<td>" + region + "</td>";
        }
    
        // Close the last row if it wasn't closed in the loop
        if (Regions.size() % cols != 0) {
            html += "</tr>";
        }
    
        html += "</table>";
        return html;
    }




    public String outputPopTempSortData(String sortCriterion, String sortOrder,String Year1,String Year2,String Countryname) {
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<TempPopSortData> tempPopsortdata = jdbc.getSortTempPopData(sortCriterion,sortOrder,Year1,Year2,Countryname);
    
        html += "<table class='country-table'>";
        html += "<tr><th>Population</th><th>Average Temperature</th><th>Year</th></tr>";
    
        for (TempPopSortData emp: tempPopsortdata) {
            html += "<tr><td>" + emp.getPopulation() + "</td><td>" + emp.getAvgTemp() +  "</td><td>"+   emp.getYear() +"</td></tr>";
        }
    
        html += "</table>";
    
        return html;
    }
    
    


}

