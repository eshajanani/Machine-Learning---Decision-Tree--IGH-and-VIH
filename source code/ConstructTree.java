import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class ConstructTree 
{
	@SuppressWarnings("rawtypes")
	List subtrees = new ArrayList();
	public static LinkedHashMap<String,List> Dtree = new LinkedHashMap<String,List> ();
	String [] root = null;
	int L_val,K_val;
	String val_filename,test_filename,to_printval;
        int opt;
	// this is node used in tree
public static class Node
	{
		private String data;
		private Node left;
		private Node right;
		private  int dataLeft;
		private int dataRight;
		private String reach_left;
		private String reach_right;
		public int[] result = new int[2];
		public int node_no;
		public String max_value;
		//Null contructor
		
		// Copy Constructor for copying the original tree to another tree
		
		public Node(Node orgNode)
		{
		//	System.out.println("calling copy constructor");
			this.data=orgNode.data;
			this.dataLeft=0;
			this.dataRight=1;
			this.reach_left= orgNode.reach_left;
			this.reach_right=orgNode.reach_right;
			this.result[0]=orgNode.result[0];
            this.result[1]=orgNode.result[1];
			left=orgNode.left;
			right=orgNode.right;
			this.node_no=123;
			this.max_value=orgNode.max_value;
		}
		// use this constructor when no NaN nodes are created
				public Node(String data, String reach_left, String reach_right, String max_value)
				{
				//	System.out.println("calling no NAN constructor");
					this.data=data;
					this.dataLeft=0;
					this.dataRight=1;
					this.reach_left= reach_left;
					this.reach_right=reach_right;
					this.max_value=max_value;
				//	this.result=result;
					left=null;
					right=null;
					
				}// end of constructore Node
				// use this constructor when there is one NaN nodes are created
				public Node(String data, String reach_left, String reach_right, int result,String max_value)
				{
				//	System.out.println("calling one NAN constructor");
					this.data=data;
					this.dataLeft=0;
					this.dataRight=1;
					this.reach_left= reach_left;
					this.reach_right=reach_right;
					this.result[0]=result;
					this.max_value=max_value;
					left=null;
					right=null;
					
				}// end of constructore Node
				
		                
		                // use this constructor when there is two NaN nodes are created
		                public Node(String data, String reach_left, String reach_right, int result1, int result2,String max_value)
				{
		               // 	System.out.println("calling two NAN constructor");
					this.data=data;
					this.dataLeft=0;
					this.dataRight=1;
					this.reach_left= reach_left;
					this.reach_right=reach_right;
					this.result[0]=result1;
		            this.result[1]=result2;
		            this.max_value=max_value;
					left=null;
					right=null;
					
				}// end of constructore Node
		              
		                
		                public void setMaxValue(String value)
		                {
		                	this.max_value=value;
		                }
		                
		                
		                public String getMaxValue()
		                {
		                	return this.max_value;
		                }
				public void setNodeNo(int node_no)
				{
					this.node_no=node_no;
				}
		
				public int getNodeNo()
				{
					return this.node_no;
				}
		// function to set left node
		public void setLeft(Node left){
            this.left = left;
        }
        public void setRight(Node right){
            this.right = right;
        }
        public Node getLeft(){
            return this.left;
        }       
        public Node getRight(){
            return this.right;
        }
        
        public void setData(String data)
        {
        	this.data=data;
        }
        public String getData(){
            return this.data;
        }
       
        public void setreachLeft(String value){
            this.reach_left = value;
        }
        
        public String getreachLeft(){
            return this.reach_left;
        }
        
        public void setreachRight(String value){
            this.reach_right = value;
        }
        
        public String getreachRight(){
            return this.reach_right;
        }
        
        public void setDataLeft(int value){
            this.dataLeft = value;
        }
        
        public int getDataLeft(){
        	return this.dataLeft;
        }
        
        public void setDataRight(int value){
            this.dataRight = value;
        }
            
        public int getDataRight(){
            return this.dataRight;
        }
                
        public void setResultLeft(int value)
        {
        	this.result[0]=value;
        }
        
        public int getResultLeftt()
        {
        	return this.result[0];
        }
        
        public void setResultRight(int value)
        {
        	this.result[1]=value;
        }
                
        
        public int getResultRight()
        {
        	return this.result[1];
        }
        
        
	}// end of class Node
		
	
	 // insert a node to the binary search tree
    public void insert(Node root, Node n){
        if(root == null|| n == null) return;
         
        if(root.getData().equalsIgnoreCase("0")){
            if(root.getLeft() == null){
                root.setLeft(n);
                // System.out.println("Added node to left of "+root.getData()+" of value "+n.getData());           
            }else{
                insert(root.getLeft(),n);
            }
 
        }else if(root.getData().equalsIgnoreCase("1")){
            if(root.getRight() == null){
                root.setRight(n);
              //  System.out.println("Added node to Right of "+root.getData()+" of value "+n.getData());     
            }else{
                insert(root.getRight(),n);
            }
             
        }
    }// end of inserting a Node 
	
	
	// method to calculate tree gain with param matrix , no.of row and column
	public void cal_tree_gain(String[][] matrix,int row,int col_pos, int L, int K, String val_file,String test_file,String to_print,int option) throws IOException
	{
		L_val=L;
		K_val=K;
		val_filename=val_file;
		test_filename=test_file;
		to_printval=to_print;
		opt=option;
	GainEntrCal gaincal = new GainEntrCal();
	
	root=gaincal.gain_cal_attr(matrix,row,col_pos,option);
	//System.out.println("The root is: " +root[1]);
	
	// Insert the root node to Dtree
	Dtree.put(root[1], null);
	Node root1= new Node(root[1],"yes","yes",(int)Double.parseDouble(root[4]),(int)Double.parseDouble(root[5]),root[6]);
/*	System.out.println("The Decision tree is :");
	print_tree(Dtree);
	System.out.println("The Binary Decision tree using class node is :");
	treeTraverse(root1);*/
	build_tree(matrix,row,col_pos,root[1],root1,option);
        if(to_printval.equalsIgnoreCase("yes"))	
        {  System.out.print("The completed tree before pruning is is: \n");
        treeTraverse(root1);
        }
        cal_test_accuracy(root1);
        cal_tree_prune(root1,val_filename,to_printval,L_val,K_val);
        
	}// end of cal_tree gain
	
	
	
	public void build_tree(String[][] matrix,int row,int col,String root,Node root1,int option)
	{
            if(root1==null)
                return;
		int col_pos=0; // get the position of root node
	/*	System.out.print("The matrix to work on \n");
                for(int i=0;i<row;i++)
                {
                    for(int j=0;j<col;j++)
                    {
                        System.out.print(matrix [i][j]+" \t");
                    }
                    System.out.print("\n");
                }
                */
                
		for(int i=0;i<col;i++)
		{
			if(matrix[0][i].equalsIgnoreCase(root))
				break;
				col_pos++;
				
			
		}
	//	System.out.println("The root : " +root +"position is" + col_pos);
                if(root == null)
                    return;
                if(root1.reach_left.equals("yes"))
		left_sub_tree(matrix,row,col,col_pos,root,root1,option);// Stores 0's subtree
              if(root1.reach_right.equals("yes"))
		right_sub_tree(matrix,row,col,col_pos,root,root1,option); // stores 1's subtree
		
		
		
	}
	
	// Method to construct left subtree of decision tree
	
	public void left_sub_tree(String[][] matrix,int row,int col,int col_pos,String root, Node root1,int option)
	{
		int col_count=0;
		int row_count=0;
	/*	System.out.println("***************************************************************************************************************");
		System.out.println("Im at left subtree with root's 0 value attributes");
		*/
	//	System.out.print(row +":" + col +":"+ col_pos+ ":" +root+ "\n");
		for(int i=1;i<row;i++)
		{
			
		if( Integer.parseInt(matrix[i][col_pos])==0)
			row_count++; // stores no of rows with root value as 0
			
		}
		int r=row_count+1;
		String[][] left_sub_matrix = new String[r][col-1]; 
	//	System.out.print("No of rows with 0's is:"+row_count +" \n");
		int n =0;
		for(int m=0;m<col;m++)
		{
			if(m==col_pos)
				continue;
		left_sub_matrix[0][n]=matrix[0][m];
		n++;
		}
		int k=1;
		for(int i=1;i<row;i++)
		{
			
			if( Integer.parseInt(matrix[i][col_pos])==0)
				
			{
				int p=0;
				for(int j=0;j<col;j++)
			{
					if(j== col_pos)
						continue;
				left_sub_matrix[k][p]=matrix[i][j];

					p++;
			}// end of j
				k++;
				
			}// end of if
			
		}// end of for i
	/*	System.out.print("Printing left sub matrix: \n");
for(int x=0;x<left_sub_matrix.length;x++)
{	for(int j=0;j<left_sub_matrix[x].length;j++)
		{System.out.print(left_sub_matrix[x][j] +"\t");
		}

System.out.println("\n");}*/
		String[] left_root= null;
		GainEntrCal left_gaincal = new GainEntrCal();
		left_root= left_gaincal.gain_cal_attr(left_sub_matrix,left_sub_matrix.length,left_sub_matrix[0].length,option);
//		System.out.println("The root is: " +left_root[1]+" and its left class value is: "+(int)Double.parseDouble(left_root[4]) +" and its right class value is: "+ (int)Double.parseDouble(left_root[5]));
		
		subtrees.add(left_root[1]);
		Dtree.put(root, subtrees);
		Node root_left= new Node(left_root[1],left_root[2],left_root[3],(int)Double.parseDouble(left_root[4]),(int)Double.parseDouble(left_root[5]),left_root[6]);
		if(root1.reach_left.equals("yes"))
		root1.setLeft(root_left);
	
	/*	System.out.println("The Decision tree is :");
		print_tree(Dtree);
		
		System.out.println("The Binary Decision tree using class node is :");
		treeTraverse(root1); */
		build_tree(left_sub_matrix,left_sub_matrix.length,left_sub_matrix[0].length,left_root[1],root_left,option);
	}// end of method left_sub_treet_
	
	

	// Method to construct right subtree of decision tree
	
	public void right_sub_tree(String[][] matrix,int row,int col,int col_pos,String root,Node root1,int option)
	{

		int row_count=0;
/*		System.out.println("***************************************************************************************************************");
		System.out.println("Im at right subtree with root's 1 value attributes");*/
		
//		System.out.print(row +":" + col +":"+ col_pos+ ":" +root+ "\n");
		for(int i=1;i<row;i++)
		{
			
		if( Integer.parseInt(matrix[i][col_pos])==1)
			row_count++; // stores no of rows with root value as 1
			
		}
		int r=row_count+1;
		String[][] right_sub_matrix = new String[r][col-1]; 
	//	System.out.print("No of rows with 1's is:"+row_count +" \n");
		int n =0;
		for(int m=0;m<col;m++)
		{
			if(m==col_pos)
				continue;
		right_sub_matrix[0][n]=matrix[0][m];
		n++;
		}
		int k=1;
		for(int i=1;i<row;i++)
		{
			
			if( Integer.parseInt(matrix[i][col_pos])==1)
				
			{
				int p=0;
				for(int j=0;j<col;j++)
			{
					if (j==col_pos)
						continue;
				right_sub_matrix[k][p]=matrix[i][j];
				p++;
					
			}// end of j
				k++;
			}// end of if
			
		}// end of for i
	/*	System.out.print("Printing right sub matrix: \n");
for(int x=0;x<right_sub_matrix.length;x++)
{	for(int j=0;j<right_sub_matrix[x].length;j++)
		{System.out.print(right_sub_matrix[x][j] +"\t");
		}
System.out.println("\n");}*/
		

String[] right_root= null;
GainEntrCal right_gaincal = new GainEntrCal();
right_root= right_gaincal.gain_cal_attr(right_sub_matrix,right_sub_matrix.length,right_sub_matrix[0].length,option);
//System.out.println("The root is: " +right_root[1]);

// Insert the root node to Dtree
subtrees.add(right_root[1]);
Dtree.put(root, subtrees);
Node root_right= new Node(right_root[1],right_root[2],right_root[3],(int)Double.parseDouble(right_root[4]),(int)Double.parseDouble(right_root[5]),right_root[6]);
if(root1.reach_right.equals("yes"))
root1.setRight(root_right);
/*System.out.println("The Decision tree is :");
print_tree(Dtree);
System.out.println("The Binary Decision tree using class node is :");
treeTraverse(root1);*/
build_tree(right_sub_matrix,right_sub_matrix.length,right_sub_matrix[0].length,right_root[1],root_right,option);
		
	} // end of method right_sub_tree
	
	
	
	// method to print decision tree
	
	public void print_tree(LinkedHashMap Dtree)
	{
		
		Set set = Dtree.entrySet();
		Iterator i = set.iterator();
		
		while(i.hasNext())
		{
         Map.Entry me = (Map.Entry)i.next();
         System.out.print(me.getKey() + ": ");
         System.out.println(me.getValue());
      }
		
		
	} // end of print_tree method
	
	
public	int currentDepth=0, depth =0;
	 //Method to tree Traversal
public void treeTraverse(Node root){
	 if (currentDepth > depth) {
        depth = currentDepth;
    }
	
   if(root != null){
   	currentDepth++;
   	for(int i=0;i<currentDepth;i++)
   	 System.out.print("|");
   	System.out.print(""+root.getData()+"="+ root.dataLeft+":");
   	if(root.result[0]!=-1)
   		System.out.print(root.result[0]+"\n");
   	else System.out.print("\n");
   	 treeTraverse(root.getLeft());
   	 
   	 
   	 for(int i=0;i<currentDepth;i++)
       	 System.out.print("|");
   	
    System.out.print(""+root.getData()+"=" +root.dataRight+":");

	if(root.result[1]!=-1)
		System.out.print(root.result[1]+"\n");
	else System.out.print("\n");
    
       treeTraverse(root.getRight()); 
     //  System.out.print("  ");
      // ("\n");
       currentDepth--;
      
   }// end of if
    
}// end of tree traversal method

	
    // method to calculate accuracy of test data
    public void cal_test_accuracy(Node root) throws IOException
    {
    	String file=test_filename;
    	CalAccuracy acc= new CalAccuracy();
    	float accuracy=acc.cal_tree_accuracy(root,file);
    	System.out.println("\nThe accuracy of decision tree on test data is : "+accuracy);
    }
    // end of method cal_tes_accuracy
    
    //Method to perform tree pruning
    public void cal_tree_prune(Node root, String val_file,String to_print,int L,int K) throws IOException
    {
    	TreePruning tp= new TreePruning();
    	tp.tree_prune(root,val_file,to_print,L,K);
    	
    	
    }// end of method cal_tree_prune
	
}// end of class construct tree
