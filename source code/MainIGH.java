

import java.io.IOException;



public class MainIGH {

	public static void main(String[] args) throws IOException {
		
		if(args.length <6)
			{
			System.out.print("Insufficient Argument please check the arguments passed ");
		System.exit(1);
			}
	
		int L =Integer.parseInt(args[0]);
		int K =Integer.parseInt(args[1]);
		String train_file =args[2];
		String val_file=args[3];
		String test_file=args[4];
		String to_print=args[5];
		// call the function to read training set into data structure
		ReadCsvToDs obj = new ReadCsvToDs();
		String csvfile=train_file;
		obj.convertCsvToMatrix(csvfile);
	//	obj.printMatrix();
		
		System.out.println("*******************Information Gain Heuristic*************");
		ConstructTree tree1= new ConstructTree();
		tree1.cal_tree_gain(obj.FileToMatrix,obj.row_count,obj.col_count,L,K,val_file,test_file,to_print,1);
		System.out.println("*******************Variance Impurity Heuristic*************");
		ConstructTree tree2= new ConstructTree();
		tree2.cal_tree_gain(obj.FileToMatrix,obj.row_count,obj.col_count,L,K,val_file,test_file,to_print,2);
	
				
	} // end of main method
	
	

	
	
}// end of class MainIGH