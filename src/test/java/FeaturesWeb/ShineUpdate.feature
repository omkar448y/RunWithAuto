Feature: R4C Quality Case Creation

@TestCaseWQCCNo1
@Smoke
Scenario: Verify the Case creation for Service Type as Stock Rotation & Return Reason as Standard Stock Rotation with Freight type as CRF using PO Search(Without ULT)
   Given User Launch Chrome browser
   When User open URL Web Shine
   And Click On Log In Button 
   And Enter Email To "omkaryadav9309@gmail.com"
   And Enter Pass To "Omkar@123"
   And Click On view All
   And click Apply
   And Close browser
 
@TestCaseWQCCNow2
@Regression
Scenario: search and apply
   Given User Launch Chrome browser
   When User open URL Web Shine
   And Click On Log In Button 
   And Enter Email To "rutujapatil2846@gmail.com"
   And Enter Pass To "Rutuja@243"
   And Enter Text globale search and click
   #And Click On view All
   #And click Apply

@TestCaseWQCCNau
@SanityWQCC
Scenario: Verify the Case creation for Service Type as Stock Rotation & Return Reason as Standard Stock Rotation with Freight type as CRF using PO Search(Without ULT)
   Given User Launch Chrome browser
   When User open URL Web Naukari
   And Click On login
   And Enter Email "omkaryadav9309@gmail.com"
   And Enter Pass "Omkar@123"
   #And Loops the best
   And Appload
  
   
   
   
   
   