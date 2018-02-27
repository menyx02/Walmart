# Walmart


How to run: 
This program was packaged in a jar. 

Download or clone the repository.    
Navigate to out -> artifacts -> Walmart_jar.  
Type the following command: "java -jar Walmart.jar #ofRows #ofColumns" 



Assumptions:
Best seats are defined as the seats closest to the stage.
The program will always prioritize seats together when searching for available 
seats, before than closeness to the stage. 
Scheduler uses interface so that if there are changes to the process we can easily switch it out without having to refactor everything. For example, if the assumption of best seat changes from closest to the stage from to cheapest, or the algorithm to find seats changes, then we can create a different subtype that implements the method on a different way and everything keeps working the same.

The menu is an abstract class, because at this point I know of the options that were required, but if more options are added or taken away, then I'd have to change a lot of if statments if I hard coded the menu options. 
The venue class is also an abstract class because all venues should have the same minimun requirements (seats, printing seats, etc) but they are a little different one from another. 
