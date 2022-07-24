

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
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
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
	// utils.addImage(orig, "Imagen Original");
	
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
Color [][] ditheringAzar = ditheringAzar(orig);
Color [][] ditheringOrdenada = ditheringOrdenada(orig);
Color [][] ditheringDispersa = ditheringDispersa(orig);
// Color [][] filtroContrasteEcualizador = filtroContrasteEcualizador(orig);

utils.addImage(gris, "Imagen Original");
utils.addImage(ditheringAzar, "Dithering Azar");
utils.addImage(ditheringOrdenada, "Dithering Ordenada");
utils.addImage(ditheringDispersa, "Dithering Dispersa");

// utils.addImage(filtroContrasteEcualizador, "Filtro Contraste con Ecualizador");

	
	


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

public static Color[][]ditheringOrdenada(Color[][] img) {
	//	Clone matrix to not replace original
		Color[][] tmp = ImageUtils.cloneArray(img);
		tmp = grisPromedio(tmp);

		double blur[][] = {
            {8,3,4},
            {6,1,2},
            {7,5,9}
        };
		
	//	Runs through entire matrix
		for( int i = 1; i < tmp.length-5; i=i+3){
				for( int j = 1; j< tmp[i].length-5;j=j+3) {


					
						Color pixel = tmp[i][j];
						int r = pixel.getRed();
						int g = pixel.getGreen();
						int b = pixel.getBlue();
						tmp[i-1][j-1] = (r/28)>=blur[0][0]?new Color (255, 255, 255):new Color(0,0,0);
						tmp[i-1][j]  =(r/28)>=blur[0][1]?new Color (255, 255, 255):new Color(0,0,0);
						tmp[i-1][j+1]=(r/28)>=blur[0][2]?new Color (255, 255, 255):new Color(0,0,0);
						tmp[i][j-1]  =(r/28)>=blur[1][0]?new Color (255, 255, 255):new Color(0,0,0);
						tmp[i][j]   =(r/28)>=blur[1][1]?new Color (255, 255, 255):new Color(0,0,0);
						tmp[i][j+1]=(r/28)>=blur[1][2]?new Color (255, 255, 255):new Color(0,0,0);
						tmp[i+1][j+1]=(r/28)>=blur[2][0]?new Color (255, 255, 255):new Color(0,0,0);
						tmp[i+1][j]  =(r/28)>=blur[2][1]?new Color (255, 255, 255):new Color(0,0,0);
						tmp[i+1][j+1]=(r/28)>=blur[2][2]?new Color (255, 255, 255):new Color(0,0,0);
						
						
					
				}
			}
		return tmp;
	}




	public static Color[][]ditheringDispersa(Color[][] img) {
		//	Clone matrix to not replace original
			Color[][] tmp = ImageUtils.cloneArray(img);
			tmp = grisPromedio(tmp);
	
			double blur[][] = {
				{1,7,4},
				{5,8,3},
				{6,2,9}
			};
			
		//	Runs through entire matrix
			for( int i = 1; i < tmp.length-5; i=i+3){
					for( int j = 1; j< tmp[i].length-5;j=j+3) {
	
	
						
							Color pixel = tmp[i][j];
							int r = pixel.getRed();
							int g = pixel.getGreen();
							int b = pixel.getBlue();
							tmp[i-1][j-1] = (r/28)>=blur[0][0]?new Color (255, 255, 255):new Color(0,0,0);
							tmp[i-1][j]  =(r/28)>=blur[0][1]?new Color (255, 255, 255):new Color(0,0,0);
							tmp[i-1][j+1]=(r/28)>=blur[0][2]?new Color (255, 255, 255):new Color(0,0,0);
							tmp[i][j-1]  =(r/28)>=blur[1][0]?new Color (255, 255, 255):new Color(0,0,0);
							tmp[i][j]   =(r/28)>=blur[1][1]?new Color (255, 255, 255):new Color(0,0,0);
							tmp[i][j+1]=(r/28)>=blur[1][2]?new Color (255, 255, 255):new Color(0,0,0);
							tmp[i+1][j+1]=(r/28)>=blur[2][0]?new Color (255, 255, 255):new Color(0,0,0);
							tmp[i+1][j]  =(r/28)>=blur[2][1]?new Color (255, 255, 255):new Color(0,0,0);
							tmp[i+1][j+1]=(r/28)>=blur[2][2]?new Color (255, 255, 255):new Color(0,0,0);
							
							
						
					}
				}
			return tmp;
		}
	


public static Color[][] ditheringAzar (Color[][] img)throws IOException{

	Color[][] tmp = ImageUtils.cloneArray(img);
	tmp = grisPromedio(tmp);
	Random random = new Random();
	double x;


	   try {
		   

			for(int i=0; i<tmp.length; i++){
				for(int j=0; j<tmp[i].length; j++){
					Color pixel = tmp[i][j];
					x=random.nextInt(255 - 0 + 1) + 0;
					int newGray=x>pixel.getRed()?0:255;
					
					
					tmp[i][j] = new Color(newGray, newGray, newGray);
					}
				}

			} 
			 finally {
				}
				return tmp;

}

public static List<Integer> equalizarHistograma(List<Integer> arr){
	System.out.println("dsfdsf: " + arr.size());
	List<Integer> frecuency = new ArrayList<>();
	int frec=0;
	Collections.sort(arr);
	for(int i =0; i<arr.size(); i++){
		// System.out.println("sdfkln: " + i);

		frec=frec + frecuency(arr, arr.get(i));
		frecuency.add(frec);

	}
	return frecuency;
}

public static int frecuency(List<Integer> arr, int n){
	int cont=0;
	for(int i =0; i<arr.size(); i++){
		if(arr.get(i)==n){
			cont ++;
		}
	}
	return cont;
}



static int mostFrequent(List<Integer> arr, int n)
    {
        // Sort the array
		Collections.sort(arr);
 
        // find the max frequency using linear traversal
        int max_count = 1, 
		res = arr.get(0);
        int curr_count = 1;
 
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) == arr.get(i-1))
                curr_count++;
            else
                curr_count = 1;
 
            if (curr_count > max_count) {
                max_count = curr_count;
                res = arr.get(i-1);
            }
        }
        return res;
    }



	static int leastFrequent(List<Integer> arr, int n)
    {
         
        // Sort the array
		Collections.sort(arr);
     
        // find the min frequency using
        // linear traversal
        int min_count = n+1, res = -1;
        int curr_count = 1;
         
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) == arr.get(i-1))
                curr_count++;
            else {
                if (curr_count < min_count) {
                    min_count = curr_count;
                    res = arr.get(i-1);
                }
                 
                curr_count = 1;
            }
        }
     
        // If last element is least frequent
        if (curr_count < min_count)
        {
            min_count = curr_count;
            res = arr.get(n-1);
        }
     
        return res;
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
