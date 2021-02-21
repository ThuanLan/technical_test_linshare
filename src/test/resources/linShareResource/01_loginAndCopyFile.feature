@loginandcopyfile 
Feature: LOGIN AND COPY FILE 
  As an user
  I want to login to application
  Copy a file to a work group
  So that verify copy file function

Scenario: TC01_Login and copy file 
	Given Login to the system
	When Click on a "My space" menu on the left menu
	And Click to view More Actions for a file
	And Select "Copy in a Workgroup" option
	And Select a workgroup from the list
	And Click Copy Here button
	And Click on a "Shared space" menu on the left menu	
	And Open the workgroup that you selected earlier
	Then Verify if the file is copied to workgroup successfully