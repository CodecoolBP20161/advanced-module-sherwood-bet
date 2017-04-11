Creation of offers:

  - From TEAMs MATCHes are formed every week
  - Certain EVENTs exist (e.g. final result, half time result, number of goals...)
  - From MATCHes and EVENTs concrete MATCHEVENTs are made
  - The USERs can bet on these MATCHEVENTs
  - TICKETs are also created with a certain name (e.g. Spanish league 25th round)
  - For every ticket 7 MATCHEVENTOFTICKET relation shall be created
  - There are two types of tickets - with 7 MATCHes (only_results) or with 1 MATCH (and a lot of different EVENT)


Requesting the offer:

  - The user shall see all the currently playable TICKETs with all the related MATCHEVENTs
  - For that the TICKETs' playable field is examined
  - The related MATCHEVENTs are returned with all their information
  - The TICKETs' only_results field is returned, so that the frontend knows the type, and knwos what to display


Requesting a ticket:

  - The USER request a TICKET and sends 3 parameters: the TICKET's number, the mode and the category
  - The backend searches for a USERTICKET, that belongs to the USER with the parameters
  - If it exists and is modifiable, the USERTICKET is returned with all the related BETs
  - If not, then the backend examines, if the USER has enough money, and informs the frontend if he does not
  - If the USER does have enough money, than a USERTICKET with created status is returned with all the related BETs


Confirming the filled the ticket

  - The USER's filled TICKET gets into the system as a played (but still modifiable) USERTICKET with 7 BETs
  - If the TICKET is not yet paid, the payment is validated here, and the status becomes modifiable
  - The all the BETs for the USERTICKET are updated (the USERTICKET here is in modifiable status)


Live informing

  - The USER is constantly informed about his current numbers and rank among the other USERs for every USERTICKET
  - Every MATCHEVENT that is currently happening is constantly updated
  - Its status is set to currently played
  - Its result is updated when something happens
  - Every minute, every TICKET thats deadline is over, but the winners are not calculated updates its USERTICKETs
  - When every BET for every related USERTICKETs are updated, they are sorted
  - From here, it is the frontend's job - it constantly requests the USER's USERTICKETs, and displays the standings


Calculating winners

  - The final version of the previous step, with little modifications
