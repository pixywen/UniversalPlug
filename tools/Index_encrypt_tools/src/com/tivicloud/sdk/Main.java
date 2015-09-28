package com.tivicloud.sdk;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Main {

	private void saveAsFileWriter(String content, String path) {

		FileWriter fwriter = null;
		try {
			fwriter = new FileWriter(path);
			fwriter.write(content);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fwriter.flush();
				fwriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static String getStreamString(InputStream tInputStream) {
		if (tInputStream != null) {
			try {
				BufferedReader tBufferedReader = new BufferedReader(
						new InputStreamReader(tInputStream));

				StringBuffer tStringBuffer = new StringBuffer();

				String sTempOneLine = new String("");

				while ((sTempOneLine = tBufferedReader.readLine()) != null) {
					tStringBuffer.append(sTempOneLine);

				}
				return tStringBuffer.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					tInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static InputStream getStringStream(String inputString) {

		if (inputString != null && !inputString.trim().equals("")) {

			try {

				ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(
						inputString.getBytes());

				return tInputStringStream;

			} catch (Exception ex) {

				ex.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args) {

		String seed = "000";
		String path = "index_fusion.xml";
		String out_path = "index.dat";

		try {

			File file = new File(path);
			InputStream inputStream = new FileInputStream(file);
			String source = getStreamString(inputStream);
			String target = AesUtil.encrypt(seed, source);
			System.out.println(target);
			inputStream.close();
			
			inputStream=getStringStream(target);

			OutputStream outputStream = new FileOutputStream(out_path);

			int bytesWritten = 0;
			int byteCount = 0;

			byte[] bytes = new byte[40960];

			while ((byteCount = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, bytesWritten, byteCount);
				bytesWritten += byteCount;
			}
			inputStream.close();
			outputStream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
