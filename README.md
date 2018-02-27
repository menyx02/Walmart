# Walmart


How to run: 
This program was packaged in a jar. 

Download or clone the repository.    
Navigate to out -> artifacts -> Walmart_jar.  
Type the following command: "java -jar Walmart.jar #ofRows #ofColumns" 



Assumptions:
Scheduler is an interface so that if there are changes to the process we can easily switch it out without having to refactor everything. 
The menu is an abstract class, because at this point I know of the options that were required, but if more options are added or taken away, then I'd have to change a lot of if statments if I hard coded the menu options. 
The venue class is also an abstract class because all venues should have the same minimun requirements (seats, printing seats, etc) but they are a little different one from another. 
