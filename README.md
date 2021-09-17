# UlessonTest

Test project designed mocking the ui found at : 
https://www.figma.com/file/TnRNuPMAPO8aDtEwrxfHBh/Live-Lessons-Assessment?node-id=640%3A117


Used MVVM, clean architechture.

App is broken into the following modules

*Databases
***Entities holding the entity of the livelesson table and the my lesson tale
***DAO holding the room structure for crud operations on the entities

*Domains
***LessonModel holds the a template that the entities can be easily converted to to provide a consitent structure of data

*network
***helpers.safeApi that prevents app from crashing if an exception occurs on the network call
***Networkcalls to hold all api queries
***NetworkService that defines the converter and use retrofit to fecth data


*repository
*** implements the network interface to fetch record from api and update database according
    also holds the calls to the database

*viemodels holds the data needed for updating th UI

*Lessonadapter for providing the list adapter methods

*util 
 ***BindingUtil for databindings for spinners, textviews, and updating visibility of some view
 ***util for returning the correct and formmatted timeString
 
 *UI 
 for holding the actities classes
 
 @ENENCHEPETER
 @NativeKotlin
 @CleanArchitecture
