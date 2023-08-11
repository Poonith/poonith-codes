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
public class PageST2B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2B.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Subtask 2.2</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";
        html = html + """
            <style>

            select, input {
                border: 2px solid Cadetblue;
                border-radius: 4px;
                background-color: AliceBlue;
                width: 200px;
                height: 30px;
            }
            .btn-primary {
                border: 2px solid DarkCyan;
                border-radius: 4px;
                background-color: #91bdbd;
                width: 80px;  
                height: 25px;
                margin-left:125px;
                text-align:center;
                color:white;
                font-weight:bold;
            }
            .form-group {
                font-family: tahoma, sans-serif;
                color:#599690;
                font-weight:600;
            }
            h2 {
                font-family: trebuchet ms, sans-serif;
                font-weight:bold;
                color:#599690;
            }
            h3 {
                font-family: trebuchet ms, sans-serif;
                font-weight:bold;
                color:#599690;
            }
            td {
                color:#434a4a;
            }
            </style>
            """;

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav' style='background-color:#637c94'>
                <a href='/' style='background-color:#728ca3; border-radius:8px;'>Homepage</a>
                <a href='mission.html' style='background-color:#728ca3; border-radius:8px;'>Our Mission</a>
                <a href='page2A.html' style='background-color:#728ca3; border-radius:8px;'>Country / Global Change</a>
                <a href='page2B.html' style='background-color:#97b1c9; border-radius:8px;'>State / City Change</a>
                <a href='page3A.html' style='background-color:#728ca3; border-radius:8px;'>Advanced Temp Change</a>
                <a href='page3B.html' style='background-color:#728ca3; border-radius:8px;'>Sort by Similarity</a>
            </div>
        """;

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>Search States & Cities</h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
        html = html + "<table style = 'background-color:#d8e8e8; text-align:top' border='3' height='50%' width = '95%' bordercolor ='CadetBlue' align=center cellspacing='10'>";
        html = html + """
                <tr>
                <th style ='font-size:25px;width:20%;font-family: trebuchet ms, sans-serif;font-weight:bold;color:#599690;'> Please select your filters </th>
                <th style ='font-size:25px;width:60%;color:#434a4a'> Results </th> </tr>
                <td style='vertical-align:top'> 
""";
        
html = html + """
         <div class = 'form-group'>
                <form action ='/page2B.html' method = 'post'> 
                <label for='Country'> Choose a Country: </label>
                <br>
                <select id='Country' name = 'Country'>
                        
                        """;
                JDBCConnection jdbc = new JDBCConnection();  
                ArrayList<String> Countries = jdbc.getCountry();

                for (int i=0; i<207; i++) {
                    String country = Countries.get(i);
                    html = html + "<option value ='" + country + "'>" + country + "</option>";
                }
            html = html + "</select> </div>";
                        
html = html + """
    <div class = 'form-group'>
                <label for='StartYear'> Choose a Starting Year: </label> <br>
                <select id='SYear' name = 'SYear'>
