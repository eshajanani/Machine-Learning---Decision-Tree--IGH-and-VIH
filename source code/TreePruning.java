

import java.io.IOException;
import java.util.Random;

public class TreePruning extends ConstructTree {
	int count=0;
	int cnode=0;// holds count of no of non leaf nodes
	int num=1;
	float prun_acc=0;
	// method to prune completed tree using validation data_set
	ConstructTree tree =new ConstructTree();
	public void tree_prune(Node root, String val_filename,String to_print,int L,int K) throws IOException
	{
	//	System.out.println("\nInside Post Pruning");
		//let Dbest =D;
		String file=val_filename;
		Node Dbest=root;
		CalAccuracy dBestAcc=new CalAccuracy();
		float Dbest_acc =dBestAcc.cal_tree_accuracy(root,file);
		System.out.println("The accuracy of tree before pruning on validation set is : "+Dbest_acc);
		
		for(int i=1;i<=L;i++)
		{
		//	System.out.println("Inside of I looop");
		//System.out.println("the temp_root is: "+temp_root.getData());
			// Copy D tree to D'
		Node temp_root= build_copy_tree(root);
		
/*		System.out.println("The duplicate tree is\n");
		tree.treeTraverse(temp_root);*/
		
		int M= random_number_generator(1,K);
	//	System.out.println("The random number for k is : "+M);
		for(int j=1;j<M;j++)
		{
			//System.out.println("Inside of JJ looop");
		int nonLeaf_count=0;
		nonLeaf_count=count_nodes(temp_root);// count the non-leaf nodes
	//	System.out.println("The number of non leaf nodes is : "+nonLeaf_count);
		order_nodes(temp_root); // order the non leaf nodes
	//	tree.treeTraverse(temp_root);
	//	System.out.println("the actual tree is:");
	//	tree.treeTraverse(root);
		if(nonLeaf_count <=2)
			break;
		int P = random_number_generator(1,nonLeaf_count);
	//	System.out.println("the node to be removed is : "+P);
		remove_and_replace(P,temp_root);
	//	System.out.println("removed tree is:\n");
	//	tree.treeTraverse(temp_root);
		count=0;
		num=1;
	}// end of for j
	//	System.out.println("out of jj looop");
		count=0;
		num=1;
		
	//	tree.treeTraverse(temp_root);
		
		// evaluate the accuracy of the D' on the validation set
		CalAccuracy temp_Acc=new CalAccuracy();
		float temp_d_acc=temp_Acc.cal_tree_accuracy(temp_root, file);
	//	System.out.println("\n The  accuracy of pruned tree on validation set is:" +temp_d_acc);
		if (temp_d_acc >= Dbest_acc)
			{
			Dbest=temp_root;
			prun_acc=temp_d_acc;
			}
		}// end of for i
	//	System.out.println("out of I looop");
		System.out.println("The accuracy of pruned tree for validation set is : "+prun_acc );
		if(to_print.equalsIgnoreCase("yes"))
		{
			System.out.print("\n The best tree after pruning is : \n");
		
		tree.treeTraverse(Dbest);
		}
		
	}// end of method tree_prune
	
	// method to construct duplicate tree for pruning
	public Node build_copy_tree(Node root)
	{
		if(root==null)
		return null;
		Node t1 = new Node(root);
	//	System.out.println(root.getLeft());
		t1.setLeft(build_copy_tree(root.getLeft()));
	//	System.out.println(root.getRight());
		t1.setRight(build_copy_tree(root.getRight()));
		return new Node(t1);
	}
	

	// method to generate random number
	public int random_number_generator(int min, int max)
	{
		Random r= new Random();
		int r_no = r.nextInt((max-min)-1)+min;
	//	System.out.print("\n the random is: "+r_no);
		if(r_no <2)
			return r_no+1;
		return r_no;
	}
	
	// end of method random_number_generator
	
	// method to count number of non-leaf nodes in the tree
	
	public int count_nodes(Node root)
	{
		

		
		if(root!=null)
		{	count++;
		count_nodes(root.getLeft());
		count_nodes(root.getRight());
		}
		return count;
	}// end of method count_nonLeaf
	
	
	// method to order the non leaf nodes in the tree
	
	public void order_nodes (Node root)
	{
		if(root!=null)
		{
			
			root.setNodeNo(num);
			num++;
			order_nodes(root.getLeft());
			order_nodes(root.getRight());
		}
	}
	
	// method to remove a node from the tree and replace it with the high class value 
	
	public void remove_and_replace(int p, Node root)
	{
		
			if(root==null)
			return;
		else if(root!=null)
		{	if(root.getLeft()!=null &&root.getLeft().node_no==p )
			
				{
				root.setreachLeft("no");
				root.result[0]=(int)Double.parseDouble(root.getLeft().getMaxValue());
				root.setLeft(null);
				return;
				}
			
			else if(root.getRight()!= null && root.getRight().node_no==p)
			{
				root.setreachRight("no");
			root.result[1]=(int)Double.parseDouble(root.getRight().getMaxValue());
				root.setRight(null);
				return;
				}
			
			remove_and_replace(p,root.getLeft());
			remove_and_replace(p,root.getRight());
			
		}// end of else if
		
		
	}
	// method to evaluate the pruned tree accuracy
	
	
}// end of class
