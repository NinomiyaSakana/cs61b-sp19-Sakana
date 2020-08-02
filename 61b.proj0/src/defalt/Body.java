package defalt;

public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double g = 6.67E-11;
	
	
	public Body(double xP, double yP, double xV,double yV, double m, String img) {
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	
	public Body(Body b) {
		xxPos=b.xxPos;
		yyPos=b.yyPos;
		xxVel=b.xxVel;
		yyVel=b.yyVel;
		mass=b.mass;
		imgFileName=b.imgFileName;
	}
	
	public double calcDistance(Body b) {
		double dx = b.xxPos - xxPos;
		double dy = b.yyPos - yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public double calcForceExertedBy(Body b) {
		double r=calcDistance(b);
		return g*b.mass*mass/(r*r);
	}
	
	public double calcForceExertedByX(Body b) {
		double f = calcForceExertedBy(b);
		double dx = b.xxPos - xxPos;
		double r = calcDistance(b);
		return f * dx / r;
	}

	public double calcForceExertedByY(Body b) {
		double f = calcForceExertedBy(b);
		double dy = b.yyPos - yyPos;
		double r = calcDistance(b);
		return f * dy / r;
	}
	
	public double calcNetForceExertedByX(Body[] bodies) {
		double fx=0;
		for(Body b:bodies) {
			if(!this.equals(b)) { //这里要用this代替这个b
				fx+=calcForceExertedByX(b); //如果this不等于b，则返回与b的距离 this应该指的是a.distance中的a
			}
		}
		return fx;
	}
	
	public double calcNetForceExertedByY(Body[] bodies) {
		double fy=0;
		for(Body b:bodies) {
			if(!this.equals(b)) { //这里要用this代替这个b
				fy+=calcForceExertedByY(b); //如果this不等于b，则返回与b的距离 this应该指的是a.distance中的a
			}
		}
		return fy;
	}
	
	public void update(double dt, double fX, double fY) {
		double ax=fX/mass;
		double ay=fY/mass;
		xxVel+=ax*dt;
		yyVel+=ay*dt;
		xxPos+=xxVel*dt;
		yyPos=yyVel*dt;
	}
	
	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}

	

}
