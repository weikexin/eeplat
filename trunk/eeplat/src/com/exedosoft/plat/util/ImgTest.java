package com.exedosoft.plat.util;

import javax.imageio.ImageIO;
import javax.imageio.IIOException;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;

public class ImgTest {
	
	
	
	 public static BufferedImage rotateImg( BufferedImage image, int degree, Color bgcolor ){
		 
		  int iw = image.getWidth();//原始图象的宽度 
		  int ih = image.getHeight();//原始图象的高度  
		  int w=0;
		  int h=0; 
		  int x=0; 
		  int y=0; 
		  degree=degree%360;
		  if(degree<0)degree=360+degree;//将角度转换到0-360度之间
		  double ang=degree* 0.0174532925;//将角度转为弧度
		  
		  /**
		   *确定旋转后的图象的高度和宽度
		   */
		   
		  if(degree == 180|| degree == 0 || degree == 360){
		   w = iw; 
		   h = ih; 
		  }else if(degree == 90|| degree == 270){ 
		   w = ih; 
		   h = iw;  
		  }else{  
		   int d=iw+ih;  
		   w=(int)(d*Math.abs(Math.cos(ang)));
		   h=(int)(d*Math.abs(Math.sin(ang)));
		  }
		  
		  x = (w/2)-(iw/2);//确定原点坐标
		  y = (h/2)-(ih/2); 
		  BufferedImage rotatedImage=new BufferedImage(w,h,image.getType()); 
		  Graphics gs=rotatedImage.getGraphics();
		  gs.setColor(bgcolor);
		  gs.fillRect(0,0,w,h);//以给定颜色绘制旋转后图片的背景
		  AffineTransform at=new AffineTransform();
		  at.rotate(ang,w/2,h/2);//旋转图象
		  at.translate(x,y); 
		  AffineTransformOp op=new AffineTransformOp(at,AffineTransformOp.TYPE_NEAREST_NEIGHBOR); 
		  op.filter(image, rotatedImage); 
		  image=rotatedImage;
		  return image;
		 }


	public static void main(String argv[]) throws IOException {
		
		File fi = new File("c:/zhang.jpg"); // 大图文件
		BufferedImage bis = ImageIO.read(fi);
		ImgTest.rotateImg(bis,30,Color.white);

		
		
		
		
		
		
		
		
		
		
//		try {
//			File fi = new File("c:/zhang.jpg"); // 大图文件
//			File fo = new File("c:/imgTest.jpg"); // 将要转换出的小图文件
//			int nw = 100;
//			AffineTransform transform = new AffineTransform();
//			BufferedImage bis = ImageIO.read(fi);
//			AffineTransformOp ato = new AffineTransformOp(transform, null);
//			
//			AffineTransform at=new AffineTransform();
//			  at.rotate(ang,w/2,h/2);//旋转图象
//			  at.translate(x,y); 
//			  AffineTransformOp op=new AffineTransformOp(at,AffineTransformOp.TYPE_NEAREST_NEIGHBOR); 
//			  op.filter(image, rotatedImage); 
//			  image=rotatedImage;
//			  return image;
//
//			
//			
//			BufferedImage bid = new BufferedImage(nw, nh,
//					BufferedImage.TYPE_3BYTE_BGR);
//			ato.filter(bis, bid);
//			ImageIO.write(bid, "jpeg", fo);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
