

import java.util.LinkedHashMap;


public class CalculateEntropy  
	{
		static int stage_count=0;
		double result[]= new double[4];
		int tot_pos,tot_neg=0;
		String[] entropy_of_S = new String[3];
		public static LinkedHashMap entropy_list= new LinkedHashMap();
		@SuppressWarnings("unchecked")
	
	// Method to calculate Entropy of matrix given and store in LinkedHashMap
	
		public double[] cal_Entropy(String[][] matrix,int row,int col_pos,int option )
			{
				stage_count++;
				int pos_count=0;
				int neg_count=0;
		
				for(int i=1;i<row;i++)
					{
						if(Integer.parseInt(matrix[i][col_pos])==1)
						tot_pos++;
					}
				for(int i=1;i<row;i++)
					{
						if(Integer.parseInt(matrix[i][col_pos])==0)
						tot_neg++;
					}
	
				String[] ent_value=entropy_of_s(tot_pos,tot_neg,row-1,option);
				// Adding entropy value to linked hashmap
				entropy_list.put(matrix[0][0], ent_value);
				// System.out.println("Entropy of S{"+matrix[0][col_pos]+"}  is:"+ent_value[0]+ " and its class value is:"+ ent_value[1]);
				result[0]=Double.parseDouble(ent_value[0]);
				result[1]=row-1;
				result[2]=Integer.parseInt(ent_value[1]);
				if(tot_pos==tot_neg)
					result[3]=Integer.parseInt("1");
				else if(tot_pos>tot_neg)
					result[3]=Integer.parseInt("1");
				else
					result[3]=Integer.parseInt("0");
				tot_pos=tot_neg=0; 
				// clearing the total postibe and negative values of previous matrix
				return result;
			}// End of cal_Entropy function
	
	// Method to caluclate entropy of S
		public String[] entropy_of_s( int pos,int neg,int tot,int option)
			{
				if(option==1)
					{
                        float pvalue=(float)pos/(float)tot;
						double logOfPos=cal_log(pvalue,2);
						float nvalue=(float)neg/(float)tot;
						double logOfNeg=cal_log(nvalue,2);
						entropy_of_S[0]= String.valueOf(-pvalue*logOfPos-nvalue*logOfNeg);	
						if(tot==pos)
							entropy_of_S[1]="1";
						else if(tot==neg)
							entropy_of_S[1]="0";
						else entropy_of_S[1]="-1";
						return entropy_of_S;
					}
				else 
					{
                        float pvalue=(float)pos/(float)tot;
						float nvalue=(float)neg/(float)tot;
						float result=pvalue*nvalue;
						entropy_of_S[0]= String.valueOf(pvalue*nvalue);	
						if(tot==pos)
							entropy_of_S[1]="1";
						else if(tot==neg)
							entropy_of_S[1]="0";
						else entropy_of_S[1]="-1";
		
						return entropy_of_S;
					}
                
			}// end of entropy_of_s
	
		// method to calculate log to the base 2 
		public double cal_log(float a, float b)
			{
			return Math.log(a) / Math.log(b);
			}
	
	}// end of class
