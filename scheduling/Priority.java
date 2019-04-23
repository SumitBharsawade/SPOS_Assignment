/*
Write a Java program (using OOP features) to implement following scheduling algorithms:
FCFS
*/
import java.util.*;

class Priority
{
	
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter no of Process:");
		int n=sc.nextInt(); //no of process stored in n
		int btime[]=new int[n]; //Array to store Arrival time
		int pid[]=new int[n];   //Array to store process ids
		int ctime[] = new int[n];     //Array to store completion times
		int ttime[] = new int[n];     //Array to store turn around times
		int wtime[] = new int[n];     //Array to store waiting times
		int pri[]=new int[n];	//Array to store priority of process
		float avgwt=0,avgta=0;  //To store avg waiting and avg turnarroundtime
		for(int i=0;i<n;i++) //get arrival and burst time of process
		{
			pid[i]=i+1;
			System.out.print("Enter "+(i+1)+". Process Priority:");
			pri[i]=sc.nextInt();
			System.out.print("Enter "+(i+1)+". Process Burst Time:");
			btime[i]=sc.nextInt();
			
		}
		int temp;
		for(int i=0;i<n;i++) //sort proces accroding to arrival time
		{
			for(int j=0;j<n-(i+1);j++)
			{
				if(pri[j]>pri[j+1])
				{
					//swap pid time
					temp=pid[j];
					pid[j]=pid[j+1];
					pid[j+1]=temp;	
					
					//swap priority time
					temp=pri[j];
					pri[j]=pri[j+1];
					pri[j+1]=temp;	
					
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
				ctime[i]=btime[i];	
			}
			else
			{
				
				ctime[i]=ctime[i-1]+btime[i];
			}
	
			ttime[i]=ctime[i]; // turnaround time= completion time
			wtime[i]=ttime[i]-btime[i]; //waiting time= turnaround time- burst time
			avgwt+=wtime[i];
			avgta+=ttime[i];
		}
	
		System.out.println("\npid  Priority  brust  complete turn waiting");
		for(int  i = 0 ; i< n;  i++)
		{
			System.out.println(pid[i] + "  \t " + pri[i] + "\t" + btime[i] + "\t" + ctime[i] + "\t" + ttime[i] + "\t"  + wtime[i] ) ;
		}
	
		System.out.println("\naverage waiting time: "+ (avgwt/n));     // printing average waiting time.
		System.out.println("average turnaround time:"+(avgta/n));    // printing average turnaround time.
	}

}
