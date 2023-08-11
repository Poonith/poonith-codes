package app;

public class SBoth3B {
    //city or state
    public String region;

    //start year
    public double SAvg;
    public double Eavg;
    public double Spop;
    public double Epop;
    public double tempavgChange;
    public double tempRanking;
    public double popavgChange;
    public double popRanking;
  // public String State;
  //  public double SSavg;
  //  public double SEavg;



    public SBoth3B() {

    }

    public SBoth3B(String region, double SAvg, double Eavg, double Spop, double Epop, double tempavgChange, double tempRanking, double popavgChange, double popRanking) {
        this.region = region;
        this.SAvg = SAvg;
        this.Eavg = Eavg;
        this.Spop = Spop;
        this.Epop = Epop;
        this.tempavgChange = tempavgChange;
        this.tempRanking = tempRanking;
        this.popavgChange = popavgChange;
        this.popRanking = popRanking;
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
    public double getSpop() {
        return Spop;
    }
    public double getEpop() {
        return Epop;
    }
    public double gettempavgChange() {
        return tempavgChange;
    }
    public double gettempRanking() {
        return tempRanking;
    }
    public double getpopavgChange() {
        return popavgChange;
    }
    public double getpopRanking() {
        return popRanking;
    }

}
