# AWS LOCAL
- aws local samples

## S3
- create bucket

```bash
aws s3 mb s3://$BUCKET --endpoint http://localhost:4572 --region ap-northeast-1
```

- get head object

```bash
aws s3api head-object --bucket $BUCKET --key $KEY --endpoint http://localhost:4572 --region ap-northeast-1
```

- get partial head object
    - query values to sample
    
```bash
aws s3api head-object --bucket $BUCKET --key $KEY --query "[ContentLength, ContentType]" --endpoint http://localhost:4572 --region ap-northeast-1
```

### References
- [Classmethod: S3オブジェクトのContent-TypeをAWS CLIで一括で変更する](https://dev.classmethod.jp/cloud/aws/change-content-type-by-aws-cli/)
