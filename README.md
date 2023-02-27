# yournameandsurname
Shoprite Java Challenge

Changes I would make in order to make it prod ready:

a) The application would receive requests using sqs fifo because the order matters, e.g: a customer can only make deposits once he has an account.,
I would provision a DLQ for all the requests the service fails to process so that we can investigate and attempt to process again.

b) I would not save the new accounts individually, but I would process them in a batch.
c) I would separate the components in the service into multiple microservice, so there would be a service specifically for the business rules,
and then a different service which takes care of making crud database transactions.

This strategy would make it easier and quicker to make changes to business rules,
without having a major impact (e.g downtime) on the other service.

Also in terms of scaling, we might need more instances running for the business rules service because it has to process all the requests,
do some validation, send some to the dead letter queue and then the validated requests to the sqs which the transaction service will pull from.
So they will have different auto scaling groups as well as docker images.

The business rules service can also trigger smses to the customers.



You can also test the implementation by calling the endpoints via postman

localhost:8080/AccountController/createSavingsAccount

{
"accountId":"3",
"customerNum":"12",
"amountToDeposit":2000,
"amountToWithdraw":0
}

localhost:8080/AccountController/createChequeAccount

{
"accountId":"0",
"customerNum":"12",
"amountToDeposit":2000,
"amountToWithdraw":0
}


localhost:8080/AccountController/withdrawAccount

{
"accountId":"3",
"customerNum":"12",
"amountToDeposit":0,
"amountToWithdraw":2000
}


localhost:8080/AccountController/depositIntoAccount

{
"accountId":"3",
"customerNum":"12",
"amountToDeposit":2000,
"amountToWithdraw":0
}



