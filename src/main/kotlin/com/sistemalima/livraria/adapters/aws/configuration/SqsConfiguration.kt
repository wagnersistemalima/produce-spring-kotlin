package com.sistemalima.livraria.adapters.aws.configuration

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class SqsConfiguration {

    @Value("\${cloud.aws.region.static}")
    var region : String = ""

    @Value("\${cloud.aws.end-point.uri}")
    var uri: String =  ""

    @Value("\${cloud.aws.credentials.access-key}")
    var accessKey: String = ""

    @Value("\${cloud.aws.credentials.secret-key}")
    var secretKey: String = ""


    @Bean
    fun queueMessageTemplate(amazonSQSAsync: AmazonSQSAsync): QueueMessagingTemplate {
        return QueueMessagingTemplate(amazonSQSAsync)
    }

    @Bean
    @Primary
    fun amazonSqs(): AmazonSQSAsync {
        return AmazonSQSAsyncClientBuilder.standard()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(uri, region))
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .build()
    }
}