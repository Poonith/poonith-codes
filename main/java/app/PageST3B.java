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
public class PageST3B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3B.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Search by Similarity</title>";

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
                    border: 2px solid Darkcyan;
                    border-radius: 4px;
                    background-color: #91bdbd;
                    width: 80px;  
                    margin-left:125px;
                    text-align:center;
                    height: 25px;
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
                <a href='page2B.html' style='background-color:#728ca3; border-radius:8px;'>State / City Change</a>
                <a href='page3A.html' style='background-color:#728ca3; border-radius:8px;'>Advanced Temp Change</a>
                <a href='page3B.html' style='background-color:#97b1c9; border-radius:8px;'>Sort by Similarity</a>
            </div>
        """;

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>Sort by Similarity</h1>
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
                <td style='vertical-align:top;'> 
""";
        
html = html + """
         <div class = 'form-group'>
                <form action ='/page3B.html' method = 'post'> 
                <label for='Country'> Enter name of region for comparison: </label>
                <br>
                <input type='text' id='Country' name = 'Country' value = 'Enter region...'>
                    <br>
                        """;

                JDBCConnection jdbc = new JDBCConnection();  
               // ArrayList<String> Countries = jdbc.getCountry();

             //   for (int i=0; i<207; i++) {
               //     String country = Countries.get(i);
                //    html = html + "<option value ='" + country + "'>" + country + "</option>";
                //}
            //html = html + "</select> </div>";
                        
html = html + """
    <div class = 'form-group'>
    <br>
                <label for='StartYear'> Choose a Starting Year: </label>
                <br>
                <select id='SYear' name = 'SYear'>
""";
        
        ArrayList<String> Years = jdbc.getYear();
        for (int i=1750; i<2014; i++) {
            html = html + "<option value ='" + i + "'>" + i + "</option>";
        }
    html = html + "</select> </div";
