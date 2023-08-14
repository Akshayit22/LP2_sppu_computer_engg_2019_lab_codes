import java.lang.*;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;

class selection_Sort{


		int arr[]=new int[1000];


		public void get_data_file(){
	
			try{
				int new_element,element;
				File file = new File("sele.txt");
				Scanner sc = new Scanner(file);
				
				for(int i=0;i<1000;i++){
					arr[i] = Integer.parseInt(sc.nextLine());
				}
				
				System.out.println("File Readed Successfully :" + file + "\n"); 
			}
			catch(Exception e){
				System.out.println(e);
			}
		
		}

		public void selection(){
			
			//int arr[]=new int[] 
			
			for(int i=0;i<arr.length;i++)
			{
					System.out.println("Array elemrnts are :"+(i+1)+" "+arr[i]);
			}
			
			
			int temp=0;
			int j;
			
			for(int i=0;i<arr.length;i++)
			{
				int min=i;
				for(j=i+1;j<arr.length;j++)
				{
					if(arr[j]<arr[min])
					{
						min=j;
					}
				}
				
				temp=arr[i];
				arr[i]=arr[min];
				arr[min]=temp;
			}
		
		
		
			for(int i=0;i<arr.length;i++)
				{
					System.out.println("Array elemrnts after sorting are :"+(i+1)+" "+arr[i]);
				}
		}	
		public static void main(String args[])
		{
			selection_Sort ss = new selection_Sort();
			ss.get_data_file();
			ss.selection();
		}


}
