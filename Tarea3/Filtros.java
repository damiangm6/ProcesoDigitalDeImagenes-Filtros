

import java.awt.Color;
import java.util.Scanner;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter; 
import java.io.FileWriter; 
import java.io.PrintWriter;

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
	// filePath = "flor.jpeg";
	// filePath = "lapras.png";

	
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

//	Filtro Componentes RGB

Color [][] filtroComponentesRGB = filtroComponentesRGB(orig);

//	Filtro Letra con Colores


	
	
	// utils.addImage(grisPromedio, "Gray 1");

	// utils.addImage(filtroComponentesRGB, "Filtro RGB");
	// utils.addImage(filtroLetraConColores, "Filtro Letra con Colores");

//	Display images
	utils.display();
	try {
		filtroLetraConColores(orig);
		filtroLetraEnBlancoColor(orig);
		filtroLetraEnBlanco(orig);
		filtroLetraConGrises(orig);

		filtroPalabraConColores(orig);
		filtroNaipes(orig);
		filtroDomino(orig);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
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
	x=30;
	y=30;
	
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

public static void filtroLetraConColores (Color[][] img)throws IOException{

	Color[][] tmp = ImageUtils.cloneArray(img);
	int x;
	int y;
	x=10;
	y=10;

	File myObj = new File("filtroLetraConColores.html");
	myObj.delete();
	File myObj2 = new File("filtroLetraConColores.html");
	myObj2.createNewFile();

	FileWriter fw = null;
	 BufferedWriter bw = null;
	  PrintWriter pw = null;
	   try {
		    fw = new FileWriter("filtroLetraConColores.html", true);
			 bw = new BufferedWriter(fw);
			 pw = new PrintWriter(bw);
			 pw.println("<FONT SIZE=.05 COLOR=#000008><PRE><title>filtroLetraConColores hecha por Gerardo García</title>");

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
								pw.print("<font color ="+hex+">M");
					 
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
				File myObj3 = new File("filtroLetraConColores.html"); 
				Desktop.getDesktop().browse(myObj3.toURI());

}



public static void filtroLetraConGrises (Color[][] img)throws IOException{

	Color[][] tmp = ImageUtils.cloneArray(img);
	tmp = grisPromedio(tmp);
	int x;
	int y;
	x=10;
	y=10;

	File myObj = new File("filtroLetraConGrises.html");
	myObj.delete();
	File myObj2 = new File("filtroLetraConGrises.html");
	myObj2.createNewFile();

	FileWriter fw = null;
	 BufferedWriter bw = null;
	  PrintWriter pw = null;
	   try {
		    fw = new FileWriter("filtroLetraConGrises.html", true);
			 bw = new BufferedWriter(fw);
			 pw = new PrintWriter(bw);
			 pw.println("<FONT SIZE=.05 COLOR=#000008><PRE><title>filtroLetraConGrises hecha por Gerardo García</title>");

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
								pw.print("<font color ="+hex+">M");
					 
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
				File myObj3 = new File("filtroLetraConGrises.html"); 
				Desktop.getDesktop().browse(myObj3.toURI());

}



public static void filtroLetraEnBlanco (Color[][] img)throws IOException{

	Color[][] tmp = ImageUtils.cloneArray(img);
	tmp = grisPromedio(tmp);
	int x;
	int y;
	x=10;
	y=10;

	File myObj = new File("filtroLetraEnBlanco.html");
	myObj.delete();
	File myObj2 = new File("filtroLetraEnBlanco.html");
	myObj2.createNewFile();

	FileWriter fw = null;
	 BufferedWriter bw = null;
	  PrintWriter pw = null;
	   try {
		    fw = new FileWriter("filtroLetraEnBlanco.html", true);
			 bw = new BufferedWriter(fw);
			 pw = new PrintWriter(bw);
			 pw.println("<FONT SIZE=.05 COLOR=#000008><PRE><title>filtroLetraEnBlanco hecha por Gerardo García</title>");

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
								char letter = changeColorToLetter(tmp[j][i].getRed(), "filtroLetraEnBlanco");
								pw.print("<font color ="+hex+">"+letter);
					 
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
				File myObj3 = new File("filtroLetraEnBlanco.html"); 
				Desktop.getDesktop().browse(myObj3.toURI());

}



public static void filtroLetraEnBlancoColor (Color[][] img)throws IOException{

	Color[][] tmp = ImageUtils.cloneArray(img);
	int x;
	int y;
	x=10;
	y=10;

	File myObj = new File("filtroLetraEnBlancoColor.html");
	myObj.delete();
	File myObj2 = new File("filtroLetraEnBlancoColor.html");
	myObj2.createNewFile();

	FileWriter fw = null;
	 BufferedWriter bw = null;
	  PrintWriter pw = null;
	   try {
		    fw = new FileWriter("filtroLetraEnBlancoColor.html", true);
			 bw = new BufferedWriter(fw);
			 pw = new PrintWriter(bw);
			 pw.println("<FONT SIZE=.2 COLOR=#000008><PRE><title>filtroLetraEnBlancoColor hecha por Gerardo García</title>");

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
								char letter = changeColorToLetter(tmp[j][i].getRed(), "filtroLetraEnBlancoColor");
								pw.print("<font color ="+hex+">"+letter);
					 
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
				File myObj3 = new File("filtroLetraEnBlancoColor.html"); 
				Desktop.getDesktop().browse(myObj3.toURI());

}




public static void filtroPalabraConColores (Color[][] img)throws IOException{

	Color[][] tmp = ImageUtils.cloneArray(img);
	System.out.println("tmp.length: " + tmp.length);
	System.out.println("tmp{}.length: " + tmp[0].length);
	int x;
	int y;
	String palabraFiltro="Proceso Digital de Imagenes";
	int contadorPalabra=0;
	x=10;
	y=10;

	File myObj = new File("filtroPalabraConColores.html");
	myObj.delete();
	File myObj2 = new File("filtroPalabraConColores.html");
	myObj2.createNewFile();

	FileWriter fw = null;
	 BufferedWriter bw = null;
	  PrintWriter pw = null;
	   try {
		    fw = new FileWriter("filtroPalabraConColores.html", true);
			 bw = new BufferedWriter(fw);
			 pw = new PrintWriter(bw);
			 pw.println("<FONT SIZE=.05 COLOR=#000008><PRE><title>filtroPalabraConColores hecha por Gerardo García</title>");

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
								pw.print("<font color ="+hex+">"+palabraFiltro.charAt(contadorPalabra));
								contadorPalabra=contadorPalabra+1>=palabraFiltro.length()?0:contadorPalabra+1;
					 
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
				File myObj3 = new File("filtroPalabraConColores.html"); 
				Desktop.getDesktop().browse(myObj3.toURI());

}




public static void filtroNaipes (Color[][] img)throws IOException{

	Color[][] tmp = ImageUtils.cloneArray(img);
	tmp = grisPromedio(tmp);
	int x;
	int y;
	x=10;
	y=10;

	File myObj = new File("filtroNaipes.html");
	myObj.delete();
	File myObj2 = new File("filtroNaipes.html");
	myObj2.createNewFile();

	FileWriter fw = null;
	 BufferedWriter bw = null;
	  PrintWriter pw = null;
	   try {
		    fw = new FileWriter("filtroNaipes.html", true);
			 bw = new BufferedWriter(fw);
			 pw = new PrintWriter(bw);
			 pw.println("<FONT SIZE=.05 COLOR=#000008><PRE><title>filtroNaipes hecha por Gerardo García</title>");

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
								char letter = changeColorToLetter(tmp[j][i].getRed(), "filtroNaipes");
								pw.print("<font color ="+hex+">"+letter);
					 
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
				File myObj3 = new File("filtroNaipes.html"); 
				Desktop.getDesktop().browse(myObj3.toURI());

}


public static void filtroDomino (Color[][] img)throws IOException{

	Color[][] tmp = ImageUtils.cloneArray(img);
	tmp = grisPromedio(tmp);
	int x;
	int y;
	x=10;
	y=10;

	File myObj = new File("filtroDomino.html");
	myObj.delete();
	File myObj2 = new File("filtroDomino.html");
	myObj2.createNewFile();

	FileWriter fw = null;
	 BufferedWriter bw = null;
	  PrintWriter pw = null;
	   try {
		    fw = new FileWriter("filtroDomino.html", true);
			 bw = new BufferedWriter(fw);
			 pw = new PrintWriter(bw);
			 pw.println("<FONT SIZE=.05 COLOR=#000008><PRE><title>filtroDomino hecha por Gerardo García</title>");

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
								char letter = changeColorToLetter(tmp[j][i].getRed(), "filtroDomino");
								pw.print("<font color ="+hex+">"+letter);
					 
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
				File myObj3 = new File("filtroDomino.html"); 
				Desktop.getDesktop().browse(myObj3.toURI());

}










public static char changeColorToLetter(int RGB, String filtro){
	switch(filtro){
		case "filtroLetraEnBlanco":
		if (isBetween(RGB, 0, 15)) {
			return 'M';
		  } else if (isBetween(RGB, 16, 31)) {
			  return 'N';
		  } else if (isBetween(RGB, 32, 47)) {
			return 'H';
		} else if (isBetween(RGB, 48, 63)) {
			return '#';
		} else if (isBetween(RGB, 64, 79)) {
			return 'Q';
		} else if (isBetween(RGB, 80, 95)) {
			return 'U';
		} else if (isBetween(RGB, 96, 111)) {
			return 'A';
		} else if (isBetween(RGB, 112, 127)) {
			return 'D';
		} else if (isBetween(RGB, 128, 143)) {
			return '0';
		} else if (isBetween(RGB, 144, 159)) {
			return 'Y';
		} else if (isBetween(RGB, 160, 175)) {
			return '2';
		} else if (isBetween(RGB, 176, 191)) {
			return '$';
		} else if (isBetween(RGB, 192, 209)) {
			return '%';
		} else if (isBetween(RGB, 210, 225)) {
			return '+';
		} else if (isBetween(RGB, 226, 239)) {
			return '.';
		} else if (isBetween(RGB, 240, 255)) {
			return ' ';
		}
		break;

		case "filtroNaipes":
		if (isBetween(RGB, 0, 20)) {
			return 'M';
		  } else if (isBetween(RGB, 21, 40)) {
			  return 'L';
		  } else if (isBetween(RGB, 41, 60)) {
			return 'K';
		} else if (isBetween(RGB, 61, 80)) {
			return 'J';
		} else if (isBetween(RGB, 81, 100)) {
			return 'I';
		} else if (isBetween(RGB, 101, 120)) {
			return 'H';
		} else if (isBetween(RGB, 120, 140)) {
			return 'G';
		} else if (isBetween(RGB, 141, 160)) {
			return 'F';
		} else if (isBetween(RGB, 161, 180)) {
			return 'E';
		} else if (isBetween(RGB, 181, 200)) {
			return 'D';
		} else if (isBetween(RGB, 201, 220)) {
			return 'C';
		} else if (isBetween(RGB, 221, 240)) {
			return 'B';
		} else if (isBetween(RGB, 241, 255)) {
			return 'A';
		}
		break;

		case "filtroDomino":
		if (isBetween(RGB, 0, 37)) {
			return '0';
		  } else if (isBetween(RGB, 38, 73)) {
			  return '1';
		  } else if (isBetween(RGB, 74, 109)) {
			return '2';
		} else if (isBetween(RGB, 110, 145)) {
			return '3';
		} else if (isBetween(RGB, 146, 181)) {
			return '4';
		} else if (isBetween(RGB, 182, 217)) {
			return '5';
		} else if (isBetween(RGB, 218, 255)) {
			return '6';
		}
		break;
	}
	return 0;
}


public static boolean isBetween(int x, int lower, int upper) {
	return lower <= x && x <= upper;
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
