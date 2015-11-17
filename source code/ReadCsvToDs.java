

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCsvToDs {
	
	String line=null;
	final String DELIMETER=",";
	int row_count,col_count =0;
	String [][] FileToMatrix =null;
	// Method  to convert csv to matrix
	public void convertCsvToMatrix(String target_file)throws IOException
		
	{	BufferedReader fileReader = new BufferedReader(new FileReader(target_file));
			
	
	//  Counting number of rows and columns in training set file
				while((line=fileReader.readLine())!=null)
				{
					row_count++;
					String [] row_data= line.split(DELIMETER);
					col_count=row_data.length;
				}
			//	System.out.println("No of rows is:"+row_count);
			//	System.out.println("No of columns is:"+col_count);
				
				fileReader.close();
				// Store CSV to Matrix
				 FileToMatrix = new String[row_count][col_count];
				BufferedReader fileReader1 = new BufferedReader(new FileReader(target_file));
				int i=0;
				int j=0;
				while((line=fileReader1.readLine())!=null)
				{
					String[] tokens=line.split(DELIMETER);
				
					j=0;
					for (int x=0;x<tokens.length;x++)
						{
						FileToMatrix[i][j]=tokens[x];
						j++;
						}
					i++;	
					
				}
				
				fileReader1.close();
				
			
				
		} // End of function 
		
	
	
	
	
	public void printMatrix()
	{
		
		for(int ii=0;ii<row_count;ii++)
		{
			for(int jj=0;jj<col_count;jj++)
		{
			System.out.print(FileToMatrix[ii][jj]+"\t");
		}
			System.out.print("\n");
	}
		
	}// End of Printmatrix function

}// end of class
