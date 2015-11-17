

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class GainEntrCal {

	LinkedHashMap gain_map = new LinkedHashMap();
	
	
	double[] s_attr_ent= new double [1];
	
	double[] g_attr_ent= new double[6];
	AttrEntropy attrobj = new AttrEntropy();
	
	// Method to calculate gain of attributes in the for gain(s,a)
	public String[] gain_cal_attr(String[][] matrix,int row,int col_pos,int option )
	{
		//System.out.println("Inside Gain class: calculating gain for each attributes");
		CalculateEntropy cal= new CalculateEntropy();
		s_attr_ent= cal.cal_Entropy(matrix,row,col_pos-1,option);
		
			//	System.out.println("&&&&&&&&The max class value is :" +s_attr_ent[3]);
		
		// Loop to go inside every attribute
		for (int i=0;i <col_pos-1;i++)
		{
			String[][] pos_temp_matrix=new String[row][2]; // holds only atrr with value 1 in column 1
			String[][] neg_temp_matrix = new String[row][2]; // holds only atrr with value 0 in column 1
			String curr_attr=matrix[0][i];
			pos_temp_matrix[0][0]=matrix[0][i];
			pos_temp_matrix[0][1]=matrix[0][col_pos-1];
			neg_temp_matrix[0][0]=matrix[0][i];
			neg_temp_matrix[0][1]=matrix[0][col_pos-1];
			
				
		g_attr_ent=attrobj.cal_att_entropy(matrix,pos_temp_matrix,neg_temp_matrix,row,col_pos,curr_attr,i,option);
	//	System.out.println("pos(weak) and neg(strong) entropy value for attribute : "+ curr_attr +" is : " + g_attr_ent[0] +" and " +g_attr_ent[2]);
		//System.out.println("the pos total is: "+g_attr_ent[1] +"and neg total is: "+g_attr_ent[3] + " and overall total is:"+s_attr_ent[1] );
		//System.out.println("Entropy stored"+ s_attr_ent[0]);
			// Now calculate gain for each attribute XC, XF ....... and store in gain array for comparison
		
	/*	System.out.println("The gain attributes before calculating gain are");
		for(int z=0;z<g_attr_ent.length;z++)
			System.out.print(g_attr_ent[z] + "\t");
		System.out.print("\n");
	*/
			double gain=0;
			
			gain=gain_calci(s_attr_ent[0],s_attr_ent[1],g_attr_ent[0],g_attr_ent[1],g_attr_ent[2],g_attr_ent[3],curr_attr );
			
		//	System.out.println("The gain for attribute "+ curr_attr +" is:" +gain);
			
			gain_map.put(curr_attr, gain);
		
		
		
				
		}// end of for loop i
	
		//Display the gain values of all attribute
		
	/*	System.out.println("The gain values are");
		Set set = gain_map.entrySet();
	      // Get an iterator
	      Iterator i = set.iterator();
	      // Display elements
	      while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	         System.out.print(me.getKey() + ": ");
	         System.out.println(me.getValue());
	      }//end of while
	      System.out.println();*/
		
	      String max_gain [] = null;
	      max_gain = max_gain(gain_map,s_attr_ent[3]);
	      
	  //    System.out.println("The highest gain is:"+ max_gain[0] +" for attribute: "+max_gain[1] + "and its class values for left is "+ max_gain[4] + "and for right is:" + max_gain[5]+ "and its class max_value is : "+max_gain[6]);
	    
		return max_gain;
		
	} // end of method gain_cal_attr
	
	
	// method to calculate gain
	public double gain_calci(double s, double s_total, double pos_ent, double pos_tot,double neg_ent, double neg_tot,String curr_attr)
	{
		double gain=0;
		
/*		System.out.print("\n inside gain calci ,the values are : \n");
		System.out.println("S=" +s +" s_total= : "+s_total +"pos_ent = "+pos_ent +" pos_tot : "+pos_tot+" neg_ent : "+neg_ent+" neg_tot :"+neg_tot +" curr_attr: "+curr_attr);
	*/	
		gain = s -( ((pos_tot/s_total)*pos_ent) + ((neg_tot/s_total)*neg_ent));
				
		return gain;
	}
	
	// Method to compute maximum gain attribute
	
	public String[] max_gain(HashMap map,double max_class)
	{
		
		String[][] attr_ent =new String[42][4];
		String[] high =new String[7];
		double max=0.0;
		String maxKey = null;
		
		double temp_max =0.0;
		String temp_maxKey=null;
//	System.out.print("the attribute entropy list inside max_gain \n");
	int i=0;
	       Set set_attr_ent2 = attrobj.attr_entropy_list.entrySet();
	   	Iterator itr2= set_attr_ent2.iterator();
	 	
	   	while(itr2.hasNext())
	   	{
	   		Map.Entry me = (Map.Entry)itr2.next();
	          //  System.out.print(me.getKey() + ": ");
	            //System.out.println(me.getValue());
	            attr_ent[i][0]=me.getKey().toString().substring(0,2);
	            attr_ent[i][1]=me.getKey().toString().substring(3,6);
	            String temp=me.getValue().toString();
	            String[] temp1=temp.split(":");
	            attr_ent[i][2]=temp1[0];
	            attr_ent[i][3]=temp1[1];
	            		
	            		i++;
	   	} 
	   	
	   	int col_count =attr_ent[0].length;
/*	   	for(int x= 0;x<i;x++)
	   	{
	   		for(int y=0;y<attr_ent[x].length;y++)
	   		{
	   				
	   		 System.out.print(attr_ent[x][y]+" \t");
	   		}//end of y
	   		
	   		System.out.print("\n");
	   	}//end of x
	   */
	   	
	   	// Checking for two NaN values in attribute entropy
	   	for(int x= 0;x<i;x++)
	   	{
	   		for(int y=0;y<attr_ent[x].length;y++)
	   		{
	   			int z=x+1;
	   			if(x%2==0 && y==0 )
	   			{
	   				if( (attr_ent[x][0].equalsIgnoreCase( attr_ent[z][0])) && Double.isNaN(Double.parseDouble(attr_ent[x][2])) && Double.isNaN(Double.parseDouble(attr_ent[z][2])))
	   				{
	   				//	System.out.print(attr_ent[x][0] +" "+ attr_ent[z][0] +" "+ attr_ent[x][2] +" " +attr_ent[z][2]);
	   				high[0]=attr_ent[x][2]; // stores high gain value
                                        high[1]=attr_ent[x][0]; //stores high gain attribute value
                                        high[2]= "no"; // says that left node cannot be reached further
                                        high[3]= "no";// says that right node cannot be reached further 
                                        high[4]= attr_ent[z][3]; // set the max class value for left
                                        high[5]= attr_ent[x][3];  // set the max class value for right
                                        high[6]=Double.toString(max_class); //maximum class value		
                                        return high;
                                        }// end of inner if
	   				
	   			}//end of if
                                
                     
	   		}//end of y
        
                }// end of x
        
        //check for two zero value
	   	  	for(int x= 0;x<i;x++)
	   	{
	   		for(int y=0;y<attr_ent[x].length;y++)
	   		{
	   			int z=x+1;
	   			if(x%2==0 && y==0 )
	   			{
	   				if( (attr_ent[x][0].equalsIgnoreCase( attr_ent[z][0])) && (Double.parseDouble(attr_ent[x][2])==0.0) && (Double.parseDouble(attr_ent[z][2])==0.0))
	   				{
	   				//	System.out.print(attr_ent[x][0] +" "+ attr_ent[z][0] +" "+ attr_ent[x][2] +" " +attr_ent[z][2]);
	   				high[0]=attr_ent[x][2]; // stores high gain value
                                        high[1]=attr_ent[x][0]; //stores high gain attribute value
                                        high[2]= "no"; // says that left node cannot be reached further
                                        high[3]= "no";// says that right node cannot be reached further 
                                        high[4]= attr_ent[z][3]; // set the max class value for left
                                        high[5]= attr_ent[x][3];  // set the max class value for right
                                        high[6]=Double.toString(max_class); //maximum class value		
                                        return high;
                                        }// end of inner if
	   				
	   			}//end of if
                 
	   		}//end of y
			}// end of x
	   	  	
	   	  	
//check for one zero value
			
			
			Set set1 = attrobj.attr_entropy_list.entrySet();
	      // Get an iterator
	      Iterator ii1 = set1.iterator();
	      // Iterate attribute attribute gain map
	      while(ii1.hasNext())
	      {
	    	  Map.Entry me = (Map.Entry)ii1.next();
	      	String tempi=me.getValue().toString();
	            String[] tempj=tempi.split(":");
	        double temp_attr_value1= Double.parseDouble(tempj[0]);
	         if ( temp_attr_value1==0.0)
	         {
                     high[2]="yes";
                     
		      high[3]="yes";
	        	String key=me.getKey().toString();
			 Set set_attr_ent1 = attrobj.attr_entropy_list.entrySet();
	      	   	Iterator itr1= set_attr_ent1.iterator();
	      	  
	      	   	// Iterate attribute entropy list when gain is NaN	      	   	
	      	   	while(itr1.hasNext())
	      	   	{
	      	   		Map.Entry me_attr = (Map.Entry)itr1.next();
	      	   	String temp2=me_attr.getValue().toString();
	            String[] temp3=temp2.split(":");
	            
	      	   		double temp_attr_value= Double.parseDouble(temp3[0]);
	      	   		String class_val =temp3[1];
	      	   		if (Double.isNaN(temp_attr_value)||temp_attr_value ==0.0)
	      	   		{
	      	   			temp_max=temp_attr_value;
	      	   			temp_maxKey=me_attr.getKey().toString().substring( 0, 2);
	      	   			break;
	      	   		}// end of if
	      	   	} 
	        	// end of att_ent_list while
	      	  high[0]=String.valueOf(temp_max);
		      high[1]=temp_maxKey;
		      
		      // checking with atribute's positive or negative value has NaN
		      String attr_nature=null;
		      String attr_class=null;
                      
		  	for(int  x= 0;x<attr_ent.length;x++)
		   	{
		   	
		   				
		   		 if((attr_ent[x][2].equalsIgnoreCase("NaN") && attr_ent[x][0].equalsIgnoreCase(temp_maxKey))||(attr_ent[x][2].equalsIgnoreCase("0.0") && attr_ent[x][0].equalsIgnoreCase(temp_maxKey)))
		   		 {
                                      attr_nature=attr_ent[x][1];
                                      attr_class=attr_ent[x][3];
		   			 break;
		   		 }
		   		
		    	}//end of x
                        
                       //    System.out.print(" the root attribute "+ high[1]+" for which it is nan with nature : "+attr_nature+ " and class value:"+attr_class+"\n");
		   		
                          if(attr_nature.equalsIgnoreCase("pos"))
                          {
		   		high[3]="no";
		   		high[4]="-1";
		   			high[5]=attr_class;
		   		 high[6]=Double.toString(max_class);
                          }
		   	else if(attr_nature.equalsIgnoreCase("neg"))
		   		{
		   		high[2]="no";
		   		high[5]="-1";
	   			high[4]=attr_class;
	   		 high[6]=Double.toString(max_class);
	   			
		   		}
		      return high;
	         }// end of if
			}
			// end of while ii1
			
	   	  	
	   	  	
	   	  	
	   	  	
	   	  	
	   	// Check for one NaN value
	   	Set set = map.entrySet();
	      // Get an iterator
	      Iterator ii = set.iterator();
	      // Iterate attribute gain map
	      while(ii.hasNext())
	      {
	    	  Map.Entry me = (Map.Entry)ii.next();
	      //   System.out.print(me.getKey() + ": ");
	        // System.out.println(me.getValue());
	         double temp= (Double)me.getValue();
	         if ( Double.isNaN(temp))
	         {
                     high[2]="yes";
                     
		      high[3]="yes";
	        	String key=me.getKey().toString();
	        	
	        	  Set set_attr_ent1 = attrobj.attr_entropy_list.entrySet();
	      	   	Iterator itr1= set_attr_ent1.iterator();
	      	  
	      	   	// Iterate attribute entropy list when gain is NaN	      	   	
	      	   	while(itr1.hasNext())
	      	   	{
	      	   		Map.Entry me_attr = (Map.Entry)itr1.next();
	      	   	String temp2=me_attr.getValue().toString();
	            String[] temp3=temp2.split(":");
	            
	      	   		double temp_attr_value= Double.parseDouble(temp3[0]);
	      	   		String class_val =temp3[1];
	      	   		if (Double.isNaN(temp_attr_value)||temp_attr_value ==0.0)
	      	   		{
	      	   			temp_max=temp_attr_value;
	      	   			temp_maxKey=me_attr.getKey().toString().substring( 0, 2);
	      	   			break;
	      	   		}// end of if
	      	   	} 
	        	// end of att_ent_list while
	      	  high[0]=String.valueOf(temp_max);
		      high[1]=temp_maxKey;
		      
		      // checking with atribute's positive or negative value has NaN
		      String attr_nature=null;
		      String attr_class=null;
                      
		  	for(int  x= 0;x<attr_ent.length;x++)
		   	{
		   	
		   				
		  		 if(attr_ent[x][2].equalsIgnoreCase("NaN") && attr_ent[x][0].equalsIgnoreCase(temp_maxKey))
		   		 {
                                      attr_nature=attr_ent[x][1];
                                      attr_class=attr_ent[x][3];
		   			 break;
		   		 }
		   		
		    	}//end of x
                        
                    //       System.out.print(" the root attribute "+ high[1]+" for which it is nan with nature : "+attr_nature+ " and class value:"+attr_class+"\n");
		   		
                          if(attr_nature.equalsIgnoreCase("pos"))
                          {
		   		high[3]="no";
		   		high[4]="-1";
		   			high[5]=attr_class;
		   		 high[6]=Double.toString(max_class);
                          }
		   	else if(attr_nature.equalsIgnoreCase("neg"))
		   		{
		   		high[2]="no";
		   		high[5]="-1";
	   			high[4]=attr_class;
	   		 high[6]=Double.toString(max_class);
	   			
		   		}
		      return high;
	         }
	         else if (max < temp )
	         { 
	        	 max=temp;
	         	maxKey = me.getKey().toString();
	         	}
	         
	      }// end of while
		
	      high[0]=String.valueOf(max);
	      high[1]=maxKey;
	      high[2]="yes";
              high[3]="yes";
              high[4]="-1";
              high[5]="-1";
              high[6]=Double.toString(max_class);
		return high;
		
	} 
	// end of max_gain method
	
}// end of class
