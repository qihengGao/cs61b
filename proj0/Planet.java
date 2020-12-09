import java.math.*;

/** Planet class */
public class Planet {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  /** Constructor */
  public Planet(double xP, double yP, double xV,
               double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  public double calcDistance(Planet p) {
    double diffX = xxPos - p.xxPos;
    double diffY = yyPos - p.yyPos;
    double dist = Math.sqrt(diffX * diffX + diffY * diffY);
    return dist;
  }

  public double calcForceExertedBy(Planet p) {
    double r = calcDistance(p);
    double F =  6.67e-11 * mass * p.mass / (r * r);
    return F;
  }

  public double calcForceExertedByX(Planet p){
    double F = calcForceExertedBy(p);
    double r  = calcDistance(p);
    double diffX = p.xxPos - xxPos;
    double fX = F * diffX / r;
    return fX;
  }

  public double calcForceExertedByY(Planet p){
    double F = calcForceExertedBy(p);
    double r  = calcDistance(p);
    double diffY = p.yyPos - yyPos;
    double fY = F * diffY / r;
    return fY;
  }

  private Boolean equals(Planet p) {
    if (xxPos == p.xxPos && yyPos == p.yyPos && xxVel == p.xxVel &&
        yyVel == p.yyVel && mass == p.mass && imgFileName == p.imgFileName) {
              return true;
    }
    return false;
  }

  public double calcNetForceExertedByX(Planet[] ps) {
    double output = 0.0;
    for (Planet p : ps) {
      if (this.equals(p)) {
        continue;
      }
      output += this.calcForceExertedByX(p);
    }
    return output;
  }

  public double calcNetForceExertedByY(Planet[] ps) {
    double output = 0.0;
    for (Planet p : ps) {
      if (this.equals(p)) {
        continue;
      }
      output += this.calcForceExertedByY(p);
    }
    return output;
  }

  public void update(double dt, double fX, double fY){
    double aX = fX / this.mass;
    double aY = fY / this.mass;
    this.xxVel += aX * dt;
    this.yyVel += aY * dt;
    this.xxPos += this.xxVel * dt;
    this.yyPos += this.yyVel * dt;
  }

  public void draw() {
    String imgPath = "./images/" + this.imgFileName;
    StdDraw.picture(xxPos, yyPos, imgPath);
  }
}
