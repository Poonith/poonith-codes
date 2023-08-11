package app;

public class STask3A {
    
    public String region;
    public double AvgTemp;
  



    public STask3A() {

    }

    public STask3A(String region, double AvgTemp) {
        this.region = region;
        this.AvgTemp= AvgTemp;
   
      


    }

    public double getAvgTemperature() {
        return AvgTemp;
    }



   

    public String getRegionlvl() {
        return region;
    }
}