""";
        
        ArrayList<String> Years = jdbc.getYear();
        for (int i=1750; i<2014; i++) {
            html = html + "<option value ='" + i + "'>" + i + "</option>";
        }
    html = html + "</select> </div";
html = html + """
        <div class = 'form-group'>
               <br> <br> <label for='EndYear'> Choose an End Year: </label> <br>
                <select id='EYear' name = 'EYear'>
            """;
            
            for (int i=1750; i<2014; i++) {
                html = html + "<option value ='" + i + "'>" + i + "</option>";
            }
        html = html + "</select> </div>";


          html = html +"""
                <div class = 'form-group'>
                <p>Select Region: <br>
                <select id='Region' name = 'Region'>
                <option> City </option>
                <option> State </option>
                </select>
                </p>
                </div>

                <div class = 'form-group'>
                <p> Select Sort Method: <br>
                <select id='Method' name = 'Method'>
                <option> Min </option>
                <option> Max </option>
                <option> Avg </option>
                </select>
                </p>

                </div>

                <div class = 'form-group'>
                <br>
                <button type='submit' class='btn-primary'>Submit!</button>

                </div>
                </form>
                <p style='text-align:center'> -------------------- </p>
                <h3 style= 'text-align:center'> How do I use the filters? </h3>
                <p style = 'text-align:center'> This page will show you the difference in minimum, maximum, and average temperature between the years you chose for the selection region of your country!</p> <p style='text-align:center'> It will also order the results by the % change in Minimum, Maximum, or Average depending on your selection. </p> <br>

                </td>
                <td style='vertical-align:top'>  
                """;
                        
                    
                String SYear = context.formParam("SYear");
                String EYear = context.formParam("EYear");
                String Region = context.formParam("Region");
                String Country = context.formParam("Country");
                String method = context.formParam("Method");


                if ((Country == null) || (SYear == null) || (Region == null)) {
                    html = html + "Select your options!";
                } else if(Integer.valueOf(SYear) > Integer.valueOf(EYear)) {
                    html = html + "Error: Start year cannot be after end year";
                } else {
                    html = html + "<table style = <table style = 'background-color:snow; text-align:top' border='0' height='50%' width = '80%' bordercolor ='CadetBlue' align=center cellspacing='10'> <tr> <tr> <th>  </th> <th> </th> </tr> <td>";
                    html = html + testString(Region, SYear, EYear, Country, method);
                    html = html + "</td> <td style = width='100%'' valign = 'top'>";
                    html = html + "</td> </tr> </table>";


                }
             html = html + """

                """;
        html = html + "<p>";


        


        // Close Content div
        html = html + "</div>";



        // Footer
        html = html + """
            </table>
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


    public String testString(String Region, String SYear, String EYear, String Country, String method) {
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<STempChange> stempchange = jdbc.getTempChange(Region, SYear, EYear, Country, method);
        int i = 0;
        if (stempchange.size() > 0) {
            html = html + "<table style = 'background-color:#ebf2f2; text-align:top' border='3' height='50%' width = '100%' bordercolor ='CadetBlue' align=center cellspacing='10'> <tr style='background-color:#91bdbd'> <th> Number </th> <th> Region </th> <th> Change in Min Temp </th> <th> Change in Max Temp </th> <th> Change in Avg Temp </th> <th> Change by Proportion (" + method + ") </th>" ;
            for (STempChange stemp: stempchange) {
                stemp = stempchange.get(i);
                if ((stemp.getEavg() > 0) && (stemp.getSAvg() > 0)) {
                    double avgChange = (stemp.getEavg() - stemp.getSAvg());
                    avgChange = (double)Math.round(avgChange * 1000000d) / 1000000d;
                    double minchange = (stemp.getEmin() - stemp.getSMin());
                    minchange = (double)Math.round(minchange * 1000d) / 1000d;
                    double maxchange = (stemp.getEmax() - stemp.getSMax());
                    maxchange = (double)Math.round(maxchange * 1000d) / 1000d;
                    double propchange = 0.0;
                    if (method.equals("Avg")) {
                       propchange = Math.abs(((stemp.getEavg() - stemp.getSAvg()) / stemp.getSAvg()) * 100);
                       propchange = (double)Math.round(propchange * 1000d) / 1000d;
                    } else if (method.equals("Min")) {
                        propchange = Math.abs(((stemp.getEmin() - stemp.getSMin()) / stemp.getSMin()) * 100);
                        propchange = (double)Math.round(propchange * 1000d) / 1000d;
                    } else if (method.equals("Max")) {
                        propchange = Math.abs(((stemp.getEmax() - stemp.getSMax()) / stemp.getSMax()) * 100);
                        propchange = (double)Math.round(propchange * 1000d) / 1000d;
                    }
                    
                    html = html + "</tr> <td> " + (i + 1) + " </td> <td> " + stemp.getRegion() + " </td> <td> " + minchange + "</td> <td>" + maxchange + "</td> <td>" + avgChange + "</td> <td>" + propchange + "% </td>" ;
                } else {
                    html = html + "</tr> <td>" + (i + 1) + "</td> <td>" + stemp.getRegion() + "</td> <td>"  + SYear + "</td> <td>"+ stemp.getSMin() + "</td> <td>" + stemp.getSMax() + "</td> <td>" + stemp.getSAvg() + "</td> <td>" + EYear + "</td> <td>" + stemp.getEmin() + "</td> <td>" + stemp.getEmax() + "</td> <td>" + stemp.getEavg() + "</td> <td>" + "N/A" + "</td>";
                }
                
                i++;
            }
            html = html + "</tr> </table>";
        } else {
            html = html + "There is no data for your selection. Please try a different year range or region for your desired country.";
        }
        

        return html;
    }
    

}