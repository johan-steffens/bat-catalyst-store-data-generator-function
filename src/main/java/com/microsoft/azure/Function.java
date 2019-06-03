package com.microsoft.azure;

import java.util.*;

import com.github.javafaker.Faker;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.utility.RandomStringUtility;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {

    private static final List<String> regions = Arrays.asList("Kwa-Zulu Natal", "Gauteng", "Western Cape", "Central", "North");

    private Random random = new Random();
    private Faker faker = new Faker();

    @FunctionName("StoreData-GenerateDeltaFile")
    public HttpResponseMessage run(@HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<String>> request, final ExecutionContext context, @BlobOutput(name = "catalystpetrastorage", connection = "AzureWebJobsStorage", path = "store-data-generated/delta-{Query.timestamp}.csv") OutputBinding<String> blob) {
        context.getLogger().info("== Started Store Data generator function ==");

        // Generate between 1 and 10 stores per file
        int stores = random.nextInt(10) + 1;
        String data = "";
        context.getLogger().info(":: generating [" + stores + "] stores");

        for(int i = 0; i < stores; i++) {
            // Generate random values for different fields used in columns of CSV file
            String region = regions.get(random.nextInt(regions.size()));
            String outletCode = new RandomStringUtility(random.nextInt(5) + 10).nextString();
            String outletName = faker.hitchhikersGuideToTheGalaxy().character();
            String status = random.nextInt(10) > 2 ? "Active" : "Inactive";
            String street = faker.address().buildingNumber() + " " + faker.address().streetName() + faker.address().streetSuffix();
            String city = faker.address().city();
            String country = "South Africa";
            String address = String.format("%s, %s, %s, %s", street, city, region, country);
            String channel = "NULL";
            String latitude = faker.address().latitude();
            String longitude = faker.address().longitude();

            // Add the current row
            data += String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", region, "NULL", outletCode, outletName, status, address, city, channel, latitude, longitude);
        }

        // Add the contents to the blob and return success response
        blob.setValue(data);
        return request.createResponseBuilder(HttpStatus.OK).body("Store file successfully generated").build();
    }
}
