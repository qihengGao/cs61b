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
    return Math.sqrt(Math.pow(this.xxPos - p.xxPos, 2)
                    + Math.pow(this.xxPos - p.yyPos, 2));
  }

  public double calcForceExertedBy(Planet p) {
    return (6.67E-11 * this.mass * p.mass) / Math.pow(this.calcDistance(p), 2);
  }

  public double calcForceExertedByX(Planet p){
    return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos)
          / this.calcDistance(p);
  }

  public double calcForceExertedByY(Planet p){
    return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos)
          / this.calcDistance(p);
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
