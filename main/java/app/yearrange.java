package app;

public class yearrange {
    private int Year;
    private String Population;
    private double AvgTemp;

public yearrange(int Year,String Population,double AvgTemp){
this.Year = Year;
this.Population = Population;
this.AvgTemp = AvgTemp;
}
public int getYear(){
    return Year;
}

public String getPopulation(){
    return Population;
}

public double getAvgTemp(){
    return AvgTemp;
}
}