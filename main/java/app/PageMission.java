package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageMission implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/mission.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Our Mission</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";
        html = html + """
            <style>

            h2 {
                font-family: trebuchet ms, sans-serif;
                font-weight:bold;
            }
            h3 {
                font-family: trebuchet ms, sans-serif;
                font-weight:bold;
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
                <a href='mission.html' style='background-color:#97b1c9; border-radius:8px;'>Our Mission</a>
                <a href='page2A.html' style='background-color:#728ca3; border-radius:8px;'>Country / Global Change</a>
                <a href='page2B.html' style='background-color:#728ca3; border-radius:8px;'>State / City Change</a>
                <a href='page3A.html' style='background-color:#728ca3; border-radius:8px;'>Advanced Temp Change</a>
                <a href='page3B.html' style='background-color:#728ca3; border-radius:8px;'>Sort by Similarity</a>
            </div>
        """;

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>Mission Statement</h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
            //labels

        html = html + """
                <table style = "background-color:#d8e8e8; text-align:top;" border="3" height="50%" width="95%"
                bordercolor="CadetBlue" align=center cellspacing ="10">
                <tr>
                    <th style="font-size:25px;width:30%;"> Climate Change: The Social Challenge </th>
                    <th style="font-size:25px;width:30%;"> How to Use the Website </th>
                </tr>
                <tr style="text-align:top;">
                    <td style="vertical-align:top;"> <p> Climate Change is one of the most crucial challenges humanity is facing in our modern era. Rising global temperatures lead to an increased risk of natural disasters, irreversible damage to our environment, disruptions to agriculture and wildlife, and so much more.  </p>

                    <p> We believe every person should have access to the facts about climate change. That's why we've made sure our website is accessible to everyone - because you deserve to have the facts about your future. If we don't reverse these changes soon, the very possibility of humanity's future will be jeopardised. </p>  
                    
                    <p> The data we provide can be filtered for analysis, comparison, and understanding as required to meet the needs of our diverse user base who all share one goal - to learn more about our changing world through facts and information. </p>
                    
                    <p> So, together, let's look towards a future where everybody is informed about these topics - understanding the issue is the first step towards a brighter future for us all. </p>
                    </td>
                   
                    <td style ="vertical-align:top;"> <p> We've divided our data across several pages, each of which have different functions and suit people with different skill levels. We want all our users to be able to truly understand how to access and interpret our data, so each page includes some tips & tricks to help you get the most out of our website. </p>

                    <p> Our first two options, 'Contry/Global Change' and 'City/State' change feature a simple interface, with just a few user inputs between you and the data you want to see. These pages are meant to be easily understood by everyone - we believe that data such as average temperature change of your city, or the population growth of your country, are absolutely vital for everybody to know. Knowledge is power! </p>
                    
                    <p> We have also included some more advanced options, for those of you who have a bit more experience or a boundless curiosity to learn more about the in-depth issues. On 'Advanced Temp Change' you can access far more complex temperature change data, while 'Sort by Similarity' allows you to compare similar regions by temperature or population. These pages require some more input and understanding from users - but the information they provide is priceless for increasing your awareness of climate issues.  </p> </td>
                    </tr> </table>
                    <table style = "background-color:#d8e8e8; text-align:top;"border = "3" height = "50%" width = "95%"
                    bordercolor="CadetBlue" align=center cellspacing ="10">
                    <tr> <th style="font-size:25px;width:40%;"> Who We are & What We're For </th> </tr>
                    <tr>
                    <td> <h2 style='text-align:center'> Creators </h2>
                    <h3 style = 'text-align:center'> This project was created by: </h3>
                    """;  
            //data goes here

        // This example uses JDBC to lookup the LGAs
        JDBCConnection jdbc = new JDBCConnection();

        ArrayList<Student> students = jdbc.getStudentInfo();
        for (Student student: students) {
        html = html + "<li style='text-align:center'>" + student.getID()
            + " - " + student.getFirstName() + "  " + student.getLastName() + "</li>";
    }


    html = html + "<h2 style='text-align:center'> Personas </h2>";
    
    ArrayList<Persona> personas = jdbc.getPersonaInfo();
    for (Persona persona: personas) {
        html = html + "<div class='content' style='background-color:#ebf2f2;margin-left:145px;margin-right:145px;border: 5px solid Cadetblue;border-radius: 10px;'>";
        html = html + "<h3 style='text-align:center;color:#599690'>" + ".................... " + persona.getPID() + " - " + persona.getName() + " .................... <br> <br>"; 
        html = html + " <img style='border:4px solid Cadetblue;border-radius:5px' src='" + persona.getImgURL() + "' alt = 'PersonaImage' height='120'> </h3>";
        html = html + " <h4 style ='text-align:center;color:#599690'> Description </h4>  <p style='margin-left:105px;margin-right:105px;text-align:center'>" + persona.getDescription() + "</p>" +
        " <h4 style ='text-align:center;color:#599690'> Needs & Goals </h4> <p style='margin-left:105px;margin-right:105px;text-align:center'> " + persona.getNeeds() + "</p>" +
        " <h4 style ='text-align:center;color:#599690'> Skills </h4> <p style='margin-left:140px;margin-right:105px;text-align:center'> " + persona.getSkills() + "</p>" +
        " <h4 style ='text-align:center;color:#599690'> Pain Points </h4>  <p style='margin-left:105px;margin-right:105px;text-align:center'>" + persona.getPainPoints() + "</p>";
        html = html + " </div> <br>";
        html = html + "<div class='test'> <h5> </h5> </div>";
    
    }


        html = html + """ 
            </ul>
          </td>
        </tr>
        </table>
        """;
        // Next we will ask this *class* for the LGAs
        //ArrayList<LGA> lgas = jdbc.getLGAs2016();

        // Add HTML for the LGA list
        //html = html + "<h1>All 2016 LGAs in the CTG database (using JDBC Connection)</h1>" + "<ul>";

        // Finally we can print out all of the LGAs
       //for (LGA lga : lgas) {
        //    html = html + "<li>" + lga.getCode()
       //                 + " - " + lga.getName() + "</li>";
       // }

        // Finish the List HTML
        //html = html + "</ul>";


        // Close Content div
        html = html + "</div>";

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

}
