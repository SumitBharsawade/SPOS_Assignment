#include<unistd.h>
#include<stdio.h>
#include<iostream>
#include<stdlib.h>
#include<sys/wait.h> 
using namespace std;

int main()
{
	int n=0;
	while(1){
	cout<<"Enter choices as follows:"<<"\n1.ps\n2.fork\n3.exec\n4.join\n5.wait\n--->>";
	cin>>n;
	cout<<endl;
	switch(n)
	{
		case 1:
				system("ps");
				cout<<endl;
				break;
		case 2:
				int result;
				 result=fork();
				if(result==0)
				{
					cout<<"Hi I am child"<<endl;
				}else if(result>0)
				{
					cout<<"New process is created   pid="<<result<<endl;
				}
				else{
						cout<<"Error in creating a child process"<<endl;
					}
				break;
		case 3:
					 result=-1;
					 result=fork();
				if(result==0)
				{
					execv("myexe",NULL);
				}else if(result>0)
				{
					cout<<"New process is created   pid="<<result<<endl;
				}
				else{
						cout<<"Error in creating a child process"<<endl;
					}
				break;
		case 4:
				{
				
				//system("cat a.txt");
				//system("cat b.txt");
				system("join a.txt b.txt >join.txt");
				cout<<endl;
				break;
				}
		case 5:
				if (fork()== 0){ 
				cout<<"I am a child"<<endl;
        		exit(0);}      
    			else{
        			int cpid = wait(NULL);
				cout<<"Child execution done"<<endl;
			}
	}}
}



















