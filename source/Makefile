name = Application

but : $(name)

CLASSES = Application.class \
	ButtonLoadFileObserver.class \
	ButtonSaveFileObserver.class \
	CaseNumber.class \
	FileManager.class \
	GridModel.class \
	SaveFilter.class \

CC = javac

CFLAGS = -implicit:none

Application.class : FileManager.class GridModel.class
	$(CC) $(CFLAGS) Application.java

ButtonLoadFileObserver.class : FileManager.class GridModel.class
	$(CC) $(CFLAGS) ButtonLoadFileObserver.java

ButtonSaveFileObserver.class : FileManager.class GridModel.class
	$(CC) $(CFLAGS) ButtonSaveFileObserver.java

FileManager.class : GridModel.class SaveFilter.class
	$(CC) $(CFLAGS) FileManager.java

GridModel.class : CaseNumber.class
	$(CC) $(CFLAGS) GridModel.java

SaveFilter.class :
	$(CC) $(CFLAGS) SaveFilter.java

CaseNumber.class :
	$(CC) $(CFLAGS) CaseNumber.java

Application : Application.class

clean : 
	-rm -f *.class

run : Application
	java $(name)

.PHONY : but clean