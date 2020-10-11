Take home project – Time given to complete 1 week from completing phone interview.
Congratulations on passing the phone interview! Now it’s time to solve a nice little problem from home.
Sample Data:
Input User Record: 

<consumer>
    <consumerId>3562662382393</consumerId>
    <consumerName> Debt Ridden </consumerName>
    <bankAccount>
        <amount>27576.0</amount>
        <accountType>LOAN</accountType>
        <lenderName>Chase</lenderName>
    </bankAccount>
    <creditCardAccount>
        <amount>3354.0</amount>
        <accountType>CREDITCARD</accountType>
        <expirationDate>12/12/2022</expirationDate>
        <issuer>Citi</issuer>
        <creditCardNumber>4485XXXXXX955556</creditCardNumber>
    </creditCardAccount>
    <mortgageAccount>
        <amount>125460.0</amount>
        <accountType>Fixed-30-year</accountType>
        <lenderName>Citi</lenderName>
    </mortgageAccount>
</consumer>

Output section for this record:
Descriptions:
bankAccount: bankAccountType: LOAN, bankName: Chase, amount: 27576
creditCardAccount: creditCardNumber : 4485XXXXXX955556, expirationDate: 20221212, Issuer : Citi, amount:3354
mortgageAccount: lenderName: Citi, accountType: Fixed-30-year, amount:125460
   
You are asked to design a new system that will receive user account records as XML files from another company. The system you design needs to process these and feed the output to your company's reporting system.
Your system will receive records via REST at any time of day, one record at a time. Each record contains all accounts for a single user. There will be at most 100 accounts per user and a maximum of 100,000 users per day. The REST service needs to handle a maximum of 100 messages, each with one user record, per second. Any records not sent via REST, will be sent via a single batch file via FTP at 2am daily. All output for the day, both for records received via REST and SFTP, needs to be combined in a single file that your system will SFTP to the receiving system at 1am daily. Account numbers are not needed in the output, as the receiving system will only use your aggregates and will also calculate its own based on the raw data in your output file.
At least some of the data you are receiving was entered by data entry operators and their UI had no validation, meaning don't expect data to be reliable.
Your system needs to be HA.
The input and output you are seeing may change soon in very specific ways. Your system needs to be flexible enough to handle these changes. All data in the output will still be there, but the output format may need to change, such as XML or JSON instead of a delimited file. We may need to add more aggregates, such as overall average and average for all credit cards accounts. All aggregates will continue to be per user. In addition to credit card, bank/loan, and mortgage, other kinds of accounts may be added in the future, but their element hierarchy will remain the same.
Due to time and space limitations we can’t state all possible requirements here, such as which account numbers are allowed. For anything you think is important but not described here, make up requirements. For example, you'll need that for validation. In your answer, state all your requirement assumptions. The system you describe will need to fulfill them.
Your answer will be composed of two parts:
Part I
Please describe and diagram your overall system design. Real world design documents may leave out specifics like validation/verification, error handling, logging, performance considerations, which specific libraries and frameworks you'll need, and other details. Since this is part of an interview, please include these and any other details you've considered. For your service, include in the diagram and describe which software/hardware/cloud services/storage/cloud platform you'll need.
Part II

Please describe the code used to parse and process the data. Please use UML to describe your class hierarchy and design patterns. Add notes for any details that can't be expressed in UML or some equivalent. Please orient your UML to Java. Actual implementation is not necessary.
In design there are many options and trade-offs. Please explain advantages and disadvantages of your choices and reasons behind choosing your design patterns.
