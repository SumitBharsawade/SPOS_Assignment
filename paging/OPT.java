/*Write a Java Program (using OOP features) to implement paging simulation using
2. Optimal algorithm
*/
import java.util.*;
class OPT
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
				int iarr[]=new int[msize]; //array to future index of element
				for(int j=0;j<msize;j++) //initalize array to lastindex+1
				{
					iarr[j]=n;
				}
				for(int j=n-1;j>i;j--) //find next index of  appropriacte element which in allocated array
				{
					for(int k=0;k<msize;k++)
					{
						if(parr[j]==aarr[k])
						{
							iarr[k]=j;
						}
					}
				}
				int max=0,index=0;
				for(int j=0;j<msize;j++)//find less ocurred element index
				{
					if(max<iarr[j])
					{
						max=iarr[j];
						index=j;
					}
				}
				
				aarr[index]=parr[i];//replace that element
				
					
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
