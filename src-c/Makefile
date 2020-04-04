CXX=g++
OBJS=de_morigm_howfluffybot_Calculator.o
HEADER_CPP=de_morigm_howfluffybot_Calculator.h
LIB=libhowfluffybot.so
INCLUDE_JAVA=${JAVA_HOME}/include
INCLUDE_JAVA_LINUX=${JAVA_HOME}/include/linux

${LIB}: ${HEADER_CPP} ${OBJS}
	${CXX} -I ${INCLUDE_JAVA} -I ${INCLUDE_JAVA_LINUX} -shared -o $@ $^

%.o: %.cpp
	${CXX} -I ${INCLUDE_JAVA} -I ${INCLUDE_JAVA_LINUX} -fPIC -o $@ -c $^

install: ${LIB}
	cp $^ ../$^

header: ${HEADER_CPP}
	
de_morigm_howfluffybot_Calculator.h:
	rm -f $@.h
	(cd ../src/main/java;javah de.morigm.howfluffybot.Calculator)
	mv ../src/main/java/*.h ./

clean:
	rm -f ${LIB}
	rm -f ../${LIB}
	rm -f ${OBJS}
	rm -f ${HEADER_CPP}

.PHONY: clean install header
