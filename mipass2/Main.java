/*
Write a Java program for pass-II of a two-pass macro-processor. The output of pass-I
(MNT, MDT and file without any macro definitions) should be input for this assignment.
*/
import java.util.*;
import java.io.*;

class Pass2
{
	int mdtp;
	int mntp;
	ArrayList<String>mdt;	//for storing MDT table data
	LinkedHashMap<String,String>mnt; //for storing MNT table data (it stores value as key value pair)
	BufferedReader br;   //buffer reader for reading input from input file
	BufferedWriter bw;  //buffer writer for writing output in output file
	String s;           //s string for storing readed line
	ArrayList<String> formalParams, actualParams;	//for storing actual and formal parameter
	
	Pass2()throws Exception
	{
		//give memory to all fields
		mdt=new ArrayList<>();
		formalParams=new ArrayList<>();
		actualParams=new ArrayList<>();
		mnt=new LinkedHashMap<>();
		br=new BufferedReader(new FileReader("input.txt"));
		bw=new BufferedWriter(new FileWriter("output.txt"));
		s=null;
		initalizeTable(); //call to function for initalizing  mdt Arraylist and mnt LinkedHashMap  
	}
	
void initalizeTable()throws Exception
{
	BufferedReader br=new BufferedReader(new FileReader("MDT.txt"));

	while((s=br.readLine())!=null)
	{
		String s_arr[]=s.split(" "); 	//split readed line into array by space
		
		if(s_arr.length==0)		//if readed line is empty
		{
			continue;
		}
		if(s_arr.length==2)		//if line contain "MEND"
		{
			mdt.add(s_arr[1]);
		}
		if(s_arr.length==3)		//for other all line
		{
			mdt.add(s_arr[1]+" "+s_arr[2]);
		}
	}
	br.close();  //close mdt file
	
		br=new BufferedReader(new FileReader("MNT.txt"));	
		while((s=br.readLine())!=null)
		{
			String s_arr[]=s.split(" "); 
			mnt.put(s_arr[0],s_arr[1]);  //put value into key and value pair
		}
		br.close();	//close mnt file
}
	
void calculate()throws Exception
{		
	while((s=br.readLine())!=null)
	{
		String s_arr[]=s.split(" ");

		if(mnt.containsKey(s_arr[0]))  //if line is macro call (i.e. word matched with mnt table word)
		{
			String act_para[] = s_arr[1].split(","); //divide parameters into array
			actualParams.clear(); //clear the arraylist of actual parameter	
			String param; //temp variable
			for(int i=0;i<act_para.length;i++)
			{
				param=act_para[i];
				if(param.contains("=")) //for parameter in the form "&A=REG"
				{
					param=param.substring(param.indexOf("=")+1); //gets only string after "=" sign
				}
				actualParams.add(param); //store parameter into actualParams arraylist
			}
			mdtp=Integer.parseInt(mnt.get(s_arr[0])); //get pointer to mdt table
				
			boolean createformal=true;       
			String macro;
			while(true)
			{
				macro=mdt.get(mdtp);
				if(createformal) //only one time execute at start
				{
					//this if block add formal parameter into formalparams arraylist
					createformal=false;
					String macrodef[]=macro.split(" ");
					String form_para[]=macrodef[1].split(",");
					for(int i=0;i<form_para.length;i++)
					{
						param=form_para[i];
						if(param.contains("="))
						{
							param=param.substring(param.indexOf("=")+1);
						}
						formalParams.add(param);
					}
				}
				else
				{
					String inst[]=macro.split(" ");
					if(inst[0].equalsIgnoreCase("mend")) //check for macro end
					{
						mdtp++;
						break;
					}
					else
					{
						//Replace formal parameters with actual parameters
						param=replaceFormalParams(inst[1]);
							
					}
							
					bw.write(inst[0]+" "+param+"\n"); //write to output file
				}
					
				mdtp++;   //increment ptr to point to next macro instruction
			}
											
		}
		else{
				bw.write(s+"\n");  //other program part write as it is.
		}
	}
	br.close();
	bw.close();
		
}
//function which accept formal parameter and return actual paramter containing string
String replaceFormalParams(String formalstring)throws Exception
{
	formalstring=formalstring.replaceAll("#","");  //removing # from parameter
	String form_arr[]=formalstring.split(",");		//split paramater
	int index=0;
	String result="";
	for(int i=0;i<form_arr.length;i++)
	{
		index=Integer.parseInt(form_arr[i]);   //get paramter no
		if(index <= actualParams.size()) //if parameter contain in arraylist
		{
			result+=actualParams.get(index-1)+","; //concat result
		}
		else{
			result+=formalParams.get(index-1)+","; //else enter default parameter
		}
		
	}

	result=result.substring(0,result.length()-1); //remove last ","
	return result;   //return result
}
	
}

class Main
{
	public static void main(String[]args)throws Exception
	{
		Pass2 obj=new Pass2();
		obj.calculate();
	}
}
