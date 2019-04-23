/*Write a Java Program (using OOP features) to implement paging simulation using
1. Least Recently Used (LRU)
*/
import java.util.*;
class LRU
{
	public static void main(String[]args)
	{
		Scanner sc=new Scanner(System.in);
		int msize=3; //Memory size 
		int aarr[]=new int[msize]; //array to store memory allocated pages
		for(int i=0;i<msize;i++) //initalize array to -1
		{
			aarr[i]=-1;
		}
		
		System.out.print("Enter no of pages:"); 
		int n=sc.nextInt();
		int parr[]=new int[n];    //array to store requested pages
		
		System.out.print("Enter pages:");  //get page names from users 
		for(int i=0;i<n;i++)
		{
			parr[i]=sc.nextInt();
		}
		
		int PH=0; //count for page hit
		int PM=0; //count for page miss
		
		for(int i=0;i<msize;i++)  //initally add first msize element to allocated array
		{
				aarr[i]=parr[i];
			for(int j=0;j<msize;j++)//print page no.
			{
				System.out.print(aarr[j]);	
			}
			System.out.println("\n");
		}
		PM=msize;   //update page miss size
		
		for(int i=msize;i<n;i++)
		{
				boolean flag=true;
				for(int j=0;j<msize;j++)  //check is page is already present
				{
					if(parr[i]==aarr[j]) //if yes increase page hit and continue for next number
					{
						PH++;
						flag=false;
						break;
					}
					
				}
				if(flag){
				//if page not present then
				int ctr=msize;
				for(int j=i-1;j>=i-msize;j--) //find index of  appropriacte element which to replace
				{					
					if(i-ctr==0) //if lowest index is 0 then break
					{
						break;
					}
					for(int k=j-1;k>=i-ctr;k--)
					{
						if(parr[j]==parr[k])
						{
							ctr++;
						}
					}
					
				}
				
				for(int j=0;j<msize;j++) //replace that element
				{
					if(aarr[j]==parr[i-ctr])
					{
						aarr[j]=parr[i];
					}
				}
				PM++; //increase page miss counter
			}	
				for(int j=0;j<msize;j++)  //print page no.
				{
					System.out.print(aarr[j]);	
				}
				System.out.println("\n");
		}
		
		System.out.println("Page Miss"+PM); //print page miss
		System.out.println("Page Hit"+PH); //print page Hit
	}
}
