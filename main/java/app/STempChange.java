package app;

public class STempChange {
    //city or state
    public String region;

    //start year
    public double SAvg;
    public double SMin;
    public double SMax;
    public double Eavg;
    public double Emin;
    public double Emax;



    public STempChange() {

    }

    public STempChange(String region, double SAvg, double SMin, double SMax, double Eavg, double Emin, double Emax) {
        this.region = region;
        this.SAvg = SAvg;
        this.SMin = SMin;
        this.SMax = SMax;
        this.Eavg = Eavg;
        this.Emin = Emin;
        this.Emax = Emax;

    }
    public double getSMin() {
        return SMin;
    }
    public double getSMax() {
        return SMax;
    }
    public double getSAvg() {
        return SAvg;
    }

    public double getEavg() {
        return Eavg;
    }

    public double getEmin() {
        return Emin;
    }

    public double getEmax() {
        return Emax;
    }

    public String getRegion() {
        return region;
    }
}
