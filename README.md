# Special-dmoney--web--automation-using-selenium

## How to run this project
- clone the project
- execute with intellij

## Tool used in this Project
- Selenium

## Framework used in this Project
- TestNG

## Project Scenarios

URL: https://dmoney-web.vercel.app/ 

1. Login as admin
creds: salman@roadtocareer.net 1234 
2. create an agent and customer. Assert agent and customer has created 
3. search by the newly created customer phone number 
4. Update customer password. Assert update is successful 
5. Now login to system account
creds: system@roadtocareer.net 1234 
6. Deposit 2000 tk to the agent you just created and assert deposit successful 
7. Now login to the agent account and deposit to the customer account you just created 
8. Assert successful deposit message 
9. Now login to the customer account and check if statement is showing 
10. Now send 100 tk to another customer account 
11 Check again the customer statement that 105 tk is debited. Assert with the expected total amount. 
12. Now withdraw tk 500 and assert the expected current balance after successful withdrawal 
13. Now go to customer statement and assert trnx id is found in statement 
14. Now payment to a merchant 100 tk and assert which current balance is expected
Creds: 01686606905 1234
15. Now login to the admin again, go to transaction menu and search by your customer mobile number to check that payment is shown in transaction list

## Test cases
https://docs.google.com/spreadsheets/d/1JiGzHZnqsCzX6Yrr-AFVL5i-F5LjqEPZ/edit?usp=share_link&ouid=103304077434156054244&rtpof=true&sd=true

## Allure report

![ReportJPG](https://user-images.githubusercontent.com/122162468/230754616-a91e83a6-8ec1-4ed6-8438-3c893045cb8d.JPG)

![Behaviour](https://user-images.githubusercontent.com/122162468/230754619-94ec434c-bf41-4f02-9ecf-d0d55f2ba54f.JPG)

## Video recording of the automation

https://user-images.githubusercontent.com/122162468/230435460-db2aad12-3b98-4497-be00-434cf1fd28cc.mp4



