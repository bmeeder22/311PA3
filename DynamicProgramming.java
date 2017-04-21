import java.util.ArrayList;

public class DynamicProgramming {

    private static String placeholder = "$";
    private static int placeholderScore = 4;

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
		for(int i=M.length-2; i>=0 ;i--)
		{
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
		//System.out.println(M[0].length);
		for(int i=0; i<M[0].length;i++)
		{
			if(M[0][startCol] >= M[0][i])
				startCol = i;
		}
		
		
		//Now Compute the path down.
		for(int i=0; i<M.length-1;i++)
		{
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

	public static String stringAlignment(String x, String y) {
        int[][] scoreMatrix = getScoreMatrix(x,y);
        int[][] D = initializeMatrix(x, y);

        for(int i = 1; i<D.length; i++) {
            for(int j=1; j<D[0].length; j++) {
                //D[i,j] = max { D[i-1,j-1]+ sim_mat[s[j],t[i]], D[i-1,j] + gap_score, D[i,j-1] + gap_score };
                D[i][j] = Math.min(D[i-1][j-1] + scoreMatrix[i-1][j-1], Math.min(D[i-1][j] + placeholderScore, D[i][j-1] + placeholderScore));
            }
        }

        int row = y.length();
        int col = x.length();
        String out = "";
        
        while(row > 0 && col > 0) {
            int diag = (D[row-1][col-1] + scoreMatrix[row-1][col-1]);
            int vert = D[row-1][col] + placeholderScore;
            int hor = D[row][col-1] + placeholderScore;

            int current = D[row][col];
            if(D[row][col] - scoreMatrix[row-1][col-1] == D[row-1][col-1]) {
                out = y.charAt(row-1) + out;
                row--;
                col--;
            }
            else if(current == vert) {
                row--;
                out = placeholder + out;
            }
            else if(current == hor) {
                col--;
                out = placeholder + out;
            }
        }

        if(row == 0 && col !=0) {
            while(col != 0) {
                out = placeholder + out;
                col--;
            }
        }

        return out;
    }

    private static int[][] getScoreMatrix(String x, String y) {
        int[][] matrix = new int[y.length()][x.length()];
        for(int i=0; i<x.length(); i++) {
            for(int j=0; j<y.length(); j++) {
                matrix[j][i] = penalty(x.charAt(i),y.charAt(j));
            }
        }
        return matrix;
    }

    private static int[][] initializeMatrix(String x, String y) {
        int[][] matrix = new int[y.length()+1][x.length()+1];
        matrix[0][0] = 0;
        for(int i = 1; i<x.length()+1; i++) {
            matrix[0][i] = matrix[0][i-1] + placeholderScore;
        }
        for(int i = 1; i<y.length()+1; i++) {
            matrix[i][0] = matrix[i-1][0] + placeholderScore;
        }
        return matrix;
    }

    private static int penalty(char x, char y) {
        if(x == y) return 0;
        else if(x == '$' || y == '$') return placeholderScore;
        else return 2;
    }
}
