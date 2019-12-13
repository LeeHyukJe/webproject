package com.leehyukje.webproject.search.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class IndexingLogServiceImpl implements IndexingLogService{

	@Override
	public String showIndexingLog(File file) {
		// TODO Auto-generated method stub
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		StringBuffer strb;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			strb = new StringBuffer();
			String str;
			while((str=bufferedReader.readLine())!=null) {
				strb.append(str);
				strb.append("<br/>");
			}

			return strb.toString();
		}catch(IOException e) {
			//e.printStackTrace();
			return e.getMessage();
		}catch(Exception e) {
			//e.printStackTrace();
			return e.getMessage();
		}finally {
			try {
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//return strb.toString();
	}

}
