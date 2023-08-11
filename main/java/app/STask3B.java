package app;

public class STask3B {
    //city or state
    public String region;

    //start year
    public double SAvg;
    public double Eavg;
    public double avgChange;
    public double Ranking;
  // public String State;
  //  public double SSavg;
  //  public double SEavg;



    public STask3B() {

    }

    public STask3B(String region, double SAvg, double Eavg, double avgChange, double Ranking) {
        this.region = region;
        this.SAvg = SAvg;
        this.Eavg = Eavg;
        this.Ranking = Ranking;
        this.avgChange = avgChange;
       // this.State = State;
       // this.SSavg = SSavg;
       // this.SEavg = SEavg;
    }

    public double get3SAvg() {
        return SAvg;
    }

    public double get3Eavg() {
        return Eavg;
    }



    public String get3Region() {
        return region;
    }

    public double getavgChange() {
        return avgChange;
    }

    public double getRanking() {
        return Ranking;
    }

   // public double getSSavg() {
 //       return SSavg;
 //   }

 //   public double getSEavg() {
 //       return SEavg;
 //   }

//    public String getState() {
//        return State;
  //  }
}