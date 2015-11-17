
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

// class to calculate entropy of the every attributes
public class AttrEntropy {
	
	public LinkedHashMap attr_entropy_list= new LinkedHashMap();
	CalculateEntropy cal= new CalculateEntropy();
	double[] pos_ent= new double[4];
	double[] neg_ent= new double[4];
	
	
	
	public double[] cal_att_entropy(String[][] matrix, String[][] pos_temp_matrix, String[][] neg_temp_matrix,int row,int col, String curr_attr,int i, int option )
	{
			double[] attr_ent = new double[10];	
			int x=1,y=1; 
				
			// Loop to copy postive and negative values to pos and neg matrix
			for (int j=1;j<row;j++)
				{
			
					if((Integer.parseInt(matrix[j][i])== 1))
						{
			
							pos_temp_matrix[x][0]=matrix[j][i];
							pos_temp_matrix[x][1]=matrix[j][col-1];
							x++;
						}
					else
						{
							neg_temp_matrix[y][0]=matrix[j][i];
							neg_temp_matrix[y][1]=matrix[j][col-1];
							y++;
			
						}// end of else
			
				
				}// end of for j
				
		
			pos_ent =cal.cal_Entropy(pos_temp_matrix,x,1,option);
			attr_ent[0]=pos_ent[0];// store weak entropy
			attr_ent[1]=pos_ent[1]; // store weak total positive
			
		 // store weak total negative
			attr_entropy_list.put(curr_attr+":pos",pos_ent[0]+":"+pos_ent[2]+":"+pos_ent[3]);
			neg_ent=cal.cal_Entropy(neg_temp_matrix,y,1,option);
			attr_entropy_list.put(curr_attr+":neg",neg_ent[0]+":"+neg_ent[2]+":"+neg_ent[3]);
			attr_ent[2]=neg_ent[0]; // store strong entropy
			attr_ent[3]=neg_ent[1]; // store strong total positive
		// store strong total negative
		
	return attr_ent;
	
	}// end of cal_att_entropy method

}// end of attrentropy class
