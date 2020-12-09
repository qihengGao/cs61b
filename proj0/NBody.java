public class NBody {

  public static double readRadius(String filePath) {
    In in = new In(filePath);
    int N = in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  public static Planet[] readPlanets(String filePath) {
    In in = new In(filePath);
    int N = in.readInt();
    Planet[] planets = new Planet[N];
    double radius = in.readDouble();
    for (int i = 0; i < N; i++) {
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String imgFileName = in.readString();
      planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
    }
    return planets;
  }

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String fileName = args[2];
    double radius = readRadius(fileName);
    Planet[] planets = readPlanets(fileName);
    String starfieldPath = "./images/starfield.jpg";
    StdDraw.enableDoubleBuffering();
    StdDraw.setScale(-radius, radius);
    StdDraw.clear();
    StdDraw.picture(0, 0, starfieldPath, 2 * radius, 2 * radius);
    for (Planet p : planets) {
      p.draw();
    }
    StdDraw.show();
		StdDraw.pause(10);
    for (double t = 0.0; t <= T; t += dt) {
      double[] xForses = new double[planets.length];
      double[] yForses = new double[planets.length];
      int i = 0;
      for (Planet p : planets) {
        xForses[i] = p.calcNetForceExertedByX(planets);
        yForses[i] = p.calcNetForceExertedByY(planets);
        i++;
      }
      i = 0;
      StdDraw.clear();
      StdDraw.picture(0, 0, starfieldPath, 2 * radius, 2 * radius);
      for (Planet p : planets) {
        p.update(dt, xForses[i], yForses[i]);
        p.draw();
        i++;
      }
      StdDraw.show();
  		StdDraw.pause(10);
    }
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                      planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                      planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }
}
