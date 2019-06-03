# Catalyst Test Data Generator function
#### Built using Azure Functions for Java 

This project aims to show an example of how to use Azure Functions to generate random data and then store that data to an Azure Blob Storage account.

This project needs to be configured correctly to work.

## Setup

First of all, ensure you've got Maven on your PATH. 

Then run 
```
echo "{
  \"IsEncrypted\": false,
  \"Values\": {
    \"AzureWebJobsStorage\": \"\",
    \"FUNCTIONS_WORKER_RUNTIME\": \"java\"
  }
}" >> local.settings.json
``` 
in whichever terminal you're using. I'm using Git Bash.

## Local development server

1. First run Maven goals `clean` and `package`.
2. Then run Maven command `azure-functions:run`

Watch your run console which will output the URLs you can hit to trigger the functions locally.
