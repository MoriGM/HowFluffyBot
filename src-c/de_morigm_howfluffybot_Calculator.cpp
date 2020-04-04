#include <jni.h>
#include <stdlib.h>
#include <time.h>
#include "de_morigm_howfluffybot_Calculator.h"

JNIEXPORT jint JNICALL Java_de_morigm_howfluffybot_Calculator_calculateFluffy(JNIEnv * env, jclass clm, jint userid, jstring username)
{
	int ret = userid;
	
	for (const char* c = env->GetStringUTFChars(username, NULL);*c != '\0';c++)
		ret += *c;
	
	return ret % 101;
}

JNIEXPORT jint JNICALL Java_de_morigm_howfluffybot_Calculator_checkFluffy(JNIEnv *, jclass)
{
	static int seed = 0;
	if (seed == 0)
	{
		seed = time(0);
		srand(seed);
	}
	return rand() % 101;
}

