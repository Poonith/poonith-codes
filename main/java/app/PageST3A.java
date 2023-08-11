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
public class PageST3A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3A.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Subtask 3.1</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";
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
        "  border: 1px solid CadetBlue;" +
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
    
          
    
        
        // Add the body
      
        html = html + "</head>";
        html = html + "<body>";
      

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav' style='background-color:#637c94'>
                <a href='/' style='background-color:#728ca3; border-radius:8px;'>Homepage</a>
                <a href='mission.html' style='background-color:#728ca3; border-radius:8px;'>Our Mission</a>
                <a href='page2A.html' style='background-color:#728ca3; border-radius:8px;'>Country / Global Change</a>
                <a href='page2B.html' style='background-color:#728ca3; border-radius:8px;'>State / City Change</a>
                <a href='page3A.html' style='background-color:#97b1c9; border-radius:8px;'>Advanced Temp Change</a>
                <a href='page3B.html' style='background-color:#728ca3; border-radius:8px;'>Sort by Similarity</a>
            </div>
        """;

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>Subtask 3.A</h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";
html+="<div>";

        // Add HTML for the page content
       
               
                html+=" Select a starting year, a time period (in years), and a geographic region of your Choice!";
                        
html = html + """
    <div class='form-group'>
    <form action ='/page3A.html' method = 'post'> 
    <label for='StartYear'>Choose Starting Years:</label>
    <select id='SYear' name='SYear' multiple>
""";
        JDBCConnection jdbc = new JDBCConnection();  
        ArrayList<String> Years = jdbc.getYear();
        for (int i=0; i<Years.size(); i++) {
            String year = Years.get(i);
            html = html + "<option value ='" + year + "'>" + year + "</option>";
        }
    html = html + "</select></div>";
html = html + """
        <div class = 'form-group'>
               <br> </br> <label for='EndYear'> Enter a time frame in years: </label>
               <input type='number' value='1' id='EYear' name='EYear' min='1' max='207'>
            """;
            
  
        html = html + "</select> </div>";


          html = html +"""
            <div class='form-group'>
            <br></br>
            <label for='Region'>Select a Region:</label>
                <div class = 'form-group'>
                <select id='Region' name = 'Region'>
                <option> City </option>
                <option> State </option>
                </select>
        </div>""";
                        

        html+="<br></br>"+"For selected region, filter results by population size or temperature change. Enjoy your exploration! " ;
         html+="""
    
<div class='form-group'>

    <label for='TempRange'>Temperature Range:</label>
    <input type='number' id='minTemp' name='minTemp' min='1' placeholder='Min Temperature'>
    <input type='number' id='maxTemp' name='maxTemp' min='1' placeholder='Max Temperature'>
</div>



        """;         
html+="""
   

<div class='form-group'>

    <label for='popRange'>Population Range:</label>
    <input type='number' id='minPop' name='minPop' min='1' placeholder='Min Population'>
    <input type='number' id='maxPop' name='maxPop' min='1' placeholder='Max Population'>
</div>


<div class='form-group'>
<br></br>
<button type='submit' class='btn-primary'>Submit!</button>
</div>
</form>
        """;



   

String minTemp = context.formParam("minTemp");
String maxTemp = context.formParam("maxTemp");
String Region =  context.formParam("Region");
String SYear = context.formParam("SYear");
String EYear = context.formParam("EYear");
String minPop = context.formParam("minPop");
String maxPop = context.formParam("maxPop");



if ((Region == null) || (SYear == null) || (EYear == null) || (minPop == null) || (maxPop == null)) {
    html += "Select your options!";
} else if(Integer.valueOf(minPop) > Integer.valueOf(maxPop)){
    html += "Minimum Population value cannot be greater than Maximum Population value";
} 
else{
    html += result(Region, SYear, EYear, minPop, maxPop,minTemp,maxTemp);
}

    
    


html+="</div>";      
// Footer
html+="<br><br>";
html += """
    </div>
    </div>
    <div class='footer' style='background-color:#637c94;''>
    <p>Your Portal to Climate Change Insights - Created 2023 by Elizabeth Pollard & Devanshu Poonith</p>
</div>
""";


        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }










    public String result(String region, String SYear, String yeartime, String minPopulation,String maxPopulation,String minAverage,String maxAverage) {
        String html = "";
      
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<STask3A>sub3Adata = jdbc.getTask3A(region, SYear, yeartime, minPopulation, maxPopulation,minAverage,maxAverage);
        html = "<table class='country-table'>"; // Use the CSS class
        html += "<tr><th>Region</th><th>Average Temperature</th></tr>";
       
        for (STask3A su: sub3Adata) {
            html += "<tr><td>" + su.getRegionlvl() + "</td><td>" + su.getAvgTemperature()  + "</td></tr>";
        }
    
        html += "</table>";
        return html;
    }
    
}   