html = html + """
        <div class = 'form-group'>
               <br> <br> <label for='EndYear'> Enter a time frame in years: </label>
               <br>
               <input type='number' value='1' id='EYear' name='EYear' min='1' max='264'>
            """;
            
  
        html = html + "</select> </div>";


          html = html +"""
                
                <div class = 'form-group'>
                <p>What type of Region did you enter?: <br>
                <select id='Region' name = 'Region'>
                <option> City </option>
                <option> State </option>
                <option> Country </option>
                </select>
            </p>
                </div>

                
                <div class = 'form-group'>
                <p> Select Comparison: <br>
                <select id = 'Comparison' name = 'Comparison'>
                <option value = 'Temperature'> Temperature </option>
                <option value = 'Population'> Population (Country Only) </option>
                <option value = 'Both'> Both (Country Only) </option>
                </select>
                </p>
                </div>

                
                <div class = 'form-group'>
                <p> Select Calculation Method <br>
                <select style = 'border-color:CadetBlue' id = 'Calculation' name = 'Calculation'>
                <option value = 'Absolute'> Absolute change in values </option>
                <option value = 'Relative'> Relative change in values </option>
                </select>
                </p>
                </div>
                <div class = 'form-group'
                <label for='quantity'> Please enter your desired number of results:</label> <br> 
                <input type='number' value='1' id='numresults' name='numresults' min='1' max='207'>
                </div>
                
                <div class = 'form-group'>
                <br>
                <button type='submit' class='btn-primary'>Submit!</button>

                </div>

                </form>
                <p style='text-align:center'> -------------------------- </p>
                <h3 style= 'text-align:center'> How do I use the filters? </h3>
                <p style='color:#434a4a;font-weight:normal;text-align:center'> This page will show you the similarity between your selected region and other regions based on your input!</p> <p style='color:#434a4a;font-weight:normal;text-align:center'> You can choose the region (make sure it matches the region type!), starting year and time period, method to compare by, method to calculate change by, and number of results to return. Have fun! </p> <br>

                </td>
                <td style='vertical-align:top'>  
                """;
                        
                    
                String SYear = context.formParam("SYear");
                String EYear = context.formParam("EYear");
                String Region = context.formParam("Region");
                String Country = context.formParam("Country");
                String method = context.formParam("Calculation");
                String numresults = context.formParam("numresults");
                String Comparison = context.formParam("Comparison");
                if (!(EYear ==null)) {
                EYear = (Integer.valueOf(SYear) + Integer.valueOf(EYear) + "");
                if (Integer.valueOf(EYear) > 2014) {
                    EYear = "2013";
                }
                }

                if ((Country == null) || (SYear == null) || (Region == null)) {
                    html = html + "Select your options!";
                    //default display
                } else if(Integer.valueOf(SYear) > Integer.valueOf(EYear)) {
                    html = html + "Error: Start year cannot be after end year";
                    System.out.println(numresults);
                } else if (!(Comparison.equals("Temperature")) && (!(Region.equals("Country")) )) {
                    html = html + "Error: Sorting by population is only available for Country";
                } else if ((numresults == null)) {
                    html = html + "Number of results cannot be left blank";
                    //for text/num input box
                } else {
                    html = html + "<table style = <table style = 'background-color:snow; text-align:top' border='0' height='50%' width = '80%' bordercolor ='CadetBlue' align=center cellspacing='10'> <tr> <tr> <th>  </th> <th> </th> </tr> <td>";
                    if (Comparison.equals("Both")) {
                        html = html + bothString(Region, SYear, EYear, Country, method, numresults, Comparison);
                    } else {
                        html = html + resultString(Region, SYear, EYear, Country, method, numresults, Comparison);
                    }

                    html = html + "</td> <td style = width='100%'' valign = 'top'>";
                    html = html + "</td> </tr> </table>";


                }
             html = html + """

                """;
        html = html + "<p>";


        // Close Content div
        html = html + " </table> </div>";

        // Footer
        html = html + """
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
    public String resultString(String Region, String SYear, String EYear, String Country, String method, String numresults, String Comparison) {
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<STask3B> sData = jdbc.getTask3B(Region, SYear, EYear, Country, method, numresults, Comparison);
        int i = 0;
        if (sData.size() > 0) {
            if (Comparison.equals("Temperature")) {
                html = html + "<table style = 'background-color:#ebf2f2; text-align:top' border='3' height='50%' width = '80%' bordercolor ='CadetBlue' align=center cellspacing='10'> <tr style = 'background-color:#91bdbd'> <th> Ranking </th> <th> Region </th>  <th> Avg Temp (" + SYear + ") </th>  <th> Avg Temp (" + EYear + ") </th> <th> Avg Temp Change </th>   <th> Similarity (" + method + ") </th>" ;
            } else {
                html = html + "<table style = 'background-color:#ebf2f2; text-align:top' border='3' height='50%' width = '80%' bordercolor ='CadetBlue' align=center cellspacing='10'> <tr style = 'background-color:#91bdbd'> <th> Ranking </th> <th> Region </th>  <th> Population (" + SYear + ") </th>  <th> Population (" + EYear + ")</th> <th> Population Change </th>   <th> Similarity (" + method + ") </th>" ;
            }

            for (STask3B stemp: sData) {
                double Ranking = stemp.getRanking();
                stemp = sData.get(i); 
                if ((stemp.get3Eavg() > 0) && (stemp.get3SAvg() > 0)) {
                    double avgChange;
                    if (method.equals("Absolute")) {
                        avgChange = (stemp.get3Eavg() - stemp.get3SAvg());
                        avgChange = (double)Math.round(avgChange * 1000000d) / 1000000d;
                        STask3B scompare = sData.get(0);
                        Double Compare = (scompare.get3Eavg() - scompare.get3SAvg());
                        Compare = Math.abs(Compare);
                        Ranking = Math.abs(Compare - Math.abs(avgChange));
                        Ranking = (double)Math.round(Ranking * 1000000d) / 1000000d;
                        String S = String.valueOf(stemp.get3SAvg());
                        String E = String.valueOf(stemp.get3Eavg());
                        if (Comparison.equals("Population")) {
                            S = String.format("%.0f",stemp.get3SAvg());
                            E = String.format("%.0f", stemp.get3Eavg());
                        }
                        html = html + "</tr> <td>" + (i + 1) + "</td> <td>" + stemp.get3Region() +  "</td> <td>" + S +  "</td> <td>" +  E + "</td> <td>" + avgChange + "</td> <td>" + Ranking + "</td>";
                    } else {
                        //avgChange = (((stemp.get3Eavg() - stemp.get3SAvg()) / stemp.get3Eavg()) * 100);
                        //COMPARE AVGCHANGE
                        avgChange = (((stemp.get3Eavg() - stemp.get3SAvg()) / stemp.get3SAvg()) * 100);
                        double Compare;
                        STask3B scompare = sData.get(0);
                        Compare = (((scompare.get3Eavg() - scompare.get3SAvg()) / scompare.get3SAvg()) * 100);
                       //Ranking = Math.abs((((Math.abs(avgChange) - Compare) / Math.abs(avgChange)) * 100));
                       if (!Comparison.equals("Population")) {
                        Ranking = avgChange - Compare;
                       // Ranking = (double)Math.round(Ranking * 100d) / 100d;
                        //Ranking = Math.abs(1.00 - Ranking);
                        //Ranking = (double)Math.round(Ranking * 100d) / 100d;
                        //Ranking = Ranking * 100;
                        Ranking = Ranking * 100;
                        Ranking = Math.round(Ranking);
                        Ranking = Ranking / 100;
                        Ranking = 100 - Ranking;
                        if ((Ranking > 100.0) || (Ranking < 0)) {
                            Double tempv = Math.abs(100.0 - Ranking);
                            Ranking = 100.0 - tempv;
                            Ranking = (double)Math.round(Ranking * 100d) / 100d;
                            
                        }
                       } else {
                        Ranking = Ranking * 100;
                        Ranking = Math.round(Ranking);
                        Ranking = Ranking / 100;
                        Ranking = 100 - Ranking;
                       }

                        avgChange = (((stemp.get3Eavg() - stemp.get3SAvg()) / stemp.get3SAvg()) * 100);
                        avgChange = (double)Math.round(avgChange * 100d) / 100d;
                        String S = String.valueOf(stemp.get3SAvg());
                        String E = String.valueOf(stemp.get3Eavg());
                        if (Comparison.equals("Population")) {
                            S = String.format("%.0f",stemp.get3SAvg());
                            E = String.format("%.0f", stemp.get3Eavg());
                        }
                        html = html + "</tr> <td>" + (i + 1) + "</td> <td>" + stemp.get3Region() +  "</td> <td>" + S +  "</td> <td>" +  E + "</td> <td>" + avgChange + "% </td> <td>" + + Math.abs(Ranking) + "% </td>";

                    }
                    //avgChange = (stemp.get3Eavg() - stemp.get3SAvg());


                    
                    
                } else if (!(stemp.get3Eavg() > 0) && !(stemp.get3SAvg() > 0)) {
                    html = html + "</tr> <td>" + (i + 1) + "</td> <td>" + stemp.get3Region() + "</td> <td>"  + SYear + "</td> <td>"+ stemp.get3SAvg() + "</td> <td>" + EYear + "</td> <td>"  + stemp.get3Eavg() + "</td> <td>" + "N/A" +  "</td> <td>" + "N/A" + "</td>";
                } else {
                    //should only be accessed when the counter reaches its limit
                    html = html + "";
                }
                
                i++;
            }
            html = html + "</tr> </table>";
        } else {
            //error handling for blank return, prevents empty table
            html = html + "There is no data for your selection. Please try a different year range or region for your desired country.";
        }
        

        return html;
    }

    public String bothString(String Region, String SYear, String EYear, String Country, String method, String numresults, String Comparison) {
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<SBoth3B> sData = jdbc.getBoth(Region, SYear, EYear, Country, method, numresults, Comparison);
        int i = 0;
        if (sData.size() > 0) {

                html = html + "<table style = 'background-color:#ebf2f2; text-align:top' border='3' height='50%' width = '80%' bordercolor ='CadetBlue' align=center cellspacing='10'> <tr style = 'background-color:#91bdbd'> <th> Ranking </th> <th> Region </th>  <th> Avg Temp (" + SYear + ") </th>  <th> Avg Temp (" + EYear + ") </th> <th> Population (" + SYear + ") </th> <th> Population (" + EYear + ") </th> <th> Avg Temp Change </th> <th> Population Change </th>  <th> Similarity (" + method + ", Population) </th>" +  "<th> Similarity (" + method + ", Temperature) </th>" ;

            for (SBoth3B stemp: sData) {
                stemp = sData.get(i); 
                if (method.equals("Absolute")) {
                    //CHANGE & RANKING FOR CURRENT 
                    double tempchange = stemp.gettempavgChange();
                    double popchange = stemp.getpopavgChange();
                    double tempranking = stemp.gettempRanking();
                    double popranking = stemp.getpopRanking();
                    tempranking = tempranking * 100;
                    tempranking = Math.round(tempranking);
                    tempranking = tempranking / 100;
                    popranking = (double)Math.round((popranking * 1000d) / 1000d);
                    tempchange = tempchange * 100;
                    tempchange = Math.round(tempchange);
                    tempchange = tempchange / 100;
                    popchange = popchange * 100;
                    popchange = Math.round(popchange);
                    popchange = popchange / 100;

                    //RANKING TO COMPARE
                    String d = String.format("%.0f",stemp.getSpop());

                html = html + "</tr> <td>" + (i + 1) + "</td> <td>" + stemp.get3Region() + "</td> <td>" + stemp.get3SAvg() + "</td> <td>" + stemp.get3Eavg() + "</td> <td>" + d + "</td> <td>" + String.format("%.0f",stemp.getEpop()) + "</td> <td>" + tempchange + "</td> <td>" + popchange + "</td> <td>" + Math.abs(popranking) + "</td> <td>" + Math.abs(tempranking) + "</td>";
                } else {
                    //relative
                    //CHANGE & RANKING FOR CURRENT
                    double tempchange = stemp.gettempavgChange();
                    double popchange = stemp.getpopavgChange();
                    double tempranking = stemp.gettempRanking();
                    double popranking = stemp.getpopRanking();
                    tempranking = tempranking * 100;
                    tempranking = Math.round(tempranking);
                    tempranking = tempranking / 100;
                    tempranking = 100 - Math.abs(tempranking);
                    popranking = popranking * 100;
                    popranking = Math.round(popranking);
                    popranking = popranking / 100;
                    popranking = 100 - Math.abs(popranking);
                    tempchange = tempchange * 100;
                    tempchange = Math.round(tempchange);
                    tempchange = tempchange / 100;
                    popchange = popchange * 100;
                    popchange = Math.round(popchange);
                    popchange = popchange / 100;

                    //RANKING TO COMPARE

                    html = html + "</tr> <td>" + (i + 1) + "</td> <td>" + stemp.get3Region() + "</td> <td>" + stemp.get3SAvg() + "</td> <td>" + stemp.get3Eavg() + "</td> <td>" + String.format("%.0f",stemp.getSpop()) + "</td> <td>" + String.format("%.0f",stemp.getEpop()) + "</td> <td>" + tempchange + "% </td> <td>" + popchange + "% </td> <td>" + Math.abs(popranking) + "% </td> <td>" + Math.abs(tempranking) + "% </td>";
                }
                i++;
            }
            html = html + "</tr> </table>";
        } else {
            //error handling for blank return, prevents empty table
            html = html + "There is no data for your selection. Please try a different year range or region for your desired country.";
        }
        

        return html;
    }
}