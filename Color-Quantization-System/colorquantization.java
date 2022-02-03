package work;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class UDS2018400225 {

	public static void main(String[] args) throws FileNotFoundException  {
		String inputFile = args[1]; 				
		int mode = Integer.parseInt(args[0]);
		File f = new File(inputFile);   //to create file inputFile
		File conv= new File("conv.ppm");	//to create file conv.ppm
		convolution();
		if(mode==0) {
			output(f);  //to call the method output
			
		}
		if(mode==1) {
			blackWhite(f,"black-and-white.ppm");		//to call the method blackWhite
		}
		if(mode==2) {
			String input1 = args[2];				
			File filter = new File(input1);			//to create file "filter.txt"
			blackWhite(f,"convolution.ppm");//to call the method blackWhite
			blackWhite(conv,"convolution.ppm");
						
		}
		File filter = new File("filter.txt");	//to create file "filter.txt"
		Scanner filt = new Scanner(filter);		//to scan filter.txt
		int m = Integer.parseInt(filt.next().substring(0,1));	//to take the rows and columns length of filter
		
		
		
	
	
	
	}
		public static File output(File f) throws FileNotFoundException {
			
			Scanner console = new Scanner(f);		//to create scanner in order to scan input file
			console.next();							//to skip 'P3'
			int x = console.nextInt();				//for taking the value of columns and rows
			int y = console.nextInt();				//for taking the value of columns and rows
			console.nextInt();						//to skip '255'
			int[][][] arr = new int[x][y][3];		//to create 3D array 
			PrintStream output = new PrintStream(new File("output.ppm")); //for filling the output.ppm 
			File outputt = new File("output.ppm") ;	//to create new file "output.ppm"
			output.println("P3\n" + 			//to create image header
					x+" "+y +"\n"+ 		
					"255");
			
			for(int i = 0 ; i<x ; i++) {
				for(int j = 0 ; j<y; j++) {
					for(int k = 0 ; k<3; k++) {			//to read the contents of input file to a 3D integer array.
						arr[i][j][k]=console.nextInt();
					}
				}
			}
			for(int i = 0 ; i<x ; i++) {
				for(int j = 0 ; j<y; j++) {
					for(int k = 0 ; k<3; k++) {		//to write the pixel values to the new PPM file by following the rules of the PPM format
						output.print(arr[i][j][k]+" ");		
							
					}
					output.print("   ");
				}
				output.println();
			}
			return outputt;
		}
		public static File blackWhite(File f,String a) throws FileNotFoundException {
			
			File filter = new File("filter.txt");				//to create file "filter.txt"
			Scanner filt = new Scanner(filter);     			//to scan "filter.txt"
			int m = Integer.parseInt(filt.next().substring(0,1)); //to take the length of filter
			Scanner console = new Scanner(f);	//to scan input file
			console.next(); 			//to skip 'P3'
			int x = console.nextInt();	//for taking the value of columns and rows
		
			int y = console.nextInt();	//for taking the value of columns and rows
			
			console.nextInt();			//to skip '255'
			int[][][] arr = new int[x][y][3];		//to create 3D array 
			for(int i = 0 ; i<x ; i++) {
				for(int j = 0 ; j<y; j++) {					//to read the contents of input file to a 3D integer array.
					for(int k = 0 ; k<3; k++) {
						arr[i][j][k]=console.nextInt();
					}
				}
			}
		for(int i = 0 ; i<x ; i++) {		
			for(int j = 0 ; j<y; j++) {
				if(arr[i][j][0]>255) {		//to change the pixel value which is larger than 255 to 255.
					arr[i][j][0]=255;
				}
				if(arr[i][j][1]>255) {		//to change the pixel value which is larger than 255 to 255.
					arr[i][j][1]=255;
				}
				if(arr[i][j][2]>255) {		//to change the pixel value which is larger than 255 to 255.
					arr[i][j][2]=255;
				}
				arr[i][j][0]=(arr[i][j][0]+arr[i][j][1]+arr[i][j][2])/3; //to calculate the color-channel average values 
																	//and substitute these values with the older values
				arr[i][j][1]=arr[i][j][0];				
				arr[i][j][2]=arr[i][j][0];
			}
		}
	
		File blackwhite = new File(a) ;
		PrintStream bw = new PrintStream(blackwhite);	//for filling the black-and-white.ppm 
		bw.println("P3\n" + 			//to create image header
				x+" "+y +"\n"+ 
				"255");
		
		for(int i = 0 ; i<x ; i++) {	
			for(int j = 0 ; j<y; j++) {				//to write the pixel values to the new PPM file by following the rules of the PPM format
				for(int k = 0 ; k<3; k++) {				
					bw.print(arr[i][j][k]+" ");
						
				}
				bw.print(" ");
			}
			bw.println();
		}
		return blackwhite;
		}
		public static void convolution() throws FileNotFoundException {
			
			File filter = new File("filter.txt");		//to create file "filter.txt"
			Scanner filt = new Scanner(filter);			//to scan "filter.txt"
			File f = new File("input.ppm");				//to create file input.ppm
			Scanner console = new Scanner(f);			//to scan input.ppm
			console.next();								//to skip 'P3'
			int x = console.nextInt();					//for taking the value of columns and rows
			int y = console.nextInt();					//for taking the value of columns and rows
			console.nextInt();							//to skip '255'
			
			int[][][] arr = new int[x][y][3];			//to create 3D array 
			for(int i = 0 ; i<x ; i++) {
				for(int j = 0 ; j<y; j++) {				//to read the contents of input file to a 3D integer array.
					for(int k = 0 ; k<3; k++) {
						arr[i][j][k]=console.nextInt();
					}
				}
			}
			
			int m = Integer.parseInt(filt.next().substring(0,1));	//to take the length of filter
			
			int[][] arr2 = new int[m][m];						//to create array for filter.txt
			int[][][] arr3 = new int[x-m/2-1][x-m/2-1][3];			//to create array for convolution
			for(int i = 0 ; i<m ; i++) {
				for(int j = 0 ; j<m; j++) {
					arr2[i][j]=filt.nextInt();					//to fill the array with filter.txt values
					
				}
			}
			PrintStream conv = new PrintStream(new File("conv.ppm"));	//for filling the conv.ppm 
			conv.println("P3\n" + 
					(x-m/2-1)+" "+(y-m/2-1) +"\n"+ 					//to create image header
					"255");
			
			int n =0;				//to start a variable
			int g= 0;				//to start a variable
			int sum = 0;			//to take the sum of channel 0
			int sum1=0;				//to take the sum of channel 1
			int sum2=0;				//to take the sum of channel 2
			if(n+m<=arr.length&&g+m<=arr.length) {  		//to transverse the array until its length
				for(g=0; g<=arr.length-m;g++) {						//to transverse the array until its length
				for(n =0 ; n<=arr.length-m;n++) {			//to transverse the array until its length
				
				for(int i = g; i<m+g; i++) {					
				for(int j = n; j<m+n; j++) {
					
			  sum+=arr[i][j][0]*arr2[(i-g)%m][(j-n)%m];				//to multiply the values of the pixels and filters
			  sum1+=arr[i][j][1]*arr2[(i-g)%m][(j-n)%m];			//to multiply the values of the pixels and filters
			  sum2+=arr[i][j][2]*arr2[(i-g)%m][(j-n)%m];			//to multiply the values of the pixels and filters
						
				}
			
				
			}
				if((sum1)<0) {
					sum1=0;		//to change the pixel value which is smaller than 0 to 0.
				}
				if((sum)<0) {
					sum=0;      //to change the pixel value which is smaller than  0 to 0.
				}
				if((sum2)<0) {
					sum2=0;    //to change the pixel value which is smaller than  0 to 0.
				}
				if((sum1)>255) {
					sum1=255;   //to change the pixel value which is larger than 255 to 255.
				}
				if((sum)>255) {
					sum=255;   //to change the pixel value which is larger than 255 to 255.
				}
				if((sum2)>255) {
					sum2=255;  //to change the pixel value which is larger than 255 to 255.
				}
					
				conv.print(sum+" "+sum1+" "+sum2+"   ");	//to write the pixel values of convolution
				 if(n==x-m) {
					conv.println();						//to pass the below line
				}
				
				sum=0;				//to refresh value
				sum1=0;				//to refresh value
				sum2=0;				//to refresh value
				
			}
				
			}
				
				}
			
			}}
