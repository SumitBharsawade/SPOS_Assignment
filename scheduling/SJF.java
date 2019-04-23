/*
Write a Java program (using OOP features) to implement following scheduling algorithms:
FCFS
*/
import java.util.*;

class SJF
{
	
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter no of Process:");
		int n=sc.nextInt(); //no of process stored in n
		int atime[]=new int[n]; //Array to store Arrival time 
		int btime[]=new int[n]; //Array to store burst Time
		int reburst[]=new int[n]; //Array to store burst Time
		int pid[]=new int[n];   //Array to store process ids
		int ctime[] = new int[n];     //Array to store completion times
		int ttime[] = new int[n];     //Array to store turn around times
		int wtime[] = new int[n];     //Array to store waiting times
		int timechart[][] = new int[50][2];
		boolean f[] = new boolean[n];  // f means it is flag it checks process is completed or not
		float avgwt=0,avgta=0;  //To store avg waiting and avg turnarroundtime
		int t=0;//index for timr chart 
		for(int i=0;i<n;i++) //get arrival and burst time of process
		{
			pid[i]=i+1;
			System.out.print("Enter "+(i+1)+". Process Arrival Time:");
			atime[i]=sc.nextInt();
			System.out.print("Enter "+(i+1)+". Process Burst Time:");
			btime[i]=sc.nextInt();
			reburst[i]=btime[i];
			f[i]=false;
		}
		int cput=0,compp=0;//1.cpu time 2.completed process
		
		while(!(compp==n)) 
		{
					cput++;  //increase cpu time
			int min=999;int index=n;
			
			for (int i=0; i<n; i++)
			{
				if(atime[i]<cput&&reburst[i]>0&&(reburst[i]<min))
				{
					
					min=reburst[i];
					index=i;
				}
			}
			
			if(!(index==n))//check any process in queue or not
			{
				if(t==0)
				{
					timechart[t][0]=index;  //store index of process
					timechart[t][1]=cput;	//give current cpu time to that process
					t++;
					reburst[index]-=1;					
				}
				else
				{
					if(timechart[t-1][0]==index)//if previous process is same
					{
						timechart[t-1][1]+=1;		//then only increment time in timechart
						reburst[index]-=1;		
					}
					else{//if different write that process entry in timechart 
						timechart[t][0]=index;
						timechart[t][1]=timechart[t][1]+1;
						t++;
						reburst[index]-=1;
					}
					if(reburst[index]==0) //update all field after particular process finish its execution
						{
							ctime[index]=cput;
							ttime[index]=ctime[index]-atime[index];
							wtime[index]=ttime[index]-btime[index];
							avgwt+=wtime[index];
							avgta+=ttime[index];
							compp++;
							
						}
				}
			}

		}
		int temp=0;
		System.out.println("\n-------Timing Diagram-----");
		System.out.print("0");
		for(int i=0;i<t;i++)
		{
			temp+=timechart[i][1];
			System.out.print("--p"+(timechart[i][0]+1)+"--"+temp);
		}		
	
		System.out.println("\n\npid  arrival  brust  complete turn waiting");
		for(int  i = 0 ; i< n;  i++)
		{
			System.out.println(pid[i] + "  \t " + atime[i] + "\t" + btime[i] + "\t" + ctime[i] + "\t" + ttime[i] + "\t"  + wtime[i] ) ;
		}
	
		System.out.println("\naverage waiting time: "+ (avgwt/n));     // printing average waiting time.
		System.out.println("average turnaround time:"+(avgta/n));    // printing average turnaround time.
	}

}
