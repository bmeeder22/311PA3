import java.util.ArrayList;

public class Tester {

	
	public static void main(String args[]) {
////		//Test for the min cost
//				int[][] M = { {7,3,2,1},
//							  {1,8,9,5},
//							  {3,4,7,8},
//							  {9,2,1,7},
//							  {1,4,3,8}};
//				
//				ArrayList<Integer> coords = DynamicProgramming.minCostVC(M);
//				
//				for(int i=0; i< coords.size(); i++)
//				{
//					System.out.print("( " + coords.get(i) + ",");
//					i++;
//					System.out.println(coords.get(i) + " ) ");
//				}
//		
//		ImageProcessor ip = new ImageProcessor("arch.jpg");
//		ip.picture.show();
//		Picture newPic = ip.reduceWidth(.726);
//		newPic.show();

        System.out.println(DynamicProgramming.stringAlignment("try_this", "test"));
        System.out.println(DynamicProgramming.stringAlignment("acdtest", "test"));
        System.out.println(DynamicProgramming.stringAlignment("atestb", "test"));
        System.out.println(DynamicProgramming.stringAlignment("abcbad", "acda"));
    }
}
