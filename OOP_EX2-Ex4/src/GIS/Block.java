package GIS;

import Geom.Point3D;

public class Block  {
	int id;
	double radius;
	public Point3D start,end,point0,point1;
	public Block(Point3D start,Point3D end,int id,double radius) {
		this.start = start;
		this.end = end;
		this.id = id;
		this.radius = radius;
		this.point1=new Point3D (start.x(),end.y());
		this.point0=new Point3D (end.x(),start.y());
	}
	
	public int[] setDimension(Map map) {
		
		int pixelStart[]=new int[2];
		int pixelEnd[]=new int[2];
		pixelStart=map.gpsToPixel(start.x(),start.y());
		pixelEnd=map.gpsToPixel(end.x(),end.y());
		Range rangeX= new Range(pixelStart[0], pixelEnd[0]);
		Range rangeY= new Range(pixelStart[1], pixelEnd[1]);
		int Dimension[]= {pixelStart[0],pixelEnd[1],(int)(Math.abs(rangeX.dx())),(int)Math.abs(rangeY.dx())};
		return Dimension;
	}
}

