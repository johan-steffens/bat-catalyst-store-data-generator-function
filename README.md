# Catalyst Test Data Generator function
#### Built using Azure Functions for Java 

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
