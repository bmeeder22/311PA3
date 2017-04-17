import java.util.ArrayList;

public class DynamicProgramming {

	public static void printMatrix(int[][] M)
	{
		for(int i=0; i<M.length;i++)
		{
			for(int j=0; j<M[0].length;j++)
			{
				System.out.print(M[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static ArrayList<Integer> MinVC(int[][] M)
	{
		ArrayList<Integer> coords = new ArrayList<Integer>();

		//Compute Weighted Paths
		for(int i=M.length-2; i>=0 ;i--) {
			for(int j=0; j<M[0].length;j++)
			{
				int down = M[i+1][j];
				int left =  down+1;
				int right = down+1;
				
				if(j>0)
					left = M[i+1][j-1];
				if(j<M[0].length-1)
					right = M[i+1][j+1];
				
				
				M[i][j] += Math.min(Math.min(left, down), right); //Select the lowest path out of the last three. 
			}
//			System.out.println();
//			printMatrix(M);
//			System.out.println();
		}
		
		
		//Find the lowest path starting position
		int startCol = 0;
		System.out.println(M[0].length);
		for(int i=0; i<M[0].length;i++) {
			if(M[0][startCol] >= M[0][i])
				startCol = i;
		}
		
		
		//Now Compute the path down.
		for(int i=0; i<M.length-1;i++) {
			coords.add(i); //Add X
			coords.add(startCol); //Add Y
			
			int down = M[i+1][startCol];
			int left =  down+1;
			int right = down+1;
			
			if(startCol>0)
				left = M[i+1][startCol-1];
			if(startCol<M[0].length-1)
				right = M[i+1][startCol+1];
			
			
			int min = Math.min(Math.min(left,down),right);
			
			if(left == min)
				startCol --;
			else if(right == min)
				startCol++;
			//Otherwise, Column stays same. 
		}

		coords.add(M.length-1); //Add X
		coords.add(startCol); //Add Y
		return coords;
	}
}
