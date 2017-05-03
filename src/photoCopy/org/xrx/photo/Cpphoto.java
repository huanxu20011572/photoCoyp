package photoCopy.org.xrx.photo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Cpphoto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String sourceFolder = "";
		String descFolder = "";
		System.out.println("请输入源文件夹路径，【；】结束：");
		while (true) {
			sourceFolder = s.nextLine();
			if (sourceFolder.endsWith(";"))
				break;
		}
		// s.close();
		System.out.println("请输入目标文件夹路径，【；】结束：");
		while (true) {
			descFolder = s.next();
			if (descFolder.endsWith(";"))
				break;
		}
		System.out.println("源文件夹:" + sourceFolder.substring(0, sourceFolder.length() - 1));
		System.out.println("目标文件夹;" + descFolder.substring(0, descFolder.length() - 1));
		copyFile(sourceFolder.substring(0, sourceFolder.length() - 1),
				descFolder.substring(0, descFolder.length() - 1));
		s.close();
	}

	public static void copyFile(String sourceFolder, String descFolder) {
		File rootfile = new File(sourceFolder);
		if (rootfile.isDirectory()) {
			File[] subFiles = rootfile.listFiles();
			for (File file : subFiles) {
				if (!file.isDirectory()) {
					if (file.getName().endsWith(".jpg") || file.getName().endsWith(".mp4")) {
						System.out.println(file.getName());
						doCopy(file.getAbsolutePath(), descFolder +"\\"+ file.getName());
					}
				} else {
					// System.out.println("文件夹："+f.getName());
					copyFile(file.getAbsolutePath(), descFolder);
				}
			}
			return;
		} else {
			if (rootfile.getName().endsWith(".jpg") || rootfile.getName().endsWith(".mp4")) {
				System.out.println(rootfile.getName());
			}
		}
	}

	public static void doCopy(String sourceFolder, String descFolder) {
		File souceFile = new File(sourceFolder);
		Date dt = new Date(souceFile.lastModified());
		File descFile = new File(descFolder);
		if (descFile.exists()) {
			return;
		}
		File parent = new File(descFile.getParent());
		if (!parent.exists()) {
			parent.mkdirs();
		}

		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(souceFile);
			out = new FileOutputStream(new File(descFolder));
			int byteRead = 0;
			byte[] buffer = new byte[1024];
			while ((byteRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteRead);
			}

			descFile.setLastModified(dt.getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
