package squarify;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Squarify {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("insert image path");
		String path = sc.nextLine();
		sc.close();
		File file = new File(path);
		BufferedImage img = ImageIO.read(file);
		
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage img_new = null;
		
		if(width>height){
			img_new = new BufferedImage(width, width, img.getType());
			int dim = width - height;
			int i=0;
			for (; i<dim/2;i++){
				for(int j=0; j<width; j++){
					img_new.setRGB(j,i,img.getRGB(randInt(0,width-1),0));
				}
			}
			for (int i2=0; i2<height;i++,i2++){
				for(int j=0,j2=0; j2<width; j++,j2++){
					img_new.setRGB(j, i, img.getRGB(j2, i2));
				}
			}
			for(;i<width;i++){
				for(int j=0;j<width;j++){
					img_new.setRGB(j, i, img.getRGB(randInt(0,width-1), height-1));
				}
			}
		}
		
		else if(height>width){
			img_new = new BufferedImage(height, height, img.getType());
			int dim = height - width;
			int j=0;
			for(;j<dim/2;j++){
				for(int i=0;i<height;i++){
					img_new.setRGB(j, i, img.getRGB(0, randInt(0,height-1)));
				}
			}
			for (int j2=0; j2<width;j++,j2++){
				for(int i=0,i2=0; i2<height; i++,i2++){
					img_new.setRGB(j, i, img.getRGB(j2, i2));
				}
			}
			for(;j<height;j++){
				for(int i=0;i<height;i++){
					img_new.setRGB(j, i, img.getRGB(width-1,randInt(0,height-1)));
				}
			}
		}
		File outputfile = new File(path+"_squared.jpg");
		ImageIO.write(img_new, "jpg", outputfile);
	}
		
	public static int randInt(int min, int max) {

	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}

}
