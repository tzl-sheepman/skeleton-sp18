public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
                xxPos = xP;
                yyPos = yP;
                xxVel = xV;
                yyVel = yV;
                mass = m;
                imgFileName = img;
              }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double r = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return r;
    }

    public double calcForceExertedBy(Planet p){
        double f = G * this.mass * p.mass / Math.pow(this.calcDistance(p), 2);
        return f;
    }
    
    public double calcForceExertedByX(Planet p){
        double dx = p.xxPos - this.xxPos;
        return this.calcForceExertedBy(p) * dx / this.calcDistance(p);
    }
    
    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos - this.yyPos;
        return this.calcForceExertedBy(p) * dy / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] p){
        double sum = 0;
        for (Planet s : p){
            if (this.equals(s)){
                continue;
            }else{
                sum += this.calcForceExertedByX(s);
            }
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] p){
        double sum = 0;
        for (Planet s : p){
            if (this.equals(s)){
                continue;
            }else{
                sum += this.calcForceExertedByY(s);
            }
        }
        return sum;
    }

    public void update(double dt, double fX, double fY){
        double ax = fX/this.mass;
        double ay = fY/this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
