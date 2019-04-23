/*
Write a Java program to implement Banker’s Algorithm
*/
import java.util.*;

class Bankers
{
	
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		int recsi=3; //no of resources  
		System.out.print("Enter no of Process:");
		int n=sc.nextInt(); //no of process stored in n
		int allo[][]=new int[n][recsi]; //Array to store current allocation 
		int max[][]=new int[n][recsi]; //Array to store max allocation
		int need[][]=new int[n][recsi]; //Array to store process need
		int f[]=new int[n]; //flag array to store process execution sequence
		int avi[]=new int[recsi]; //avilable resources array 
		
		for(int i=0;i<n;i++) //get allocation and max array
		{
			System.out.println("Enter process "+i+ ". allocation ");
			for(int j=0;j<recsi;j++){
				System.out.print("Enter Resource"+i+" :");
				allo[i][j]=sc.nextInt();
			}	
			System.out.println("Enter process "+i+ ". Max ");
			for(int j=0;j<recsi;j++){
				System.out.print("Enter Resource"+i+" :");
				max[i][j]=sc.nextInt();
			}
			f[i]=-1;  //initally add -1 in f array
			for(int j=0;j<recsi;j++){//calculate need array
				need[i][j]=max[i][j]-allo[i][j];
			}
		}
		
		for(int i=0;i<recsi;i++)
		{
				System.out.print("Enter Avilablity of Resource"+i+" :");
				avi[i]=sc.nextInt();
		}		
		
		
		int index=0; //index for f array
		int com=0;//completed process
		while(true)
		{
			if(com==n) //if all process completed break for loop
				break;
				
			for(int i=0;i<n;i++) //calculate process sequence		
			{
				boolean flag=true;
				for(int j=0;j<recsi;j++)//check if proces is already completed.
				{
					if(f[j]==i)
					{
						flag=false;
					}
				}
				if(!flag)//continue if process already completed.
				{continue;}
			
				for(int j=0;j<recsi;j++)
				{
					if(need[i][j]>avi[j])
					{	flag=false;
						break;
					}	
				}
				if(flag)//if condition satisy i.e. need<avilable
				{
					f[index]=i;
					index++;
					com++;
					for(int j=0;j<recsi;j++) //add resources in avilable array
					{
						avi[j]+=allo[i][j];

					}	
				}
			}
			//System.out.println("com	="+com+" ");
		}
		System.out.print("\n---Process sheduling sequence---\n\t");
		for(int j=0;j<index;j++) //add resources in avilable array
		{	
			System.out.print("P"+f[j]+"_");
		}
		System.out.println("\n");
	}	
}		
