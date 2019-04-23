class My
{
	static{
		System.loadLibrary("Hello");
	}
	
	public native void printmsg();
	
	public static void main(String[] args)
	{
		new My().printmsg();
	} 

}
