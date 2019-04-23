/*
Write a Java program (using OOP features) to implement following scheduling algorithms:
Round Robin
*/
import java.util.*;

class RR
{
	
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter time span for process:");
		int span=sc.nextInt(); 
		
		System.out.print("Enter no of Process:");
		int n=sc.nextInt(); //no of process stored in n
		int atime[]=new int[n]; //Array to store Arrival time 
		int btime[]=new int[n]; //Array to store burst Time
		int reburst[]=new int[n]; //Array to remaining store burst Time
		int pid[]=new int[n];   //Array to store process ids
		int ctime[] = new int[n];     //Array to store completion times
		int ttime[] = new int[n];     //Array to store turn around times
		int wtime[] = new int[n];     //Array to store waiting times
		int timechart[][] = new int[50][2];
		int squeue[] = new int[n];  // squeue to store process in queue
		//int spcom[]=new int[n];  //store span complete or not
		float avgwt=0,avgta=0;  //To store avg waiting and avg turnarroundtime
		int t=0;//index for timr chart
		System.out.print("\n");
		int temp=0;
		for(int i=0;i<n;i++) //get arrival and burst time of process
		{
			pid[i]=i+1;
			System.out.print("Enter "+(i+1)+". Process Arrival Time:");
			atime[i]=sc.nextInt();
			System.out.print("Enter "+(i+1)+". Process Burst Time:");
			btime[i]=sc.nextInt();
			
		}
		
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
		for(int i=0;i<n;i++)
		{
			reburst[i]=btime[i];
		}
		int cput=-1,compp=0;//1.cpu time 2.completed process
		int cp=0,qp=0,en=1; //1.current process pointer  2.queue pointer 3.en =no of process till arived
		
		squeue[0]=pid[0];
		cput=atime[0];
		while(!(compp==n)) 
		{
			int index=0;
			for(int i=0;i<n;i++)
			{
				if(pid[i]==squeue[cp])
				{
					index=i;
				}
			}
	
			if(reburst[index]>=span) //if remaining burst time is greater or equal to span
			{
				timechart[t][0]=pid[index];					
				timechart[t][1]=cput+span;	
				reburst[index]-=span;	
			}else //if burst time is lower than span
			{
				timechart[t][0]=pid[index];					
				timechart[t][1]=cput+reburst[index];					
				reburst[index]=0;
			}
			t++;	//update the time chart index		
			cput=timechart[t-1][1];
			if(reburst[index]==0) //update all field after particular process finish its execution
			{
				ctime[index]=cput;
				ttime[index]=ctime[index]-atime[index];
				wtime[index]=ttime[index]-btime[index];
				avgwt+=wtime[index];
				avgta+=ttime[index];
				compp++;
							
			}
		
			for(int j=en;j<n;j++)//add process in queue which comes till now
			{
				if(cput>=atime[j])
				{
					en++;
					if(qp<n-1)
						qp++;
					else
						qp=0;
					
					squeue[qp]=pid[j];

				}else
				{break;}	
	
			}
		
			if(reburst[index]>0)//current process enter into queue
			{
					if(qp<n-1)
						qp++;
					else
						qp=0;
						
					squeue[qp]=pid[index];		
			}
			if(cp<n-1)
				cp++;
			else
				cp=0;

		}

		
		temp=0;
		System.out.println("\n-------Timing Diagram-----");
		System.out.print("0");
		for(int i=0;i<t;i++)
		{
			System.out.print("--p"+(timechart[i][0])+"--"+timechart[i][1]);
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

