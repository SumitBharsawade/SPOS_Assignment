#include<stdio.h>
#include<jni.h>
#include "My.h"
/*The first parameter, JNIEnv *, is the JNI interface pointer. This interface pointer is organized as a function table, with every JNI function at a known table entry. Your native method invokes specific JNI functions to access Java objects through the JNIEnv * pointer. The jobject parameter is a reference to the object itself (it is like the this pointer in C++).
*/
JNIEXPORT void JNICALL Java_My_printmsg(JNIEnv *env,jobject object)
{
	printf("Hellow world");
}
