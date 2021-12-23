# Conference track management
### Coding challenge for itemis 

---
Task description:
You are planning a big programming conference and have received many proposals which have passed the initial screen 
process, but you're having trouble fitting them into the time constraints of the day -- there are so many possibilities! 
So you write a program to do it for you.
- The conference has multiple tracks each of which has a morning and afternoon session. 
- Each session contains multiple talks. 
- Morning sessions begin at 9am and must finish by 12 noon, for lunch. 
- Afternoon sessions begin at 1pm and must finish in time for the networking event. 
- The networking event can start no earlier than 4:00 and no later than 5:00. 
- No talk title has numbers in it. 
- All talk lengths are either in minutes (not hours) or lightning (5 minutes). 
- Presenters will be very punctual; there needs to be no gap between sessions.

Note that depending on how you choose to complete this problem, your solution may give a different ordering or 
combination of talks into tracks. This is acceptable; you don't need to exactly duplicate the sample output given here.

----
***### Test Input:***
- Writing Fast Tests Against Enterprise Rails 60min
- Overdoing it in Python 45min
- Lua for the Masses 30min
- Ruby Errors from Mismatched Gem Versions 45min
- Common Ruby Errors 45min
- Rails for Python Developers lightning
- Communicating Over Distance 60min
- Accounting-Driven Development 45min
- Woah 30min
- Sit Down and Write 30min
- Pair Programming vs Noise 45min
- Rails Magic 60min
- Ruby on Rails: Why We Should Move On 60min
- Clojure Ate Scala (on my project) 45min
- Programming in the Boondocks of Seattle 30min
- Ruby vs. Clojure for Back-End Development 30min
- Ruby on Rails Legacy App Maintenance 60min
- A World Without HackerNews 30min
- User Interface CSS in Rails Apps 30min  

***### Test Output:***  
Track 1:
- 09:00AM Writing Fast Tests Against Enterprise Rails 60min
- 10:00AM Overdoing it in Python 45min
- 10:45AM Lua for the Masses 30min
- 11:15AM Ruby Errors from Mismatched Gem Versions 45min
- 12:00PM Lunch
- 01:00PM Ruby on Rails: Why We Should Move On 60min
- 02:00PM Common Ruby Errors 45min
- 02:45PM Pair Programming vs Noise 45min
- 03:30PM Programming in the Boondocks of Seattle 30min
- 04:00PM Ruby vs. Clojure for Back-End Development 30min
- 04:30PM User Interface CSS in Rails Apps 30min
- 05:00PM Networking Event 

Track 2:
- 09:00AM Communicating Over Distance 60min 
- 10:00AM Rails Magic 60min 
- 11:00AM Woah 30min 
- 11:30AM Sit Down and Write 30min 
- 12:00PM Lunch 
- 01:00PM Accounting-Driven Development 45min 
- 01:45PM Clojure Ate Scala (on my project) 45min 
- 02:30PM A World Without HackerNews 30min 
- 03:00PM Ruby on Rails Legacy App Maintenance 60min 
- 04:00PM Rails for Python Developers lightning 
- 05:00PM Networking Event
---
### Design overview
Application is run from Main.main() method and should get an input argument - path to the input file with Talks 
description. Let's review implemented classes in `com.itemis` package:
- `ConferenceConstant` in `constant` package contains some constants used in different other classes
- `IncorrectTalkFormatException` in `exception` package was implemented to be thrown when Talk has incorrect format:
empty task title, duration of a Talk is less than 10 minutes or more than the duration of the longest afternoon session.
- `Conference` in `model` package represents a Conference which is instantiated based on the list of Talk and
allocate them into Tracks based on the following logic: initially we create the first empty Track, iterate over Talks 
list and add them to the Track. If Talk cannot be added to Track, we create the new Track and this Talk to it. 
While iterating over Talks we also iterate over Tracks because even though current Talk cannot be added to this Track,
next Talk could be possibly added further because of smaller duration.
- `Track` in `model` package represents Track class which consist of 2 sessions: morning and afternoon. Both sessions 
are not created right while Track object creation, they're created while Talk addition - when we want to add Talk to 
Track without morning session, this session should be instantiated and Talk should be added. When we cannot add Talk to
- morning Session, we create an afternoon Session and add Talk to it. 
- `Session` in `model`package represents Session class which can be instantiated as a morning or afternoon Session.
They're instantiated not by constructor, but by static methods, where we set to the Session some initial fields like
duration, remaining duration and last Talk end date, which are useful for calculations of addition possibility of Talk
or Talk start date.
- `Talk` in `model` represents Talk which contains duration, title fields, which are used for Talk instantiating. 
Talk class contains also startDate field which are set later when we add Talk to the Session and can 
calculate the start date.
- `Main` class contains the entry main() method which reads data from input file, path to which should be set in program
arguments. Data is read line by line, which are converted into Talk objects, stored in a collection and then are 
transmitted into Conference object.  


---
### How to run application 
1) Clone git repository to your local directory 
2) Run command line in this local directory and run maven:
``` 
mvn clean package
```
Maven will run all tests, report about results of testing and package jar file which we run in next step.
3) Run in command line:
``` 
java -jar ./target/conference.jar ./src/main/resources/test_data.txt
```
where ./src/main/resources/test_data.txt is a relative path to the input file.

P.S. Of course if you want you can run Intellij IDEA or other IDE, configure path to input file in program arguments 
and run Main.main().