package com.stark.challenge.infrastructure.config;

import com.google.gson.Gson;
import com.starkbank.Project;
import com.starkbank.Settings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.util.Map;

@Configuration
public class AuthStark {

    @Bean
    public Project authenticated() throws Exception {

        var project = new Project(
                "sandbox",
                "4802170968866816",
                getSecret()
        );

        Settings.user = project;

        return project;
    }

    public static String getSecret() {

        String secretName = "challenge";
        Region region = Region.of("us-east-1");

        // Create a Secrets Manager client
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            // For a list of exceptions thrown, see
            // https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
            throw e;
        }


        return (String) new Gson().fromJson(getSecretValueResponse.secretString(), Map.class).get("primary_key").toString().replace("\r", "");

        // Your code goes here.
    }

}
