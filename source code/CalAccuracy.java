
import java.io.IOException;


//class to calculate accuracy of the tree
public class CalAccuracy extends ConstructTree {

	// method to calculate accuracy of the tree with tree root node as argument
	public float cal_tree_accuracy(Node root, String FileName) throws IOException
		{
			ReadCsvToDs obj1 = new ReadCsvToDs();
			String csvfile=FileName;
			obj1.convertCsvToMatrix(csvfile);
			int count=0; // counter to hold how many records are correct
				for(int i=1;i<obj1.FileToMatrix.length;i++)
					{
						Node currentnode =root;
						String root_Mvalue ="-1";
						while(currentnode!=null) 
							{
								int root_nodeIndex=findIndex(currentnode.getData(),obj1.FileToMatrix );
								root_Mvalue= obj1.FileToMatrix[i][root_nodeIndex];
								if((root_Mvalue.equals("0")) && (currentnode.getreachLeft().equals("yes")))
									currentnode=currentnode.getLeft();
								else if((root_Mvalue.equals("1")) && (currentnode.getreachRight().equals("yes")))
									currentnode=currentnode.getRight();
								else
									break;
	
							}// end of while
			
						if(root_Mvalue.equals("0"))
							{	
				
								if(obj1.FileToMatrix[i][obj1.col_count-1].equalsIgnoreCase(Integer.toString(currentnode.getResultLeftt())))
									count++;
							}			
						else if(root_Mvalue.equals("1"))
							{
				
								if(obj1.FileToMatrix[i][obj1.col_count-1].equalsIgnoreCase(Integer.toString(currentnode.getResultRight())))
								count++;
					
							}
					}// end of for
	
				int total=obj1.FileToMatrix.length-1;
				float accuracy =(float)(count*100)/(float)(total-1);
				return accuracy;
		}
	// end of cal_tree_accuracy method
	
	
	
	// Method to get the index of the node in the test data matrix
	public int findIndex(String col, String[][] data_matrix)
		{
		
			int col_pos=-1;
			for(int i=0;i<data_matrix[0].length;i++)
				{
					col_pos++;
					if(col.equals(data_matrix[0][i]))
					return col_pos;
				}
			return -1;
		
		}// end of method findIndex
	
	
	
		}// end of class
