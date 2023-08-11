package app;

public class TempPopSortData {
    public int Population;
    public double AvgTemp;
    public int Year;


public TempPopSortData(){

}

public TempPopSortData(int Population,double AvgTemp,int Year){
    this.Population = Population;
    this.AvgTemp = AvgTemp;
    this.Year = Year;
}

public int getPopulation(){
    return Population;
}
public double getAvgTemp(){
    return AvgTemp;
}

public int getYear(){
    return Year;
}

}
