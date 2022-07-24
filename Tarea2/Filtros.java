

import java.awt.Color;
import java.util.Scanner;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Filtros {

	/**
	 * Method that loads an image
	 * @param route -- Route of the image
	 * @return the image in a BufferedImage
	 * @throws IOException
	 */
	public BufferedImage readImage(String route) throws IOException{
		InputStream input = new FileInputStream(route);
		ImageInputStream imageInput = ImageIO.createImageInputStream(input);
		return ImageIO.read(imageInput);
	}
	/**
	 * Method that saves an image in png file
	 * @param image 
	 * @param name  -- name of the file
	 * @throws IOException
	 */
	public void saveImage(BufferedImage image, String name) throws IOException{
		ImageIO.write(image, "png", new File(name));
	}
	/**
	 * Method that copies an image from {@link BufferedImage} with the specified image type, where
	 * the graphical content is a copy of the specified image
	 * @param img -- the image to copy
	 * @param imageType
	 * @return A copy of the image
	 */
	public static BufferedImage copy(BufferedImage img, int imageType){
		int width = img.getWidth();
		int height = img.getHeight();

		BufferedImage newImage = new BufferedImage(width, height, imageType);
		Graphics graphic = newImage.createGraphics();
		
		graphic.drawImage(img, 0, 0, null);
		graphic.dispose();

		return newImage;
		
		
	}

	public void blur(BufferedImage image, BufferedImage copy){
		int widthCopy = copy.getWidth();
		int heightCopy = copy.getHeight();

		double blur[][] = {
            {0,0.2,0},
            {.2,.2,.2},
            {0,0.2,0}
        };

		for(int i=0; i<widthCopy; i++ ){
			for(int j=0; j <heightCopy; j++){
				double red = 0;
				double green = 0;
				double blue = 0;

				//Pixel 1 (-1,1)
				int pixel = image.getRGB(((i-1)%widthCopy<0)?widthCopy-1:i-1,((j-1)%heightCopy<0)?heightCopy-1:j-1);
			}
		}
	}
public static void main(String[] args) throws IOException
{
//	Initialize scanner
	Scanner input = new Scanner(System.in);
//	New utility
	ImageUtils utils = new ImageUtils();
	
//	asking user for image location input
	String filePath = "";
	
	// filePath = input.nextLine();
	// filePath = "pokemon2.jpeg";
	// filePath = "ronnie.png";
	filePath = "flor.jpeg";
	
//	Load Image
	Color[][] orig = utils.loadImage(filePath);
	
//	Display original
	utils.addImage(orig, "Imagen Original");
	
//	Ask user if they want to perform custom color function

// System.out.println("Do you wish to perform the custom color funciton? (yes or no)");

// String customChoice = input.next();
String customChoice = "no";

//   If they want to replace a color with a custom color.
if(customChoice.equalsIgnoreCase("yes")) {
	
//	Replace Color function method
	Color[][] customColor = colorReplacer(orig);
	
//	custom color replacement picture to the gallery
	utils.addImage(customColor, "Custom Color Replacement");
}

//  Filtro Gris Promedio

Color [][] grisPromedio = grisPromedio(orig);

//	Filtro Mosaico

Color [][] mosaico = mosaico(orig);

//	Filtro Blur

Color [][] blur = Blur(orig);

//	Filtro Motion Blur

Color [][] motionBlur = motionBlur(orig);

//	Filtro Find Edges

Color [][] findEdges = edges(orig);

//	Filtro Find Edges Horizontal

Color [][] edgesHorizontal = edgesHorizontal(orig);

//	Filtro Sharpen

Color [][] sharpen = sharpen(orig);

//	Filtro Sharpen2

Color [][] sharpen2 = sharpen2(ImageIO.read(new File(filePath)));


//	Filtro Edges2

Color [][] edges2Horizontal = edges2Horizontal(ImageIO.read(new File(filePath)));

//	Filtro Edges2

Color [][] edges2Vertical = edges2Vertical(ImageIO.read(new File(filePath)));

// ImageIO.read(new File(filepath));

//	Filtro Emboss

Color [][] emboss = emboss(orig);

//	Filtro Emboss

Color [][] emboss2 = emboss2(ImageIO.read(new File(filePath)));

//	Filtro Mean

Color [][] mean = mean(orig);

//	Filtro Median

Color [][] median = median(orig);

//	Filtro Componentes RGB

Color [][] filtroComponentesRGB = filtroComponentesRGB(orig);
	
	
	// utils.addImage(grisPromedio, "Gray 1");
	utils.addImage(mosaico, "Mosaic");
	utils.addImage(blur, "Blurred");
	utils.addImage(motionBlur, "Motion Blur");
	utils.addImage(findEdges, "Find Edges");
	utils.addImage(edgesHorizontal, "Edges Horizontal");
	utils.addImage(edges2Horizontal, "Edges 2 Horizontal");
	utils.addImage(edges2Vertical, "Edges 2 Vertical");
	utils.addImage(sharpen, "Sharpen");
	utils.addImage(sharpen2, "Sharpen 2");
	utils.addImage(emboss, "Emboss");
	utils.addImage(emboss2, "Emboss 2");
	utils.addImage(mean, "Mean");
	utils.addImage(median, "Median");

	utils.addImage(filtroComponentesRGB, "Filtro RGB");

//	Display images
	utils.display();
}






// ──────────────────────────────────────────────────────────────────────────────────────────────────────────
// ─██████████████─██████████─██████─────────██████████████─████████████████───██████████████─██████████████─
// ─██░░░░░░░░░░██─██░░░░░░██─██░░██─────────██░░░░░░░░░░██─██░░░░░░░░░░░░██───██░░░░░░░░░░██─██░░░░░░░░░░██─
// ─██░░██████████─████░░████─██░░██─────────██████░░██████─██░░████████░░██───██░░██████░░██─██░░██████████─
// ─██░░██───────────██░░██───██░░██─────────────██░░██─────██░░██────██░░██───██░░██──██░░██─██░░██─────────
// ─██░░██████████───██░░██───██░░██─────────────██░░██─────██░░████████░░██───██░░██──██░░██─██░░██████████─
// ─██░░░░░░░░░░██───██░░██───██░░██─────────────██░░██─────██░░░░░░░░░░░░██───██░░██──██░░██─██░░░░░░░░░░██─
// ─██░░██████████───██░░██───██░░██─────────────██░░██─────██░░██████░░████───██░░██──██░░██─██████████░░██─
// ─██░░██───────────██░░██───██░░██─────────────██░░██─────██░░██──██░░██─────██░░██──██░░██─────────██░░██─
// ─██░░██─────────████░░████─██░░██████████─────██░░██─────██░░██──██░░██████─██░░██████░░██─██████████░░██─
// ─██░░██─────────██░░░░░░██─██░░░░░░░░░░██─────██░░██─────██░░██──██░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─
// ─██████─────────██████████─██████████████─────██████─────██████──██████████─██████████████─██████████████─
// ──────────────────────────────────────────────────────────────────────────────────────────────────────────





public static Color[][]grisPromedio(Color[][] img) {
//	Clone matrix to not replace original
	Color[][] tmp = ImageUtils.cloneArray(img);
	
//	Runs through entire matrix
	for( int i = 0; i < tmp.length; i++)
		{
			for( int j = 0; j< tmp[i].length;j++) {
				
					
//					fetches values of each pixel
					Color pixel = tmp[i][j];
					int r = pixel.getRed();
					int g = pixel.getGreen();
					int b = pixel.getBlue();
					
					int rNew = (r+g+b)/3;
					int gNew = (r+g+b)/3;
					int bNew = (r+g+b)/3;
					
					tmp[i][j] = new Color (rNew, gNew, bNew);
					
				
			}
		}
	return tmp;
}

	public static Color[][] mosaico(Color[][] img) {
		//Clone matrix to not replace original
		Color[][] tmp = ImageUtils.cloneArray(img);
		int x;
		int y;
		x=15;
		y=15;
		
		//Runs through entire matrix
		for(int k=0; k<tmp.length; k=k+x){
			for(int l=0; l<tmp[k].length; l=l+y){
				
				int totalR=0;
				int totalG=0;
				int totalB=0;
				int superficie=x*y;
				for(int i = k; i < k+x; i++){
						for( int j = l; j< l+y;j++) {
							if(i<tmp.length && j<tmp[i].length)	{	
								Color pixel = tmp[i][j];
								totalR += pixel.getRed();
								totalG += pixel.getGreen();
								totalB += pixel.getBlue();	
							}	
						}
					}
				for( int i = k; i < k+x; i++){
					for( int j = l; j< l+y;j++) {
						if(i<tmp.length && j<tmp[i].length)	{
							tmp[i][j] = new Color (totalR/superficie, totalG/superficie, totalB/superficie);
						}					
					}
				}
			}
		}
		return tmp;
		}

public static Color[][]Blur(Color[][] img) {
	//	Clone matrix to not replace original
		Color[][] tmp = ImageUtils.cloneArray(img);

		double blur[][] = {
            {0,0.2,0},
            {.2,.2,.2},
            {0,0.2,0}
        };
		
	//	Runs through entire matrix
		for( int i = 1; i < tmp.length-5; i++){
				for( int j = 1; j< tmp[i].length-5;j++) {


					
						Color pixel = tmp[i][j];
						int r = pixel.getRed();
						int g = pixel.getGreen();
						int b = pixel.getBlue();
						
						double rNew = blur[0][0]* tmp[i-1][j-1].getRed()+
										blur[0][1]* tmp[i-1][j].getRed() +
										blur[0][2]* tmp[i-1][j+1].getRed() +
										blur[1][0]* tmp[i][j-1].getRed() +
										blur[1][1]* tmp[i][j].getRed() + 
										blur[1][2]* tmp[i][j+1].getRed() +
										blur[2][0]* tmp[i+1][j+1].getRed() +
										blur[2][1]* tmp[i+1][j].getRed() +
										blur[2][2]* tmp[i+1][j+1].getRed();

						double gNew = blur[0][0]* tmp[i-1][j-1].getGreen()+
									blur[0][1]* tmp[i-1][j].getGreen() +
									blur[0][2]* tmp[i-1][j+1].getGreen() +
									blur[1][0]* tmp[i][j-1].getGreen() +
									blur[1][1]* tmp[i][j].getGreen() + 
									blur[1][2]* tmp[i][j+1].getGreen() +
									blur[2][0]* tmp[i+1][j+1].getGreen() +
									blur[2][1]* tmp[i+1][j].getGreen() +
									blur[2][2]* tmp[i+1][j+1].getGreen();

						double bNew = blur[0][0]* tmp[i-1][j-1].getBlue()+
									blur[0][1]* tmp[i-1][j].getBlue() +
									blur[0][2]* tmp[i-1][j+1].getBlue() +
									blur[1][0]* tmp[i][j-1].getBlue() +
									blur[1][1]* tmp[i][j].getBlue() + 
									blur[1][2]* tmp[i][j+1].getBlue() +
									blur[2][0]* tmp[i+1][j+1].getBlue() +
									blur[2][1]* tmp[i+1][j].getBlue() +
									blur[2][2]* tmp[i+1][j+1].getBlue();
						rNew=rNew>255?255:rNew;
						gNew=gNew>255?255:gNew;
						bNew=bNew>255?255:bNew;
						
						tmp[i][j] = new Color ((int)rNew, (int)gNew, (int)bNew);
						
					
				}
			}
		return tmp;
	}



	public static Color[][]motionBlur(Color[][] img) {
		//	Clone matrix to not replace original
			Color[][] tmp = ImageUtils.cloneArray(img);
	
			int blur[][] = {
				{1,0,0,0,0,0,0,0,0},
				{0,1,0,0,0,0,0,0,0},
				{0,0,1,0,0,0,0,0,0},
				{0,0,0,1,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0},
				{0,0,0,0,0,1,0,0,0},
				{0,0,0,0,0,0,1,0,0},
				{0,0,0,0,0,0,0,1,0},
				{0,0,0,0,0,0,0,0,1},
			};

			double factor = 1/9;
			
		//	Runs through entire matrix
			for( int i = 4; i < tmp.length-5; i++){
					for( int j = 4; j< tmp[i].length-5;j++) {
	
	
						
							Color pixel = tmp[i][j];
							int r = pixel.getRed();
							int g = pixel.getGreen();
							int b = pixel.getBlue();
							
							double rNew = blur[0][0]* tmp[i-4][j-4].getRed()+
											blur[0][1]* tmp[i-4][j-3].getRed() +
											blur[0][2]* tmp[i-4][j-2].getRed() +
											blur[0][3]* tmp[i-4][j-1].getRed() +
											  blur[0][4]* tmp[i-4][j].getRed() + 
											blur[0][5]* tmp[i-4][j+1].getRed() +
											blur[0][6]* tmp[i-4][j+2].getRed() +
											blur[0][7]* tmp[i-4][j+3].getRed() +
											blur[0][8]* tmp[i-4][j+4].getRed()+

											blur[1][0]* tmp[i-3][j-4].getRed()+
											blur[1][1]* tmp[i-3][j-3].getRed() +
											blur[1][2]* tmp[i-3][j-2].getRed() +
											blur[1][3]* tmp[i-3][j-1].getRed() +
											  blur[1][4]* tmp[i-3][j].getRed() + 
											blur[1][5]* tmp[i-3][j+1].getRed() +
											blur[1][6]* tmp[i-3][j+2].getRed() +
											blur[1][7]* tmp[i-3][j+3].getRed() +
											blur[1][8]* tmp[i-3][j+4].getRed() +
											
											blur[2][0]* tmp[i-2][j-4].getRed()+
											blur[2][1]* tmp[i-2][j-3].getRed() +
											blur[2][2]* tmp[i-2][j-2].getRed() +
											blur[2][3]* tmp[i-2][j-1].getRed() +
											  blur[2][4]* tmp[i-2][j].getRed() + 
											blur[2][5]* tmp[i-2][j+1].getRed() +
											blur[2][6]* tmp[i-2][j+2].getRed() +
											blur[2][7]* tmp[i-2][j+3].getRed() +
											blur[2][8]* tmp[i-2][j+4].getRed() +

											blur[3][0]* tmp[i-1][j-4].getRed()+
											blur[3][1]* tmp[i-1][j-3].getRed() +
											blur[3][2]* tmp[i-1][j-2].getRed() +
											blur[3][3]* tmp[i-1][j-1].getRed() +
											  blur[3][4]* tmp[i-1][j].getRed() + 
											blur[3][5]* tmp[i-1][j+1].getRed() +
											blur[3][6]* tmp[i-1][j+2].getRed() +
											blur[3][7]* tmp[i-1][j+3].getRed() +
											blur[3][8]* tmp[i-1][j+4].getRed() +

											blur[4][0]* tmp[i][j-4].getRed()+
											blur[4][1]* tmp[i][j-3].getRed() +
											blur[4][2]* tmp[i][j-2].getRed() +
											blur[4][3]* tmp[i][j-1].getRed() +
											  blur[4][4]* tmp[i][j].getRed() + 
											blur[4][5]* tmp[i][j+1].getRed() +
											blur[4][6]* tmp[i][j+2].getRed() +
											blur[4][7]* tmp[i][j+3].getRed() +
											blur[4][8]* tmp[i][j+4].getRed() +

											blur[5][0]* tmp[i+1][j-4].getRed()+
											blur[5][1]* tmp[i+1][j-3].getRed() +
											blur[5][2]* tmp[i+1][j-2].getRed() +
											blur[5][3]* tmp[i+1][j-1].getRed() +
											  blur[5][4]* tmp[i+1][j].getRed() + 
											blur[5][5]* tmp[i+1][j+1].getRed() +
											blur[5][6]* tmp[i+1][j+2].getRed() +
											blur[5][7]* tmp[i+1][j+3].getRed() +
											blur[5][8]* tmp[i+1][j+4].getRed() +

											blur[6][0]* tmp[i+2][j-4].getRed()+
											blur[6][1]* tmp[i+2][j-3].getRed() +
											blur[6][2]* tmp[i+2][j-2].getRed() +
											blur[6][3]* tmp[i+2][j-1].getRed() +
											  blur[6][4]* tmp[i+2][j].getRed() + 
											blur[6][5]* tmp[i+2][j+1].getRed() +
											blur[6][6]* tmp[i+2][j+2].getRed() +
											blur[6][7]* tmp[i+2][j+3].getRed() +
											blur[6][8]* tmp[i+2][j+4].getRed() +

											blur[7][0]* tmp[i+3][j-4].getRed()+
											blur[7][1]* tmp[i+3][j-3].getRed() +
											blur[7][2]* tmp[i+3][j-2].getRed() +
											blur[7][3]* tmp[i+3][j-1].getRed() +
											  blur[7][4]* tmp[i+3][j].getRed() + 
											blur[7][5]* tmp[i+3][j+1].getRed() +
											blur[7][6]* tmp[i+3][j+2].getRed() +
											blur[7][7]* tmp[i+3][j+3].getRed() +
											blur[7][8]* tmp[i+3][j+4].getRed() +

											blur[8][0]* tmp[i+4][j-4].getRed()+
											blur[8][1]* tmp[i+4][j-3].getRed() +
											blur[8][2]* tmp[i+4][j-2].getRed() +
											blur[8][3]* tmp[i+4][j-1].getRed() +
											  blur[8][4]* tmp[i+4][j].getRed() + 
											blur[8][5]* tmp[i+4][j+1].getRed() +
											blur[8][6]* tmp[i+4][j+2].getRed() +
											blur[8][7]* tmp[i+4][j+3].getRed() +
											blur[8][8]* tmp[i+4][j+4].getRed();
	
							double gNew = blur[0][0]* tmp[i-4][j-4].getGreen()+
											blur[0][1]* tmp[i-4][j-3].getGreen() +
											blur[0][2]* tmp[i-4][j-2].getGreen() +
											blur[0][3]* tmp[i-4][j-1].getGreen() +
											  blur[0][4]* tmp[i-4][j].getGreen() + 
											blur[0][5]* tmp[i-4][j+1].getGreen() +
											blur[0][6]* tmp[i-4][j+2].getGreen() +
											blur[0][7]* tmp[i-4][j+3].getGreen() +
											blur[0][8]* tmp[i-4][j+4].getGreen()+

											blur[1][0]* tmp[i-3][j-4].getGreen()+
											blur[1][1]* tmp[i-3][j-3].getGreen() +
											blur[1][2]* tmp[i-3][j-2].getGreen() +
											blur[1][3]* tmp[i-3][j-1].getGreen() +
											  blur[1][4]* tmp[i-3][j].getGreen() + 
											blur[1][5]* tmp[i-3][j+1].getGreen() +
											blur[1][6]* tmp[i-3][j+2].getGreen() +
											blur[1][7]* tmp[i-3][j+3].getGreen() +
											blur[1][8]* tmp[i-3][j+4].getGreen() +
											
											blur[2][0]* tmp[i-2][j-4].getGreen()+
											blur[2][1]* tmp[i-2][j-3].getGreen() +
											blur[2][2]* tmp[i-2][j-2].getGreen() +
											blur[2][3]* tmp[i-2][j-1].getGreen() +
											  blur[2][4]* tmp[i-2][j].getGreen() + 
											blur[2][5]* tmp[i-2][j+1].getGreen() +
											blur[2][6]* tmp[i-2][j+2].getGreen() +
											blur[2][7]* tmp[i-2][j+3].getGreen() +
											blur[2][8]* tmp[i-2][j+4].getGreen() +

											blur[3][0]* tmp[i-1][j-4].getGreen()+
											blur[3][1]* tmp[i-1][j-3].getGreen() +
											blur[3][2]* tmp[i-1][j-2].getGreen() +
											blur[3][3]* tmp[i-1][j-1].getGreen() +
											  blur[3][4]* tmp[i-1][j].getGreen() + 
											blur[3][5]* tmp[i-1][j+1].getGreen() +
											blur[3][6]* tmp[i-1][j+2].getGreen() +
											blur[3][7]* tmp[i-1][j+3].getGreen() +
											blur[3][8]* tmp[i-1][j+4].getGreen() +

											blur[4][0]* tmp[i][j-4].getGreen()+
											blur[4][1]* tmp[i][j-3].getGreen() +
											blur[4][2]* tmp[i][j-2].getGreen() +
											blur[4][3]* tmp[i][j-1].getGreen() +
											  blur[4][4]* tmp[i][j].getGreen() + 
											blur[4][5]* tmp[i][j+1].getGreen() +
											blur[4][6]* tmp[i][j+2].getGreen() +
											blur[4][7]* tmp[i][j+3].getGreen() +
											blur[4][8]* tmp[i][j+4].getGreen() +

											blur[5][0]* tmp[i+1][j-4].getGreen()+
											blur[5][1]* tmp[i+1][j-3].getGreen() +
											blur[5][2]* tmp[i+1][j-2].getGreen() +
											blur[5][3]* tmp[i+1][j-1].getGreen() +
											  blur[5][4]* tmp[i+1][j].getGreen() + 
											blur[5][5]* tmp[i+1][j+1].getGreen() +
											blur[5][6]* tmp[i+1][j+2].getGreen() +
											blur[5][7]* tmp[i+1][j+3].getGreen() +
											blur[5][8]* tmp[i+1][j+4].getGreen() +

											blur[6][0]* tmp[i+2][j-4].getGreen()+
											blur[6][1]* tmp[i+2][j-3].getGreen() +
											blur[6][2]* tmp[i+2][j-2].getGreen() +
											blur[6][3]* tmp[i+2][j-1].getGreen() +
											  blur[6][4]* tmp[i+2][j].getGreen() + 
											blur[6][5]* tmp[i+2][j+1].getGreen() +
											blur[6][6]* tmp[i+2][j+2].getGreen() +
											blur[6][7]* tmp[i+2][j+3].getGreen() +
											blur[6][8]* tmp[i+2][j+4].getGreen() +

											blur[7][0]* tmp[i+3][j-4].getGreen()+
											blur[7][1]* tmp[i+3][j-3].getGreen() +
											blur[7][2]* tmp[i+3][j-2].getGreen() +
											blur[7][3]* tmp[i+3][j-1].getGreen() +
											  blur[7][4]* tmp[i+3][j].getGreen() + 
											blur[7][5]* tmp[i+3][j+1].getGreen() +
											blur[7][6]* tmp[i+3][j+2].getGreen() +
											blur[7][7]* tmp[i+3][j+3].getGreen() +
											blur[7][8]* tmp[i+3][j+4].getGreen() +

											blur[8][0]* tmp[i+4][j-4].getGreen()+
											blur[8][1]* tmp[i+4][j-3].getGreen() +
											blur[8][2]* tmp[i+4][j-2].getGreen() +
											blur[8][3]* tmp[i+4][j-1].getGreen() +
											  blur[8][4]* tmp[i+4][j].getGreen() + 
											blur[8][5]* tmp[i+4][j+1].getGreen() +
											blur[8][6]* tmp[i+4][j+2].getGreen() +
											blur[8][7]* tmp[i+4][j+3].getGreen() +
											blur[8][8]* tmp[i+4][j+4].getGreen();
	
							double bNew = blur[0][0]* tmp[i-4][j-4].getBlue()+
							blur[0][1]* tmp[i-4][j-3].getBlue() +
							blur[0][2]* tmp[i-4][j-2].getBlue() +
							blur[0][3]* tmp[i-4][j-1].getBlue() +
							  blur[0][4]* tmp[i-4][j].getBlue() + 
							blur[0][5]* tmp[i-4][j+1].getBlue() +
							blur[0][6]* tmp[i-4][j+2].getBlue() +
							blur[0][7]* tmp[i-4][j+3].getBlue() +
							blur[0][8]* tmp[i-4][j+4].getBlue()+

							blur[1][0]* tmp[i-3][j-4].getBlue()+
							blur[1][1]* tmp[i-3][j-3].getBlue() +
							blur[1][2]* tmp[i-3][j-2].getBlue() +
							blur[1][3]* tmp[i-3][j-1].getBlue() +
							  blur[1][4]* tmp[i-3][j].getBlue() + 
							blur[1][5]* tmp[i-3][j+1].getBlue() +
							blur[1][6]* tmp[i-3][j+2].getBlue() +
							blur[1][7]* tmp[i-3][j+3].getBlue() +
							blur[1][8]* tmp[i-3][j+4].getBlue() +
							
							blur[2][0]* tmp[i-2][j-4].getBlue()+
							blur[2][1]* tmp[i-2][j-3].getBlue() +
							blur[2][2]* tmp[i-2][j-2].getBlue() +
							blur[2][3]* tmp[i-2][j-1].getBlue() +
							  blur[2][4]* tmp[i-2][j].getBlue() + 
							blur[2][5]* tmp[i-2][j+1].getBlue() +
							blur[2][6]* tmp[i-2][j+2].getBlue() +
							blur[2][7]* tmp[i-2][j+3].getBlue() +
							blur[2][8]* tmp[i-2][j+4].getBlue() +

							blur[3][0]* tmp[i-1][j-4].getBlue()+
							blur[3][1]* tmp[i-1][j-3].getBlue() +
							blur[3][2]* tmp[i-1][j-2].getBlue() +
							blur[3][3]* tmp[i-1][j-1].getBlue() +
							  blur[3][4]* tmp[i-1][j].getBlue() + 
							blur[3][5]* tmp[i-1][j+1].getBlue() +
							blur[3][6]* tmp[i-1][j+2].getBlue() +
							blur[3][7]* tmp[i-1][j+3].getBlue() +
							blur[3][8]* tmp[i-1][j+4].getBlue() +

							blur[4][0]* tmp[i][j-4].getBlue()+
							blur[4][1]* tmp[i][j-3].getBlue() +
							blur[4][2]* tmp[i][j-2].getBlue() +
							blur[4][3]* tmp[i][j-1].getBlue() +
							  blur[4][4]* tmp[i][j].getBlue() + 
							blur[4][5]* tmp[i][j+1].getBlue() +
							blur[4][6]* tmp[i][j+2].getBlue() +
							blur[4][7]* tmp[i][j+3].getBlue() +
							blur[4][8]* tmp[i][j+4].getBlue() +

							blur[5][0]* tmp[i+1][j-4].getBlue()+
							blur[5][1]* tmp[i+1][j-3].getBlue() +
							blur[5][2]* tmp[i+1][j-2].getBlue() +
							blur[5][3]* tmp[i+1][j-1].getBlue() +
							  blur[5][4]* tmp[i+1][j].getBlue() + 
							blur[5][5]* tmp[i+1][j+1].getBlue() +
							blur[5][6]* tmp[i+1][j+2].getBlue() +
							blur[5][7]* tmp[i+1][j+3].getBlue() +
							blur[5][8]* tmp[i+1][j+4].getBlue() +

							blur[6][0]* tmp[i+2][j-4].getBlue()+
							blur[6][1]* tmp[i+2][j-3].getBlue() +
							blur[6][2]* tmp[i+2][j-2].getBlue() +
							blur[6][3]* tmp[i+2][j-1].getBlue() +
							  blur[6][4]* tmp[i+2][j].getBlue() + 
							blur[6][5]* tmp[i+2][j+1].getBlue() +
							blur[6][6]* tmp[i+2][j+2].getBlue() +
							blur[6][7]* tmp[i+2][j+3].getBlue() +
							blur[6][8]* tmp[i+2][j+4].getBlue() +

							blur[7][0]* tmp[i+3][j-4].getBlue()+
							blur[7][1]* tmp[i+3][j-3].getBlue() +
							blur[7][2]* tmp[i+3][j-2].getBlue() +
							blur[7][3]* tmp[i+3][j-1].getBlue() +
							  blur[7][4]* tmp[i+3][j].getBlue() + 
							blur[7][5]* tmp[i+3][j+1].getBlue() +
							blur[7][6]* tmp[i+3][j+2].getBlue() +
							blur[7][7]* tmp[i+3][j+3].getBlue() +
							blur[7][8]* tmp[i+3][j+4].getBlue() +

							blur[8][0]* tmp[i+4][j-4].getBlue()+
							blur[8][1]* tmp[i+4][j-3].getBlue() +
							blur[8][2]* tmp[i+4][j-2].getBlue() +
							blur[8][3]* tmp[i+4][j-1].getBlue() +
							  blur[8][4]* tmp[i+4][j].getBlue() + 
							blur[8][5]* tmp[i+4][j+1].getBlue() +
							blur[8][6]* tmp[i+4][j+2].getBlue() +
							blur[8][7]* tmp[i+4][j+3].getBlue() +
							blur[8][8]* tmp[i+4][j+4].getBlue();
							rNew=rNew/9>255?255:rNew/9;
							gNew=gNew/9>255?255:gNew/9;
							bNew=bNew/9>255?255:bNew/9;
							
							tmp[i][j] = new Color ((int)rNew, (int)gNew, (int)bNew);
							
						
					}
				}
			
				return tmp;
		}



		public static Color[][] edges(Color[][] img) {
			//	Clone matrix to not replace original
				Color[][] tmp = ImageUtils.cloneArray(img);
		
				double blur[][] = {
					{0,0,-1,0,0},
					{0,0,-1,0,0},
					{0,0,4,0,0},
					{0,0,-1,0,0},
					{0,0,-1,0,0}
				};
				
			//	Runs through entire matrix
				for( int i = 2; i < tmp.length-2; i++){
						for( int j = 2; j< tmp[i].length-2;j++) {
		
		
							
								Color pixel = tmp[i][j];
								int r = pixel.getRed();
								int g = pixel.getGreen();
								int b = pixel.getBlue();
								
								double rNew = blur[0][0]* tmp[i-2][j-2].getRed()+
												blur[0][1]* tmp[i-2][j-1].getRed() +
												blur[0][2]* tmp[i-2][j].getRed() +
												blur[0][3]* tmp[i-2][j+1].getRed() +
												blur[0][4]* tmp[i-2][j+2].getRed() + 

												blur[1][0]* tmp[i-1][j-2].getRed()+
												blur[1][1]* tmp[i-1][j-1].getRed() +
												blur[1][2]* tmp[i-1][j].getRed() +
												blur[1][3]* tmp[i-1][j+1].getRed() +
												blur[1][4]* tmp[i-1][j+2].getRed() +

												blur[2][0]* tmp[i][j-2].getRed()+
												blur[2][1]* tmp[i][j-1].getRed() +
												blur[2][2]* tmp[i][j].getRed() +
												blur[2][3]* tmp[i][j+1].getRed() +
												blur[2][4]* tmp[i][j+2].getRed() +

												blur[3][0]* tmp[i+1][j-2].getRed()+
												blur[3][1]* tmp[i+1][j-1].getRed() +
												blur[3][2]* tmp[i+1][j].getRed() +
												blur[3][3]* tmp[i+1][j+1].getRed() +
												blur[3][4]* tmp[i+1][j+2].getRed() +

												blur[4][0]* tmp[i+2][j-2].getRed()+
												blur[4][1]* tmp[i+2][j-1].getRed() +
												blur[4][2]* tmp[i+2][j].getRed() +
												blur[4][3]* tmp[i+2][j+1].getRed() +
												blur[4][4]* tmp[i+2][j+2].getRed();
		
								double gNew = blur[0][0]* tmp[i-2][j-2].getGreen()+
								blur[0][1]* tmp[i-2][j-1].getGreen() +
								blur[0][2]* tmp[i-2][j].getGreen() +
								blur[0][3]* tmp[i-2][j+1].getGreen() +
								blur[0][4]* tmp[i-2][j+2].getGreen() + 

								blur[1][0]* tmp[i-1][j-2].getGreen()+
								blur[1][1]* tmp[i-1][j-1].getGreen() +
								blur[1][2]* tmp[i-1][j].getGreen() +
								blur[1][3]* tmp[i-1][j+1].getGreen() +
								blur[1][4]* tmp[i-1][j+2].getGreen() +

								blur[2][0]* tmp[i][j-2].getGreen()+
								blur[2][1]* tmp[i][j-1].getGreen() +
								blur[2][2]* tmp[i][j].getGreen() +
								blur[2][3]* tmp[i][j+1].getGreen() +
								blur[2][4]* tmp[i][j+2].getGreen() +

								blur[3][0]* tmp[i+1][j-2].getGreen()+
								blur[3][1]* tmp[i+1][j-1].getGreen() +
								blur[3][2]* tmp[i+1][j].getGreen() +
								blur[3][3]* tmp[i+1][j+1].getGreen() +
								blur[3][4]* tmp[i+1][j+2].getGreen() +

								blur[4][0]* tmp[i+2][j-2].getGreen()+
								blur[4][1]* tmp[i+2][j-1].getGreen() +
								blur[4][2]* tmp[i+2][j].getGreen() +
								blur[4][3]* tmp[i+2][j+1].getGreen() +
								blur[4][4]* tmp[i+2][j+2].getGreen();

								double bNew = blur[0][0]* tmp[i-2][j-2].getBlue()+
												blur[0][1]* tmp[i-2][j-1].getBlue() +
												blur[0][2]* tmp[i-2][j].getBlue() +
												blur[0][3]* tmp[i-2][j+1].getBlue() +
												blur[0][4]* tmp[i-2][j+2].getBlue() + 

												blur[1][0]* tmp[i-1][j-2].getBlue()+
												blur[1][1]* tmp[i-1][j-1].getBlue() +
												blur[1][2]* tmp[i-1][j].getBlue() +
												blur[1][3]* tmp[i-1][j+1].getBlue() +
												blur[1][4]* tmp[i-1][j+2].getBlue() +

												blur[2][0]* tmp[i][j-2].getBlue()+
												blur[2][1]* tmp[i][j-1].getBlue() +
												blur[2][2]* tmp[i][j].getBlue() +
												blur[2][3]* tmp[i][j+1].getBlue() +
												blur[2][4]* tmp[i][j+2].getBlue() +

												blur[3][0]* tmp[i+1][j-2].getBlue()+
												blur[3][1]* tmp[i+1][j-1].getBlue() +
												blur[3][2]* tmp[i+1][j].getBlue() +
												blur[3][3]* tmp[i+1][j+1].getBlue() +
												blur[3][4]* tmp[i+1][j+2].getBlue() +

												blur[4][0]* tmp[i+2][j-2].getBlue()+
												blur[4][1]* tmp[i+2][j-1].getBlue() +
												blur[4][2]* tmp[i+2][j].getBlue() +
												blur[4][3]* tmp[i+2][j+1].getBlue() +
												blur[4][4]* tmp[i+2][j+2].getBlue();
								rNew=rNew>255?255:rNew;
								rNew=rNew<0?0:rNew;
								gNew=gNew>255?255:gNew;
								gNew=gNew<0?0:gNew;
								bNew=bNew>255?255:bNew;
								bNew=bNew<0?0:bNew;
								
								tmp[i][j] = new Color ((int)rNew, (int)gNew, (int)bNew);
								
							
						}
					}
				return tmp;
			}

			public static Color[][] edgesHorizontal(Color[][] img) {
				//	Clone matrix to not replace original
					Color[][] tmp = ImageUtils.cloneArray(img);
			
					double blur[][] = {
						{0,0,-1,0,0},
						{0,0,-1,0,0},
						{0,0,2,0,0},
						{0,0,0,0,0},
						{0,0,0,0,0}
					};
					
				//	Runs through entire matrix
					for( int i = 2; i < tmp.length-2; i++){
							for( int j = 2; j< tmp[i].length-2;j++) {
			
			
								
									Color pixel = tmp[i][j];
									int r = pixel.getRed();
									int g = pixel.getGreen();
									int b = pixel.getBlue();
									
									double rNew = blur[0][0]* tmp[i-2][j-2].getRed()+
													blur[0][1]* tmp[i-2][j-1].getRed() +
													blur[0][2]* tmp[i-2][j].getRed() +
													blur[0][3]* tmp[i-2][j+1].getRed() +
													blur[0][4]* tmp[i-2][j+2].getRed() + 
	
													blur[1][0]* tmp[i-1][j-2].getRed()+
													blur[1][1]* tmp[i-1][j-1].getRed() +
													blur[1][2]* tmp[i-1][j].getRed() +
													blur[1][3]* tmp[i-1][j+1].getRed() +
													blur[1][4]* tmp[i-1][j+2].getRed() +
	
													blur[2][0]* tmp[i][j-2].getRed()+
													blur[2][1]* tmp[i][j-1].getRed() +
													blur[2][2]* tmp[i][j].getRed() +
													blur[2][3]* tmp[i][j+1].getRed() +
													blur[2][4]* tmp[i][j+2].getRed() +
	
													blur[3][0]* tmp[i+1][j-2].getRed()+
													blur[3][1]* tmp[i+1][j-1].getRed() +
													blur[3][2]* tmp[i+1][j].getRed() +
													blur[3][3]* tmp[i+1][j+1].getRed() +
													blur[3][4]* tmp[i+1][j+2].getRed() +
	
													blur[4][0]* tmp[i+2][j-2].getRed()+
													blur[4][1]* tmp[i+2][j-1].getRed() +
													blur[4][2]* tmp[i+2][j].getRed() +
													blur[4][3]* tmp[i+2][j+1].getRed() +
													blur[4][4]* tmp[i+2][j+2].getRed();
			
									double gNew = blur[0][0]* tmp[i-2][j-2].getGreen()+
									blur[0][1]* tmp[i-2][j-1].getGreen() +
									blur[0][2]* tmp[i-2][j].getGreen() +
									blur[0][3]* tmp[i-2][j+1].getGreen() +
									blur[0][4]* tmp[i-2][j+2].getGreen() + 
	
									blur[1][0]* tmp[i-1][j-2].getGreen()+
									blur[1][1]* tmp[i-1][j-1].getGreen() +
									blur[1][2]* tmp[i-1][j].getGreen() +
									blur[1][3]* tmp[i-1][j+1].getGreen() +
									blur[1][4]* tmp[i-1][j+2].getGreen() +
	
									blur[2][0]* tmp[i][j-2].getGreen()+
									blur[2][1]* tmp[i][j-1].getGreen() +
									blur[2][2]* tmp[i][j].getGreen() +
									blur[2][3]* tmp[i][j+1].getGreen() +
									blur[2][4]* tmp[i][j+2].getGreen() +
	
									blur[3][0]* tmp[i+1][j-2].getGreen()+
									blur[3][1]* tmp[i+1][j-1].getGreen() +
									blur[3][2]* tmp[i+1][j].getGreen() +
									blur[3][3]* tmp[i+1][j+1].getGreen() +
									blur[3][4]* tmp[i+1][j+2].getGreen() +
	
									blur[4][0]* tmp[i+2][j-2].getGreen()+
									blur[4][1]* tmp[i+2][j-1].getGreen() +
									blur[4][2]* tmp[i+2][j].getGreen() +
									blur[4][3]* tmp[i+2][j+1].getGreen() +
									blur[4][4]* tmp[i+2][j+2].getGreen();
	
									double bNew = blur[0][0]* tmp[i-2][j-2].getBlue()+
													blur[0][1]* tmp[i-2][j-1].getBlue() +
													blur[0][2]* tmp[i-2][j].getBlue() +
													blur[0][3]* tmp[i-2][j+1].getBlue() +
													blur[0][4]* tmp[i-2][j+2].getBlue() + 
	
													blur[1][0]* tmp[i-1][j-2].getBlue()+
													blur[1][1]* tmp[i-1][j-1].getBlue() +
													blur[1][2]* tmp[i-1][j].getBlue() +
													blur[1][3]* tmp[i-1][j+1].getBlue() +
													blur[1][4]* tmp[i-1][j+2].getBlue() +
	
													blur[2][0]* tmp[i][j-2].getBlue()+
													blur[2][1]* tmp[i][j-1].getBlue() +
													blur[2][2]* tmp[i][j].getBlue() +
													blur[2][3]* tmp[i][j+1].getBlue() +
													blur[2][4]* tmp[i][j+2].getBlue() +
	
													blur[3][0]* tmp[i+1][j-2].getBlue()+
													blur[3][1]* tmp[i+1][j-1].getBlue() +
													blur[3][2]* tmp[i+1][j].getBlue() +
													blur[3][3]* tmp[i+1][j+1].getBlue() +
													blur[3][4]* tmp[i+1][j+2].getBlue() +
	
													blur[4][0]* tmp[i+2][j-2].getBlue()+
													blur[4][1]* tmp[i+2][j-1].getBlue() +
													blur[4][2]* tmp[i+2][j].getBlue() +
													blur[4][3]* tmp[i+2][j+1].getBlue() +
													blur[4][4]* tmp[i+2][j+2].getBlue();
									rNew=rNew>255?255:rNew;
									rNew=rNew<0?0:rNew;
									gNew=gNew>255?255:gNew;
									gNew=gNew<0?0:gNew;
									bNew=bNew>255?255:bNew;
									bNew=bNew<0?0:bNew;
									
									tmp[i][j] = new Color ((int)rNew, (int)gNew, (int)bNew);
									
								
							}
						}
					return tmp;
				}

			public static Color[][] sharpen2(BufferedImage img){
				BufferedImage bufferedImage = img;

				Kernel kernel = new Kernel(3, 3, new float[] { -1, -1, -1, -1, 9, -1, -1,
					-1, -1 });
				BufferedImageOp op = new ConvolveOp(kernel);
				bufferedImage = op.filter(bufferedImage, null);
				bufferedImage.createGraphics();
				return convertTo2DUsingGetRGB(bufferedImage);
			}

			// public static Color[][] sharpen3(Color[][] img){

			// 	Color[][] tmp = ImageUtils.cloneArray(img);
			// 	double factor = 1.0;
			// 	double bias = 0.0;
		
			// 	double blur[][] = {
			// 		{0,0,-1,0,0},
			// 		{0,0,-1,0,0},
			// 		{0,0,4,0,0},
			// 		{0,0,-1,0,0},
			// 		{0,0,-1,0,0}
			// 	};

			// 	for(int x = 0; x < tmp.length; x++)
			// 	for(int y = 0; y < tmp[x].length; y++)
			// 	{
			// 		double red = 0.0, green = 0.0, blue = 0.0;

			// 		//multiply every value of the filter with corresponding image pixel
			// 		for(int filterY = 0; filterY < blur.length-3; filterY++)
			// 		for(int filterX = 0; filterX < blur.length-3; filterX++)
			// 		{
			// 		int imageX = (x - blur.length / 2 + filterX + tmp.length) % tmp.length;
			// 		int imageY = (y - blur.length / 2 + filterY + tmp[x].length) % tmp[x].length;
			// 		System.out.println("imageY: " + imageY);
			// 		System.out.println("imageX: " + imageX);
			// 		System.out.println("tmplength: " + tmp.length);
			// 		red += tmp[imageY * tmp.length + imageX][imageY * tmp[x].length + imageX].getRed() * blur[filterY][filterX];
			// 		green += tmp[imageY * tmp.length + imageX][imageY * tmp[x].length + imageX].getGreen() * blur[filterY][filterX];
			// 		blue += tmp[imageY * tmp.length + imageX][imageY * tmp[x].length + imageX].getBlue() * blur[filterY][filterX];
			// 		}

			// 		//truncate values smaller than zero and larger than 255
			// 		tmp[y * tmp.length + x][y * tmp[x].length + x]=new Color(Math.min(Math.max((int)(factor * red + bias), 0), 255), Math.min(Math.max((int)(factor * red + bias), 0), 255), Math.min(Math.max((int)(factor * blue + bias), 0), 255));
			// 		// tmp[y * tmp.length + x][y * tmp[x].length + x].r = min(max(int(factor * red + bias), 0), 255);
			// 		// tmp[y * tmp.length + x][y * tmp[x].length + x].g = min(max(int(factor * green + bias), 0), 255);
			// 		// tmp[y * tmp.length + x][y * tmp[x].length + x].b = min(max(int(factor * blue + bias), 0), 255);
			// }
			// 	return tmp;
			// }



			public static Color[][] edges2Horizontal(BufferedImage img){
				BufferedImage bufferedImage = img;

				Kernel kernel = new Kernel(5, 5, new float[] { 0, 0, -1, 0, 0, 0, 0, -1, 0, 0,0, 0, 2, 0, 0,0, 0, 0, 0, 0,0, 0, 0, 0, 0});
				BufferedImageOp op = new ConvolveOp(kernel);
				bufferedImage = op.filter(bufferedImage, null);
				bufferedImage.createGraphics();
				return convertTo2DUsingGetRGB(bufferedImage);
			}

			public static Color[][] edges2Vertical(BufferedImage img){
				BufferedImage bufferedImage = img;

				Kernel kernel = new Kernel(5, 5, new float[] { 0, 0, -1, 0, 0, 0, 0, -1, 0, 0,0, 0, 4, 0, 0,0, 0, -1, 0, 0,0, 0, -1, 0, 0});
				BufferedImageOp op = new ConvolveOp(kernel);
				bufferedImage = op.filter(bufferedImage, null);
				bufferedImage.createGraphics();
				return convertTo2DUsingGetRGB(bufferedImage);
			}

			private static Color[][] convertTo2DUsingGetRGB(BufferedImage image) {
				int width = image.getWidth();
				int height = image.getHeight();
				int[][] result = new int[height][width];
				Color[][] color=new Color[height][width];
				for (int row = 0; row < height; row++) {
				   for (int col = 0; col < width; col++) {
						int a = image.getRGB(col, row);
					  	result[row][col] = image.getRGB(col, row);
					  	int red = (a >> 16) & 255;
						int green = (a >> 8) & 255;
						int blue = (a) & 255;
					  	color[row][col] = new Color(red, green, blue);
				   }
				}
		  
				return color;
			 }




			public static Color[][]sharpen(Color[][] img) {
				//	Clone matrix to not replace original
					Color[][] tmp = ImageUtils.cloneArray(img);
			
					int blur[][] = {
						{-1,-1,-1},
						{-1,9,-1},
						{-1,-1,-1}
					};
					
				//	Runs through entire matrix
					for( int i = 1; i < tmp.length-5; i++){
							for( int j = 1; j< tmp[i].length-5;j++) {
			
			
								
									Color pixel = tmp[i][j];
									int r = pixel.getRed();
									int g = pixel.getGreen();
									int b = pixel.getBlue();
									
									int rNew = blur[0][0]* tmp[i-1][j-1].getRed()+
													blur[0][1]* tmp[i-1][j].getRed() +
													blur[0][2]* tmp[i-1][j+1].getRed() +
													blur[1][0]* tmp[i][j-1].getRed() +
													blur[1][1]* tmp[i][j].getRed() + 
													blur[1][2]* tmp[i][j+1].getRed() +
													blur[2][0]* tmp[i+1][j+1].getRed() +
													blur[2][1]* tmp[i+1][j].getRed() +
													blur[2][2]* tmp[i+1][j+1].getRed();
			
									int gNew = blur[0][0]* tmp[i-1][j-1].getGreen()+
												blur[0][1]* tmp[i-1][j].getGreen() +
												blur[0][2]* tmp[i-1][j+1].getGreen() +
												blur[1][0]* tmp[i][j-1].getGreen() +
												blur[1][1]* tmp[i][j].getGreen() + 
												blur[1][2]* tmp[i][j+1].getGreen() +
												blur[2][0]* tmp[i+1][j+1].getGreen() +
												blur[2][1]* tmp[i+1][j].getGreen() +
												blur[2][2]* tmp[i+1][j+1].getGreen();
			
									int bNew = blur[0][0]* tmp[i-1][j-1].getBlue()+
												blur[0][1]* tmp[i-1][j].getBlue() +
												blur[0][2]* tmp[i-1][j+1].getBlue() +
												blur[1][0]* tmp[i][j-1].getBlue() +
												blur[1][1]* tmp[i][j].getBlue() + 
												blur[1][2]* tmp[i][j+1].getBlue() +
												blur[2][0]* tmp[i+1][j+1].getBlue() +
												blur[2][1]* tmp[i+1][j].getBlue() +
												blur[2][2]* tmp[i+1][j+1].getBlue();
									rNew=rNew>255?255:rNew;
									rNew=rNew<0?0:rNew;
									gNew=gNew>255?255:gNew;
									gNew=gNew<0?0:gNew;
									bNew=bNew>255?255:bNew;
									bNew=bNew<0?0:bNew;
									
									tmp[i][j] = new Color (rNew, gNew, bNew);
									
								
							}
						}
					return tmp;
				}




public static Color[][]emboss(Color[][] img) {
	//	Clone matrix to not replace original
		Color[][] tmp = ImageUtils.cloneArray(img);

		int blur[][] = {
			{-1,-1,0},
			{-1,0,1},
			{0,1,1}
		};
		
	//	Runs through entire matrix
		for( int i = 1; i < tmp.length-5; i++){
				for( int j = 1; j< tmp[i].length-5;j++) {


					
						Color pixel = tmp[i][j];
						int r = pixel.getRed();
						int g = pixel.getGreen();
						int b = pixel.getBlue();
						
						int rNew = blur[0][0]* tmp[i-1][j-1].getRed()+
										blur[0][1]* tmp[i-1][j].getRed() +
										blur[0][2]* tmp[i-1][j+1].getRed() +
										blur[1][0]* tmp[i][j-1].getRed() +
										blur[1][1]* tmp[i][j].getRed() + 
										blur[1][2]* tmp[i][j+1].getRed() +
										blur[2][0]* tmp[i+1][j+1].getRed() +
										blur[2][1]* tmp[i+1][j].getRed() +
										blur[2][2]* tmp[i+1][j+1].getRed();

						int gNew = blur[0][0]* tmp[i-1][j-1].getGreen()+
									blur[0][1]* tmp[i-1][j].getGreen() +
									blur[0][2]* tmp[i-1][j+1].getGreen() +
									blur[1][0]* tmp[i][j-1].getGreen() +
									blur[1][1]* tmp[i][j].getGreen() + 
									blur[1][2]* tmp[i][j+1].getGreen() +
									blur[2][0]* tmp[i+1][j+1].getGreen() +
									blur[2][1]* tmp[i+1][j].getGreen() +
									blur[2][2]* tmp[i+1][j+1].getGreen();

						int bNew = blur[0][0]* tmp[i-1][j-1].getBlue()+
									blur[0][1]* tmp[i-1][j].getBlue() +
									blur[0][2]* tmp[i-1][j+1].getBlue() +
									blur[1][0]* tmp[i][j-1].getBlue() +
									blur[1][1]* tmp[i][j].getBlue() + 
									blur[1][2]* tmp[i][j+1].getBlue() +
									blur[2][0]* tmp[i+1][j+1].getBlue() +
									blur[2][1]* tmp[i+1][j].getBlue() +
									blur[2][2]* tmp[i+1][j+1].getBlue();
						rNew=rNew>255?255:rNew;
						rNew=rNew<0?0:rNew;
						gNew=gNew>255?255:gNew;
						gNew=gNew<0?0:gNew;
						bNew=bNew>255?255:bNew;
						bNew=bNew<0?0:bNew;
						
						tmp[i][j] = new Color (rNew, gNew, bNew);
						
					
				}
			}
		return tmp;
	}


	public static Color[][] emboss2(BufferedImage img){
		BufferedImage bufferedImage = img;

		Kernel kernel = new Kernel(3, 3, new float[] { -1, -1, 0, -1, 0, 1, 0,
			1, 1 });
		BufferedImageOp op = new ConvolveOp(kernel);
		bufferedImage = op.filter(bufferedImage, null);
		bufferedImage.createGraphics();
		return convertTo2DUsingGetRGB(bufferedImage);
	}



	public static Color[][]mean(Color[][] img) {
		//	Clone matrix to not replace original
			Color[][] tmp = ImageUtils.cloneArray(img);
	
			double blur[][] = {
				{1,1,1},
				{1,1,1},
				{1,1,1}
			};
			
		//	Runs through entire matrix
			for( int i = 1; i < tmp.length-5; i++){
					for( int j = 1; j< tmp[i].length-5;j++) {
	
	
						
							Color pixel = tmp[i][j];
							int r = pixel.getRed();
							int g = pixel.getGreen();
							int b = pixel.getBlue();
							
							double rNew = blur[0][0]* tmp[i-1][j-1].getRed()+
											blur[0][1]* tmp[i-1][j].getRed() +
											blur[0][2]* tmp[i-1][j+1].getRed() +
											blur[1][0]* tmp[i][j-1].getRed() +
											blur[1][1]* tmp[i][j].getRed() + 
											blur[1][2]* tmp[i][j+1].getRed() +
											blur[2][0]* tmp[i+1][j+1].getRed() +
											blur[2][1]* tmp[i+1][j].getRed() +
											blur[2][2]* tmp[i+1][j+1].getRed();
	
							double gNew = blur[0][0]* tmp[i-1][j-1].getGreen()+
										blur[0][1]* tmp[i-1][j].getGreen() +
										blur[0][2]* tmp[i-1][j+1].getGreen() +
										blur[1][0]* tmp[i][j-1].getGreen() +
										blur[1][1]* tmp[i][j].getGreen() + 
										blur[1][2]* tmp[i][j+1].getGreen() +
										blur[2][0]* tmp[i+1][j+1].getGreen() +
										blur[2][1]* tmp[i+1][j].getGreen() +
										blur[2][2]* tmp[i+1][j+1].getGreen();
	
							double bNew = blur[0][0]* tmp[i-1][j-1].getBlue()+
										blur[0][1]* tmp[i-1][j].getBlue() +
										blur[0][2]* tmp[i-1][j+1].getBlue() +
										blur[1][0]* tmp[i][j-1].getBlue() +
										blur[1][1]* tmp[i][j].getBlue() + 
										blur[1][2]* tmp[i][j+1].getBlue() +
										blur[2][0]* tmp[i+1][j+1].getBlue() +
										blur[2][1]* tmp[i+1][j].getBlue() +
										blur[2][2]* tmp[i+1][j+1].getBlue();
							rNew=rNew/9>255?255:rNew/9;
							gNew=gNew/9>255?255:gNew/9;
							bNew=bNew/9>255?255:bNew/9;
							
							tmp[i][j] = new Color ((int)rNew, (int)gNew, (int)bNew);
							
						
					}
				}
			return tmp;
		}




	public static Color[][] median(Color[][] img) {
		//	Clone matrix to not replace original
			Color[][] tmp = ImageUtils.cloneArray(img);
	
			double blur[][] = {
				{1,1,1},
				{1,1,1},
				{1,1,1}
			};
			
		//	Runs through entire matrix
			for( int i = 1; i < tmp.length-5; i++){
					for( int j = 1; j< tmp[i].length-5;j++) {
	
	
						
							Color pixel = tmp[i][j];
							int r = pixel.getRed();
							int g = pixel.getGreen();
							int b = pixel.getBlue();
							
							List<Double> rNew = new ArrayList<Double>();
							rNew.add(blur[0][0]* tmp[i-1][j-1].getRed());
							rNew.add(blur[0][1]* tmp[i-1][j].getRed());
							rNew.add(blur[0][2]* tmp[i-1][j+1].getRed());
							rNew.add(blur[1][0]* tmp[i][j-1].getRed());
							rNew.add(blur[1][1]* tmp[i][j].getRed());
							rNew.add(blur[1][2]* tmp[i][j+1].getRed());
							rNew.add(blur[2][0]* tmp[i+1][j+1].getRed());
							rNew.add(blur[2][1]* tmp[i+1][j].getRed());
							rNew.add(blur[2][2]* tmp[i+1][j+1].getRed());
	
							List<Double> gNew = new ArrayList<Double>(); 
							gNew.add(blur[0][0]* tmp[i-1][j-1].getGreen());
							gNew.add(blur[0][1]* tmp[i-1][j].getGreen());
							gNew.add(blur[0][2]* tmp[i-1][j+1].getGreen());
							gNew.add(blur[1][0]* tmp[i][j-1].getGreen());
							gNew.add(blur[1][1]* tmp[i][j].getGreen() );
							gNew.add(blur[1][2]* tmp[i][j+1].getGreen());
							gNew.add(blur[2][0]* tmp[i+1][j+1].getGreen());
							gNew.add(blur[2][1]* tmp[i+1][j].getGreen());
							gNew.add(blur[2][2]* tmp[i+1][j+1].getGreen());
	
							List<Double> bNew = new ArrayList<Double>(); 
							bNew.add(blur[0][0]* tmp[i-1][j-1].getBlue());
							bNew.add(blur[0][1]* tmp[i-1][j].getBlue());
							bNew.add(blur[0][2]* tmp[i-1][j+1].getBlue());
							bNew.add(blur[1][0]* tmp[i][j-1].getBlue());
							bNew.add(blur[1][1]* tmp[i][j].getBlue());
							bNew.add(blur[1][2]* tmp[i][j+1].getBlue());
							bNew.add(blur[2][0]* tmp[i+1][j+1].getBlue());
							bNew.add(blur[2][1]* tmp[i+1][j].getBlue());
							bNew.add(blur[2][2]* tmp[i+1][j+1].getBlue());

							Collections.sort(rNew);
							Collections.sort(gNew);
							Collections.sort(bNew);

							double medianR;
							if (rNew.size() % 2 == 0)
								medianR = ((double)rNew.get(rNew.size()/2) + (double)rNew.get(rNew.size()/2 - 1))/2;
							else
								medianR = (double) rNew.get(rNew.size()/2);

								double medianG;
							if (rNew.size() % 2 == 0)
								medianG = ((double)rNew.get(rNew.size()/2) + (double)rNew.get(rNew.size()/2 - 1))/2;
							else
								medianG = (double) rNew.get(rNew.size()/2);

								double medianB;
							if (rNew.size() % 2 == 0)
								medianB = ((double)rNew.get(rNew.size()/2) + (double)rNew.get(rNew.size()/2 - 1))/2;
							else
								medianB = (double) rNew.get(rNew.size()/2);

							medianR=medianR>255?255:medianR;
							medianG=medianG>255?255:medianG;
							medianB=medianB>255?255:medianB;
							
							tmp[i][j] = new Color ((int)medianR, (int)medianG, (int)medianB);
							
						
					}
				}
			return tmp;
		}
	





//Method to intensify blue

public static Color[][]filtroComponentesRGB(Color[][] img) {
//Clone matrix to not replace original
Color[][] tmp = ImageUtils.cloneArray(img);

//Runs through entire matrix
for( int i = 0; i < tmp.length; i++)
	{
		for( int j = 0; j< tmp[i].length;j++) {
			
				
//				fetches values of each pixel
				Color pixel = tmp[i][j];
				int r = pixel.getRed();
				int g = pixel.getGreen();
				int b = pixel.getBlue();
				
				int rNew = r+50;
				int gNew = g+0;
				int bNew = b+25;
				
//				if bNew > 255, make 255
				
				if (rNew >255) {
					rNew = 255;
				}
				if (gNew >255) {
					gNew = 255;
				}
				if (bNew >255) {
					bNew = 255;
				}
				tmp[i][j] = new Color (rNew, gNew, bNew);
				
			
		}
	}
return tmp;
}






//	Method to replace all colors of a certain instance with an inputed color
public static Color[][] colorReplacer(Color [][] img) {
	Scanner input = new Scanner(System.in);
	Color[][] tmp = ImageUtils.cloneArray(img);
	
//	Gives user choice for custom or feather
	
	System.out.println("Do you want to change a specific color, or accent the feather?");
	System.out.println("(specific or feather)");
	
	String target = input.next();
	
	int rReplace = 0;
	int gReplace = 0;
	int bReplace = 0;
	if(target.equalsIgnoreCase("specific")) {
//	Asks for values of color to be replaced
	System.out.println("Enter the red value of the pixel you want to replace from 0 to 255");
	
	rReplace = input.nextInt();
	
	System.out.println("Enter the green value of the pixel you want to replace from 0 to 255");
	
	gReplace = input.nextInt();
	
	System.out.println("Enter the blue value of the color you want to replace from 0 to 255");
	
	bReplace = input.nextInt();
	}
	else if (target.equalsIgnoreCase("feather")) {
		rReplace = 156;
		gReplace = 118;
		bReplace = 169;
	}
	
	else {
		System.out.println("Please enter a valid input next time");
	}
//	Asks to use custom color or preset color as replacement
	
	System.out.println("Would you like to use a preset or custom color to replace?");
	System.out.println("(preset or custom)");
	String choice = input.next().toLowerCase();
	

//		sets color range
	int range = 14;
	
//	If preset color is desired
	String newColorName = null;
	
	if (choice.equalsIgnoreCase("preset"))
	{
//		Accept input for new color name
		
		System.out.println("Input the preset color you would like to use (ex. black, blue, cyan, darkgray, gray, magenta, orange, pink, red, white, yellow");
		newColorName = input.next().toLowerCase();
		Color c;
//		
//		if statement to set new color
		if(newColorName.equalsIgnoreCase("black")) {
			c = Color.BLACK;
		}
		
		else if(newColorName.equalsIgnoreCase("blue")) {
			c = Color.BLUE;
		}
		else if(newColorName.equalsIgnoreCase("cyan")) {
			c = Color.CYAN;
		}
		else if(newColorName.equalsIgnoreCase("darkgray")) {
				c = Color.darkGray;
		}
		else if(newColorName.equalsIgnoreCase("gray")) {
					c = Color.gray;
		}
		
		else if(newColorName.equalsIgnoreCase("magenta")) {
			c = Color.magenta;
}
		else if(newColorName.equalsIgnoreCase("orange")) {
			c = Color.orange;
}
		else if(newColorName.equalsIgnoreCase("pink")) {
			c = Color.pink;
}
		else if(newColorName.equalsIgnoreCase("red")) {
			c = Color.red;
}
		else if(newColorName.equalsIgnoreCase("white")) {
			c = Color.white;
}
		else if(newColorName.equalsIgnoreCase("yellow")) {
			c = Color.yellow;
}
			
//			Default color
		else {
			System.out.println("Preset color not detected. I hope you like black.");
			c = Color.black;
	}
//		for loop runs through entire picture
		for( int i = 0; i < tmp.length; i++)
		{
			for( int j = 0; j< tmp[i].length;j++) {
				
				Color pixel = tmp[i][j];
//				Gets color information about current pixel
				int rCurrent = pixel.getRed();
				int gCurrent = pixel.getGreen();
				int bCurrent = pixel.getBlue();

//				If color is close to color you want to replace
				if(rCurrent-range<=rReplace && rReplace<=rCurrent+range 
						&& gCurrent-range<=gReplace && gReplace<=gCurrent+range 
						&& bCurrent-range<=bReplace && bReplace<=bCurrent+range ) {

//					input new color into output
					tmp[i][j] = c;
				}
					
					
					
				
			}
		}
		
	}
	
	else if (choice.equalsIgnoreCase("custom")) {
		int rNew = 0;
		int gNew = 0;
		int bNew = 0;
		
		System.out.println("Input the custom value of red from 0 to 255:");
		
		rNew = input.nextInt();
		
		System.out.println("Input the custom value of green from 0 to 255");
		
		gNew = input.nextInt();
		
		System.out.println("Input the custom value of blue from 0 to 255");
		
		bNew = input.nextInt();
		
		for( int i = 0; i < tmp.length; i++)
		{
			for( int j = 0; j< tmp[i].length;j++) {
				
					Color pixel = tmp[i][j];
//				Gets color information about current pixel
				int rCurrent = pixel.getRed();
				int gCurrent = pixel.getGreen();
				int bCurrent = pixel.getBlue();

//				If color is close to color you want to replace
				if(rCurrent-range<=rReplace && rReplace<=rCurrent+range 
						&& gCurrent-range<=gReplace && gReplace<=gCurrent+range 
						&& bCurrent-range<=bReplace && bReplace<=bCurrent+range ) {
//					
//					replace old color with new color
					tmp[i][j] = new Color (rNew,gNew,bNew);
				}
										
				
			}
		}
		
	}
	else {
		System.out.println("invalid entry. I'm printing the original image");
		
	}
	
	


	return tmp;
}
}
