/*
Write a Java program (using OOP features) to implement following scheduling algorithms:
FCFS
*/
import java.util.*;

class FCFS
{
	
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter no of Process:");
		int n=sc.nextInt(); //no of process stored in n
		int atime[]=new int[n]; //Array to store Arrival time 
		int btime[]=new int[n]; //Array to store burst Time
		int pid[]=new int[n];   //Array to store process ids
		int ctime[] = new int[n];     //Array to store completion times
		int ttime[] = new int[n];     //Array to store turn around times
		int wtime[] = new int[n];     //Array to store waiting times
		float avgwt=0,avgta=0;  //To store avg waiting and avg turnarroundtime
		for(int i=0;i<n;i++) //get arrival and burst time of process
		{
			pid[i]=i+1;
			System.out.print("Enter "+(i+1)+". Process Arrival Time:");
			atime[i]=sc.nextInt();
			System.out.print("Enter "+(i+1)+". Process Burst Time:");
			btime[i]=sc.nextInt();
		}
		int temp;
		for(int i=0;i<n;i++) //sort proces accroding to arrival time
		{
			for(int j=0;j<n-(i+1);j++)
			{
				if(atime[j]>atime[j+1])
				{
					//swap process id time
					temp=pid[j];
					pid[j]=pid[j+1];
					pid[j+1]=temp;	
					
					//swap arrival time
					temp=atime[j];
					atime[j]=atime[j+1];
					atime[j+1]=temp;	
					
					//swap burst time
					temp=btime[j];
					btime[j]=btime[j+1];
					btime[j+1]=temp;	
				}
			}
		}
		
		for(int i=0;i<n;i++) //find process completion time
		{
			if(i==0)
			{
				ctime[i]=atime[i]+btime[i];	
			}
			else
			{
				if(atime[i]>ctime[i-1])
				{
					ctime[i]=atime[i]+btime[i];	
				}
				else
				{
					ctime[i]=ctime[i-1]+btime[i];
				}
			
			}
			ttime[i]=ctime[i]-atime[i]; // turnaround time= completion time- arrival time
			wtime[i]=ttime[i]-btime[i]; //waiting time= turnaround time- burst time
			avgwt+=wtime[i];
			avgta+=ttime[i];
		}
	
		System.out.println("\npid  arrival  brust  complete turn waiting");
		for(int  i = 0 ; i< n;  i++)
		{
			System.out.println(pid[i] + "  \t " + atime[i] + "\t" + btime[i] + "\t" + ctime[i] + "\t" + ttime[i] + "\t"  + wtime[i] ) ;
		}
	
		System.out.println("\naverage waiting time: "+ (avgwt/n));     // printing average waiting time.
		System.out.println("average turnaround time:"+(avgta/n));    // printing average turnaround time.
	}

}
