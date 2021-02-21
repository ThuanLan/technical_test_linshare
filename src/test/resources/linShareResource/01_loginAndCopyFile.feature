@loginandcopyfile 
Feature: LOGIN 
  As an user
  I want to login to application
  So that verify login function

Scenario: TC01_Login and copy file 
	Given Login to the system
	When Click on My Space on the left menu
	And Click to view More Actions for a file
	And Select "Copy in a Workgroup" option
	And Select a workgroup from the list
	And Click Copy Here button
	And Click on Shared Space on the left menu	
	And Open the workgroup that you selected in step 5
	Then Verify if the file is copied to workgroup successfully