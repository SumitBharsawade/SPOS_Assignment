/*
Design suitable data structures and implement pass-I of a two-pass macro-processor using
OOP features in Java.
*/
import java.util.*;
import java.io.*;
class MNT{
	String mname;
	int mdtptr;
	MNT(String name,int pt)
	{
		mname=name;  //store macro name
		mdtptr=pt;  //store ptr to MDt table
	}

}
class ALA{  //argument list array
	String pname; //parameter name
	int pno;     //parameter number
	ALA(String name,int no)
	{
		pname=name;
		pno=no;
	}
}
class MDT{
	String iname; //instruction name or macro name
	String rest; //remining part
	MDT(String name,String res)
	{
		iname=name;
		rest=res;
	}

}
class Pass1
{
	int mdtp;
	int mntp;
	ArrayList<MDT>mdt; //ArrayList of MDT
	ArrayList<MNT>mnt; //ArrayList of MNT
	BufferedReader br; //buffer reader for reading input from input file
	BufferedWriter bw;//buffer writer for writing output in output file
	String s;
		
	Pass1()throws Exception
	{
		//give memory to all fields
		mdt=new ArrayList<>();
		mnt=new ArrayList<>();
		br=new BufferedReader(new FileReader("input.txt"));
		bw=new BufferedWriter(new FileWriter("output.txt"));
		s=null;
	}
	
	void calculate()throws Exception
	{
		s=br.readLine(); //read first line
		while(s!=null)
		{
			String s_arr[]=s.split(" ");
			if(s_arr[0].equalsIgnoreCase("MACRO"))
			{
				macro(); //handle macro operation
				continue;
			}
			
			bw.write(s+"\n"); //write normal (code withot) 
			s=br.readLine(); //read next line
		}
		br.close();
		bw.close();
		
		tableWrite();
		
	}
	void macro()throws Exception
	{
		
		boolean flag=true;
		s=br.readLine(); 
		int plength=0; //stores total no argument
		ArrayList<ALA>ala=new ArrayList<>(); //stores each macros parameter 
		while(!s.equalsIgnoreCase("MEND"))
		{
			String s_arr[]=s.split(" ");
			if(s.equals(""))
			{
				s=br.readLine();
				continue;
			}

			
			if(s_arr[0].equalsIgnoreCase("MACRO"))
			{
				macro();  //for nested macros (recursive call)
				continue;
			}
					
			if(flag)
			{
				mnt.add(new MNT(s_arr[0],mdtp));
				mntp++;
				flag=false;
				String temp[]=s_arr[1].split(",");
				plength=temp.length;
				for(int i=0;i<plength;i++)
				{
					if(temp[i].contains("="))//check parameter contain "=" sign
					{
						String temp2[]=temp[i].split("="); 
						ala.add(new ALA(temp2[0],i+1)); //only take part after "="
					}
					else{
					
						ala.add(new ALA(temp[i],i+1)); //other wise add it is.
					}
				}
				mdt.add(new MDT(s," "));
				mdtp++;
				s=br.readLine();
				continue;
			}
			
			for(int i=0;i<plength;i++)
			{
				s_arr[1]=s_arr[1].replaceAll(ala.get(i).pname,"#"+Integer.toString(ala.get(i).pno));//replace parameter with # and its no. 

			}
			mdt.add(new MDT(s_arr[0],s_arr[1]));	//add entry to  mnt table
			mdtp++;
			s=br.readLine();
		}
		mdt.add(new MDT(s," "));
		mdtp++;
		s=br.readLine(); //read next line
				
	}
	
	void tableWrite()throws Exception //fuction write to MDT and MNT file
	{
		BufferedWriter mntw=new BufferedWriter(new FileWriter("MNT.txt"));	
		BufferedWriter mdtw=new BufferedWriter(new FileWriter("MDT.txt"));
		
        for(int i=0;i<mntp;i++){
           		mntw.write(mnt.get(i).mname+" "+mnt.get(i).mdtptr+"\n");
        }
        	
        for(int i=0;i<mdtp;i++)
        {
        	mdtw.write(i+" "+mdt.get(i).iname+" "+mdt.get(i).rest+"\n");
        }
		mntw.close(); //close mnt file
		mdtw.close(); //close mdt file
	}
}

class Main
{
	public static void main(String[]args)throws Exception
	{
		Pass1 obj=new Pass1();
		obj.calculate();
	}
}
