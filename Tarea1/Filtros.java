

import java.awt.Color;
import java.util.Scanner;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
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
public static void main(String[] args)
{
//	Initialize scanner
	Scanner input = new Scanner(System.in);
//	New utility
	ImageUtils utils = new ImageUtils();
	
//	asking user for image location input
	String filePath = "";
	
	// filePath = input.nextLine();
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

Color [][] grisPromedio = grisPromedio(orig);

//	Filtro Gris Correctud

Color [][] grisCorrectud = grisCorrectud(orig);

//	Filtro Gris 3

Color [][] gris3 = gris3(orig);

//	Filtro Gris 4

Color [][] gris4 = gris4(orig);

//	Filtro Gris 5

Color [][] gris5 = gris5(orig);

//	Filtro Gris 6

Color [][] gris6 = gris6(orig);

//	Filtro Gris 7

Color [][] gris7 = gris7(orig);

//	Filtro Rojo

Color [][] filtroRojo = filtroRojo(orig);

//	Filtro Verde

Color [][] filtroVerde = filtroVerde(orig);

//	Filtro Azul

Color [][] filtroAzul = filtroAzul(orig);

//	Filtro Alto Contraste

Color [][] altoContraste = altoContraste(orig);

//	Filtro Alto Contraste Inverso

Color [][] altoContrasteInverso = altoContrasteInverso(orig);

//	Filtro Brillo

Color [][] brillo = brillo(orig);

//	Filtro Mosaico

Color [][] mosaico = mosaico(orig);

//	Filtro Componentes RGB

Color [][] filtroComponentesRGB = filtroComponentesRGB(orig);
	
	
	utils.addImage(grisPromedio, "Gray 1");
	utils.addImage(grisCorrectud, "Gray 2");
	utils.addImage(gris3, "Gray 3");
	utils.addImage(gris4, "Gray 4");
	utils.addImage(gris5, "Gray 5");
	utils.addImage(gris6, "Gray 6");
	utils.addImage(gris7, "Gray 7");
	utils.addImage(filtroRojo, "Red");
	utils.addImage(filtroVerde, "Green");
	utils.addImage(filtroAzul, "Blue");
	utils.addImage(altoContraste, "High Contrast");
	utils.addImage(altoContrasteInverso, "Inverse High Contrast");
	utils.addImage(brillo, "Brightness");
	utils.addImage(mosaico, "Mosaic");

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


public static Color[][]grisCorrectud(Color[][] img) {
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
				
				int rNew = (int)((r*0.03)+(g*0.59)+(b*0.11));
				int gNew = (int)((r*0.03)+(g*0.59)+(b*0.11));
				int bNew = (int)((r*0.03)+(g*0.59)+(b*0.11));
				
				tmp[i][j] = new Color (rNew, gNew, bNew);
		}
	}
return tmp;
}


public static Color[][]gris3(Color[][] img) {
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
					
					int rNew = (int)((r*0.2126)+(g*0.7152)+(b*0.0722));
					int gNew = (int)((r*0.2126)+(g*0.7152)+(b*0.0722));
					int bNew = (int)((r*0.2126)+(g*0.7152)+(b*0.0722));
					
					tmp[i][j] = new Color (rNew, gNew, bNew);
			}
		}
	return tmp;
	}


public static Color[][]gris4(Color[][] img) {
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
						
						int rNew = (int)((r*0.299)+(g*0.587)+(b*0.114));
						int gNew = (int)((r*0.299)+(g*0.587)+(b*0.114));
						int bNew = (int)((r*0.299)+(g*0.587)+(b*0.114));
						
						tmp[i][j] = new Color (rNew, gNew, bNew);
				}
			}
		return tmp;
}



	public static Color[][] gris5(Color[][] img) {
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
								
								int rNew = (Math.max(r, Math.max(g, b)))/2;
								int gNew =(Math.max(r, Math.max(g, b)))/2;
								int bNew = (Math.max(r, Math.max(g, b)))/2;
								
								tmp[i][j] = new Color (rNew, gNew, bNew);
								
							
						}
					}
				return tmp;
	}


	public static Color[][] gris6(Color[][] img) {
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
						
						int rNew = (Math.max(r, Math.max(g, b)));
						int gNew =(Math.max(r, Math.max(g, b)));
						int bNew = (Math.max(r, Math.max(g, b)));
						
						tmp[i][j] = new Color (rNew, gNew, bNew);
				}
			}
		return tmp;
		}

		public static Color[][] gris7(Color[][] img) {
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
							
							int rNew = (Math.min(r, Math.min(g, b)));
							int gNew =(Math.min(r, Math.min(g, b)));
							int bNew = (Math.min(r, Math.min(g, b)));
							
							tmp[i][j] = new Color (rNew, gNew, bNew);
					}
				}
			return tmp;
			}


	public static Color[][] filtroRojo(Color[][] img) {
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
						
						tmp[i][j] = new Color (r, 0, 0);
				}
			}
		return tmp;
		}

		public static Color[][] filtroVerde(Color[][] img) {
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
							
							tmp[i][j] = new Color (0, g, 0);
					}
				}
			return tmp;
			}

			public static Color[][] filtroAzul(Color[][] img) {
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
								
								tmp[i][j] = new Color (0, 0, b);
						}
					}
				return tmp;
				}

	public static Color[][] altoContraste(Color[][] img) {
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
						int sum = r +b+g;
						if(sum>382){
							tmp[i][j] = new Color (255, 255, 255);
						} else {
							tmp[i][j] = new Color (0, 0, 0);
						}						
				}
			}
		return tmp;
		}

		public static Color[][] altoContrasteInverso(Color[][] img) {
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
							int sum = r +b+g;
							if(sum<382){
								tmp[i][j] = new Color (255, 255, 255);
							} else {
								tmp[i][j] = new Color (0, 0, 0);
							}						
					}
				}
			return tmp;
			}

	public static Color[][] brillo(Color[][] img) {
		//Clone matrix to not replace original
		Color[][] tmp = ImageUtils.cloneArray(img);
		
		//Runs through entire matrix
		for( int i = 0; i < tmp.length; i++)
			{
				for( int j = 0; j< tmp[i].length;j++) {
					
						int brillo;
						brillo = 30;
		//				fetches values of each pixel
						Color pixel = tmp[i][j];
						int r = pixel.getRed();
						int g = pixel.getGreen();
						int b = pixel.getBlue();

						int rNew = r+brillo>255?255:r+brillo;
						int gNew =g+brillo>255?255:g+brillo;;
						int bNew = b+brillo>255?255:b+brillo;;

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

					System.out.println("totalR: " + totalR);
					System.out.println("totalG: " + totalG);
					System.out.println("totalB: " + totalB);
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
