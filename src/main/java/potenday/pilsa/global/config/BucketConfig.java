package potenday.pilsa.global.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BucketConfig {
    @Value("${ncp.bucket.endPoint}")
    private String endPoint;
    @Value("${ncp.bucket.regionName}")
    private String regionName;
    @Value("${ncp.bucket.access-key}")
    private String accessKey;
    @Value("${ncp.bucket.secret-key}")
    private String secretKey;

    public AmazonS3 S3Client() {
        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }
}
