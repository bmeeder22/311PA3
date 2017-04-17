import java.awt.Color;
import java.util.ArrayList;

public class ImageProcessor {

	Picture picture = null;
	public ImageProcessor(String imageFile)
	{
		picture = new Picture(imageFile);
	}
	
	public Picture reduceWidth(double X)
	{
		int pixels = (int) Math.ceil(X*picture.width());
		//System.out.println(pixels + " " + picture.width());
		while(pixels < picture.width())
		{
			picture = reduceWidthBy1(picture);
			System.out.println("Currently at " + picture.width() +" out of " + pixels);
		}
		return picture;
	}
	
	private Picture reduceWidthBy1(Picture startPicture)
	{
		int[][] values = new int[startPicture.height()][startPicture.width()];
		//System.out.println(startPicture.height() + " " + startPicture.width());
		
		
		for(int i=0; i<startPicture.height();i++)
		{
			for(int j=0; j<startPicture.width();j++)
			{
				int Yimportance = 0;
				int Ximportance = 0;
			
				Color pixelY1 = null;
				Color pixelY2 = null;
				if(i==0)
				{
					pixelY1 = startPicture.get(j, startPicture.height()-1);
					pixelY2 = startPicture.get(j, i+1);
				}
				else if(i == startPicture.height()-1)
				{
					pixelY1 = startPicture.get(j, i-1);
					pixelY2 = startPicture.get(j, 0);
				}
				else
				{
					pixelY1 = startPicture.get(j, i-1);
					pixelY2 = startPicture.get(j, i+1);
				}
				Yimportance = getDistance(pixelY1, pixelY2);
				
				Color pixelX1 = null;
				Color pixelX2 = null;
				if(j==0)
				{
					pixelX1 = startPicture.get(startPicture.width() -1,i);
					pixelX2 = startPicture.get(j+1,i);
				}
				else if(j == startPicture.width()-1)
				{
					pixelX1 = startPicture.get(0,i);
					pixelX2 = startPicture.get(j-1,i);
				}
				else
				{
					pixelX1 = startPicture.get(j+1,i);
					pixelX2 = startPicture.get(j-1,i);
				}
				Ximportance = getDistance(pixelX1, pixelX2);
				//Finally,
				values[i][j] = Ximportance + Yimportance;
			}
		}
		//DynamicProgramming.printMatrix(values);
		
		//Now that we have the values, 
		ArrayList<Integer> pixel_Remove = DynamicProgramming.MinVC(values);
		Picture endPicture = new Picture(startPicture.width()-1,startPicture.height());
		
//				for(int i=0; i< pixel_Remove.size(); i++)
//				{
//					System.out.print("( " + pixel_Remove.get(i) + ",");
//					i++;
//					System.out.println(pixel_Remove.get(i) + " ) ");
//				}
		
				
				
		for(int i=0; i<startPicture.height();i++)
		{
			boolean found = false;
			for(int j=0; j<startPicture.width();j++)
			{

				//System.out.println("i " + i + " , j " + j);
				if(pixel_Remove.size() >0 && i == pixel_Remove.get(0) && j == pixel_Remove.get(1))
				{
					pixel_Remove.remove(0);
					pixel_Remove.remove(0);
					found = true;
					//System.out.println("Found row " + i);
				}
				else
				{
					if(found)
						endPicture.set((j-1), i, startPicture.get((j), i));
					else
					{
//						System.out.println("i = " + i + " j = " + j + " " + " pixel to be removed in col, " + pixel_Remove.get(0) + " " + pixel_Remove.get(1));
//						System.out.println("old picture width " + picture.width() + "old picture height " + picture.height());
//						System.out.println("new picture width " + endPicture.width() + "new picture height " + endPicture.height());
						endPicture.set(j, i, startPicture.get(j, i));
					}
				}
			}
		}
				
		
		
		return endPicture;
	}
	
	private int getDistance(Color pixel1, Color pixel2)
	{
		return (int)  (((pixel1.getRed() - pixel2.getRed())    * (pixel1.getRed() - pixel2.getRed())) + 
				      ((pixel1.getBlue() - pixel2.getBlue())   * (pixel1.getBlue() - pixel2.getBlue()))+ 
				      ((pixel1.getGreen() - pixel2.getGreen()) * (pixel1.getGreen() - pixel2.getGreen()))); 
	}
}
