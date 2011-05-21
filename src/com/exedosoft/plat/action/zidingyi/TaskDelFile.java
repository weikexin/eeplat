package com.exedosoft.plat.action.zidingyi;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import com.exedosoft.plat.util.DOGlobals;

public class TaskDelFile extends TimerTask {
	private String filePath;

	public TaskDelFile(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		File zipFile = new File(filePath);
		if (zipFile.exists()) {
			System.out.println("正在删除文件......");
			zipFile.delete();
		}
		System.out.println("文件已删除。");
		String uploadPath = DOGlobals.UPLOAD_TEMP;
		File uploadFile = new File(uploadPath);
		File[] files;
		files = uploadFile.listFiles();
		for (File fileName : files) {
			String filepath = fileName.getName();
			String fileAbsPath = fileName.getAbsolutePath();
			if (fileAbsPath.endsWith(".zip") && filepath.length() > 13) {
				if (filepath.substring(0, filepath.length() - 4).matches(
						"^\\d+$")) {
					String fileSub = filepath.substring(0, 8);
					String nowstr = OperationUtil.getDateTimeString();
					String nowSub = nowstr.substring(0, 8);
					int fileInt = Integer.parseInt(fileSub);
					int nowInt = Integer.parseInt(nowSub);
					if (fileInt < nowInt - 1) {
						fileName.delete();
						System.out.println("删除文件:" + filepath);
					}
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String uploadPath = DOGlobals.UPLOAD_TEMP;
		File uploadFile = new File(uploadPath);
		File[] files;
		files = uploadFile.listFiles();
		for (File fileName : files) {
			String filepath = fileName.getName();
			String fileAbsPath = fileName.getAbsolutePath();
			if (fileAbsPath.endsWith(".zip") && filepath.length() > 13) {
				if (filepath.substring(0, filepath.length() - 4).matches(
						"^\\d+$")) {
					String fileSub = filepath.substring(0, 8);
					String nowstr = OperationUtil.getDateTimeString();
					String nowSub = nowstr.substring(0, 8);
					int fileInt = Integer.parseInt(fileSub);
					int nowInt = Integer.parseInt(nowSub);
					if (fileInt < nowInt - 1) {
						fileName.delete();
						System.out.println("删除文件:" + filepath);
					}
				}
			}
		}
	}

}
