

import java.awt.Color;
import java.util.Scanner;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter; 
import java.io.FileWriter; 
import java.io.PrintWriter;
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
	public static void saveImage(BufferedImage image, String name) throws IOException{
		ImageIO.write(image, "jpeg", new File(name));
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
	
	filePath = "pokemon2.jpeg";

	
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

Color [][] gris = grisPromedio(orig);

	
	


//	Display images
	utils.display();
	imagenesRecursivas(orig);
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

public static Color[][]imagenesRecursivas(Color[][] img) throws IOException {
	ImageUtils utils = new ImageUtils();
	Color[][] tmp = ImageUtils.cloneArray(img);
	tmp = grisPromedio(tmp);
	int gray=-127;
	List<Color[][]> listaGrises = new ArrayList<Color[][]>();
	for(int i=0; i<256; i=i+8){
		listaGrises.add(brillo(tmp, gray+50));
		gray+=8;
		BufferedImage bi = utils.convertToBufferedFrom2D(brillo(tmp, gray));
		String fileName = "Gris" + i/8;
		saveImage(bi, fileName);
	}
	// LIST SIZE = 32 TYPES OF GRAY


	int x;
	int y;
	x=3;
	y=3;

	File myObj = new File("imagenesRecursivas.html");
	myObj.delete();
	File myObj2 = new File("imagenesRecursivas.html");
	myObj2.createNewFile();

	FileWriter fw = null;
	 BufferedWriter bw = null;
	  PrintWriter pw = null;
	   try {
		    fw = new FileWriter("imagenesRecursivas.html", true);
			 bw = new BufferedWriter(fw);
			 pw = new PrintWriter(bw);
			 pw.println("<FONT SIZE=.05 COLOR=#000008><PRE><title>imagenesRecursivas hecha por Gerardo García</title>");

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



			for(int i = 0; i<tmp[0].length; i ++){
				for(int j =0; j<tmp.length; j++){
					String hex = Integer.toHexString(tmp[j][i].getRGB() & 0xffffff);
								if(hex.length() < 6) 
								{
									if(hex.length()==5)
										hex = "0" + hex;
									if(hex.length()==4)
										hex = "00" + hex;
									if(hex.length()==3)
										hex = "000" + hex;
								}
								hex = "#" + hex;
								int image = changeGrayForImage(tmp[j][i].getRed(), listaGrises);
								pw.print("<img src='Gris"+image+"' width='10' height='4'>");
					 
					pw.flush();
				}
				pw.println("<br>");
			}
			pw.println("</PRE>");
			} 
			 finally { 
				 try { 
					 pw.close(); 
					 bw.close(); 
					 fw.close(); 
					} catch (IOException io) {
					}
				}
				File myObj3 = new File("imagenesRecursivas.html"); 
				Desktop.getDesktop().browse(myObj3.toURI());




	return listaGrises.get(20);
}


public static int changeGrayForImage(int gray, List<Color[][]> list){
	if (isBetween(gray, 0, 8)) {
		return 0;
	} else if (isBetween(gray, 9, 16)){
		return 1;
	} else if (isBetween(gray, 9, 16)){
		return 2;
	} else if (isBetween(gray, 17, 24)){
		return 3;
	} else if (isBetween(gray, 25, 32)){
		return 4;
	} else if (isBetween(gray,33, 40)){
		return 5;
	} else if (isBetween(gray, 41, 48)){
		return 6;
	} else if (isBetween(gray, 49, 56)){
		return 7;
	} else if (isBetween(gray, 57, 64)){
		return 8;
	} else if (isBetween(gray, 65, 72)){
		return 9;
	} else if (isBetween(gray, 73, 80)){
		return 10;
	} else if (isBetween(gray, 81, 88)){
		return 11;
	} else if (isBetween(gray, 89, 96)){
		return 12;
	} else if (isBetween(gray, 97, 104)){
		return 13;
	} else if (isBetween(gray, 105, 112)){
		return 14;
	} else if (isBetween(gray, 113, 120)){
		return 15;
	} else if (isBetween(gray, 121, 128)){
		return 16;
	} else if (isBetween(gray, 129, 136)){
		return 17;
	} else if (isBetween(gray, 137, 144)){
		return 18;
	} else if (isBetween(gray, 145, 152)){
		return 19;
	} else if (isBetween(gray, 153, 160)){
		return 20;
	} else if (isBetween(gray, 161, 168)){
		return 21;
	} else if (isBetween(gray, 169, 176)){
		return 22;
	} else if (isBetween(gray, 177, 184)){
		return 23;
	} else if (isBetween(gray, 185, 192)){
		return 24;
	} else if (isBetween(gray, 193, 200)){
		return 25;
	} else if (isBetween(gray, 201, 208)){
		return 26;
	} else if (isBetween(gray, 209, 216)){
		return 27;
	} else if (isBetween(gray, 217, 224)){
		return 28;
	} else if (isBetween(gray, 225, 232)){
		return 29;
	} else if (isBetween(gray, 233, 240)){
		return 30;
	} else if (isBetween(gray, 241, 255)){
		return 31;
	}
	return 0;
}


public static boolean isBetween(int x, int lower, int upper) {
	return lower <= x && x <= upper;
  }



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



	public static Color[][] brillo(Color[][] img, int brillo) {
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

						int rNew = r+brillo>255?255:r+brillo;
						int gNew =g+brillo>255?255:g+brillo;;
						int bNew = b+brillo>255?255:b+brillo;

						rNew = rNew<0?0:rNew;
						gNew =gNew<0?0:gNew;
						bNew = bNew<0?0:bNew;

						tmp[i][j] = new Color (rNew, gNew, bNew);
						
				}
			}
		return tmp;
		}



public static void resize(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight)
 throws IOException {
 // reads input image
File inputFile = new File(inputImagePath);
BufferedImage inputImage = ImageIO.read(inputFile);

// creates output image
BufferedImage outputImage = new BufferedImage(scaledWidth,
scaledHeight, inputImage.getType());

// scales the input image to the output image
Graphics2D g2d = outputImage.createGraphics();
g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
g2d.dispose();

// extracts extension of output file
String formatName = outputImagePath.substring(outputImagePath
.lastIndexOf(".") + 1);

// writes to output file
ImageIO.write(outputImage, formatName, new File(outputImagePath));
}

/**
     * Resizes an image by a percentage of original size (proportional).
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
public static void resize(String inputImagePath,
String outputImagePath, double percent) throws IOException {
File inputFile = new File(inputImagePath);
BufferedImage inputImage = ImageIO.read(inputFile);
int scaledWidth = (int) (inputImage.getWidth() * percent);
int scaledHeight = (int) (inputImage.getHeight() * percent);
resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
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
