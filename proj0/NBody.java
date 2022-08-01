public class NBody{
    public static double readRadius(String name){
        In in = new In(name);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String name){
        In in = new In(name);
        int num = in.readInt();
        Planet[] ps = new Planet[num];
        double radius = in.readDouble();
        for (int i = 0; i < num; i++){
            ps[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return ps;
    }

    public static void main(String[] args){
        //read the file and commands
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);
        int l = planets.length;
        //begin to draw
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        double t = 0;
        while(t <= T){
            StdDraw.clear();
            double[] xForces = new double[l];
            double[] yForces = new double[l];
            for(int i = 0; i <l; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < l; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(Planet s : planets){
                s.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        //printout
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}
/*to run this program,use: 
javac NBody.java
java NBody 157788000.0 25000.0 data/planets.txt*/
