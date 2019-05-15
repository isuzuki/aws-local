package io.github.isuzuki.s3

import java.io.{File, FileInputStream, InputStream}

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.{ObjectMetadata, PutObjectRequest}

import scala.util.Random

object PutSample extends App {
  val s3Client = AmazonS3ClientBuilder
    .standard()
    .withPathStyleAccessEnabled(true) // error when not exists
    .withEndpointConfiguration(new EndpointConfiguration(
      "http://localhost:4572",
      Regions.AP_NORTHEAST_1.getName
    ))
    .build()

  val bucketName = "test"
  val file = new File(getClass.getResource("/test.png").getPath)

  val randomStr = Random.alphanumeric.take(5).mkString

  // normal upload
  // uploaded file head object { "ContentType": "image/png" }
  val putRequest = new PutObjectRequest(bucketName, s"$randomStr-${file.getName}", file)
  s3Client.putObject(putRequest)

  // wrong file (file name extension is jpg but file actually is png.) extension upload
  // uploaded file head object { "ContentType": "image/jpeg" }
  val file2 = new File(getClass.getResource("/wtest.jpg").getPath)
  val putRequest2 = new PutObjectRequest(bucketName, s"$randomStr-wrong-ext-${file.getName}", file2)
  s3Client.putObject(putRequest2)

  // empty metadata upload
  // uploaded file head object { "ContentType": "application/octet-stream" }
  val input = new FileInputStream(file)
  val metadata = new ObjectMetadata()
  val putRequest3 = new PutObjectRequest(bucketName, s"$randomStr-empty-meta-${file.getName}", input, metadata)
  s3Client.putObject(putRequest3)

  input.close()
}
