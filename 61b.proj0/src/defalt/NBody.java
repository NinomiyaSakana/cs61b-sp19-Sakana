package defalt;

import examples.In;
import examples.StdDraw;

public class NBody {
	
	public static double readRadius(String filename) {
		
		In in = new In(filename);
		int N = in.readInt();
		double R=in.readDouble();
		
		return R;
	}
	
	public static Body[] readBodies(String filename) {
		
		In in = new In(filename);
		int N = in.readInt(); //这里N是planet的个数
		double R=in.readDouble();
		
		Body[] bodies=new Body[N]; 
		//就是创建一个名为了bodies的array 每个元素是bodies[i],然后每个bodies都是Body类型 
		//就跟int[] bodies=new int[] 是一个原理
		
		for(int i=0;i<N;i++) {
			double xxPos = in.readDouble();  //就是轮流填充数组中的元素罢了
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            bodies[i]=new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return bodies;
	}
	
	
	
	public static void main(String[] args) {
		
		//Store the 0th and 1st command line arguments as doubles named T and dt.
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename=args[2];
		
		
		//filename从本作业前面的方法中描述的文件中读取物体和宇宙半径
		double radius = readRadius(filename);
		Body[] bodies = readBodies(filename); 
		
		//bodies的长度是行星的个数
		int N = bodies.length;
		
		StdDraw.setScale(-radius, radius); //设置宇宙边界
		StdDraw.clear();
		StdDraw.picture(0,0,"images/starfield.jpg");
		
		
		//Be sure to do this after drawing the starfield.jpg file so that the planets don’t get covered up by the background.
		for(Body b:bodies) {
			b.draw();
		}
		
		StdDraw.enableDoubleBuffering(); //防止图形闪烁，直接调用
		
		double time=0;
		while(time<T) {
			//Create an xForces array and yForces array.
			double[] xForces=new double[N];
			double[] yForces=new double[N];
			
			for (int i = 0; i < N; i++) {
				xForces[i]=bodies[i].calcNetForceExertedByX(bodies);
				yForces[i]=bodies[i].calcNetForceExertedByY(bodies);
			}
			
			//update each body’s position, velocity, and acceleration
			for(int i=0;i<N;i++){
				bodies[i].update(dt, xForces[i], yForces[i]);
			}
			
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Body b: bodies) {
				b.draw();
			}
			
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
			}
		
		
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
		                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}
		
		
		
		
	}
	
	
	
	

}
