package jp.classmethod.quarkus.sample.service;

import software.amazon.awssdk.services.s3.S3Client;

import javax.inject.Inject;

public class S3ReadOnlyRepository {

    @Inject
    S3Client s3Client;
}
