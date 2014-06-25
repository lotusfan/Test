package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.CannedAccessControlList;
import com.aliyun.openservices.oss.model.GetObjectRequest;
import com.aliyun.openservices.oss.model.OSSObjectSummary;
import com.aliyun.openservices.oss.model.ObjectListing;
import com.aliyun.openservices.oss.model.ObjectMetadata;

/**
 * ��ʾ������չʾ�������OSS�д�����ɾ��һ��Bucket���Լ�����ϴ�������һ���ļ���
 * 
 * ��ʾ�������ִ�й����ǣ� 1. ���ָ����Bucket�Ƿ���ڣ�����������򴴽����� 2. �ϴ�һ���ļ���OSS�� 3. ��������ļ������أ� 4. ���������Դ��ɾ��Bucket�����е�����Objects��
 * 
 * �����������ʾ������ʱ��Ҫע�⣺ 1. Ϊ��չʾ��ɾ��Bucketʱ������Ҫɾ�����е�Objects, ʾ���������Ϊɾ����ָ����Bucket����Ϊ��Ҫʹ�������Ѿ�����Դ��Bucket���в��ԣ� 2. ��ʹ������API��Ȩ��Կ���ACCESS_ID��ACCESS_KEY������ 3.
 * ��Ҫ׼ȷ�ϴ��õĲ����ļ������޸ĳ���uploadFilePathΪ�����ļ���·���� �޸ĳ���downloadFilePathΪ�����ļ���·���� 4. �ó����Ϊʾ�����룬�����ο��������ܱ�֤�㹻��׳��
 * 
 */
public class Test_AliYum {

	private static final String ACCESS_ID = "sqBujekHqytMNdAc";
	private static final String ACCESS_KEY = "5eIDkvWbDfuSywKdUwObOOUcnlU9mU";
	private static final String OSS_ENDPOINT = "http://oss-cn-qingdao.aliyuncs.com";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		String bucketName = "xcfh-app";
		String key = "aaaaa.jpg";

		String uploadFilePath = "D://aa.jpg";
		String downloadFilePath = "d:/photo.jpg";

		// ����ʹ��ClientConfiguration�������ô����������������Դ����Ȳ�����
		ClientConfiguration config = new ClientConfiguration();
		OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY, config);

		//ensureBucket(client, bucketName);

		try {
			//setBucketPublicReadable(client, bucketName);

			System.out.println("�����ϴ�...");
			uploadFile(client, bucketName, key, uploadFilePath);

			System.out.println("��������...");
			downloadFile(client, bucketName, key, downloadFilePath);
		} finally {
			//deleteBucket(client, bucketName);
		}
	}

	// ���Bucket�����ڣ��򴴽�����
	private static void ensureBucket(OSSClient client, String bucketName) throws OSSException, ClientException {

		if (client.isBucketExist(bucketName)) {
			return;
		}

		//����bucket
		client.createBucket(bucketName);
	}

	// ɾ��һ��Bucket�����е�Objects 
	private static void deleteBucket(OSSClient client, String bucketName) throws OSSException, ClientException {

		ObjectListing ObjectListing = client.listObjects(bucketName);
		List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
		for (int i = 0; i < listDeletes.size(); i++) {
			String objectName = listDeletes.get(i).getKey();
			// �����Ϊ�գ���ɾ��bucket�µ��ļ�
			client.deleteObject(bucketName, objectName);
		}
		client.deleteBucket(bucketName);
	}

	// ��Bucket����Ϊ�����˿ɶ�
	private static void setBucketPublicReadable(OSSClient client, String bucketName) throws OSSException, ClientException {

		//����bucket
		client.createBucket(bucketName);

		//����bucket�ķ���Ȩ�ޣ�public-read-writeȨ��
		client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
	}

	// �ϴ��ļ�
	private static void uploadFile(OSSClient client, String bucketName, String key, String filename) throws OSSException, ClientException,
			FileNotFoundException {

		File file = new File(filename);

		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(0);
		// ������metadata�б���ļ�����
		objectMeta.setContentType("text/xml");

		InputStream input = new FileInputStream(file);
		client.putObject(bucketName, key, input, objectMeta);
	}

	// �����ļ�
	private static void downloadFile(OSSClient client, String bucketName, String key, String filename) throws OSSException, ClientException {

		client.getObject(new GetObjectRequest(bucketName, key), new File(filename));
	}
}
