package com.tvunetworks.richardyao.threadpool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Dec 12, 2016 4:48:29 PM
 */
public class TestThreadPool {

	public static void main(String[] args) {
		/*
		 * MainUse mainUse = MainUse.getInstance(5); mainUse.useThreadPool();
		 */
		// stackLength();
		/*BookmarkItem result = generateList("11111");
		System.out.println(JSONObject.fromObject(result));*/
		getFileInput();
	}

	private static int stackLength = 1;

	public static void stackLength() {
		stackLength++;
		try {
			stackLength();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BookmarkItem generateList(String id) {
		List<String> childrenIds = getChildrenIds(id);
		if (childrenIds != null && childrenIds.size() > 0) {
			BookmarkItem item = new BookmarkItem();
			item.setId(id);
			item.setName("Name:" + id);
			item.setIdLists(childrenIds);
			List<BookmarkItem> childrenList = new ArrayList<BookmarkItem>();
			for (int i = 0; i < childrenIds.size(); i++) {
				BookmarkItem temp = generateList(childrenIds.get(i));
				if (temp != null) {
					childrenList.add(temp);
				}
			}
			item.setChildrenItems(childrenList);
			return item;
		} else {
			return null;
		}
	}

	public static List<String> getChildrenIds(String id) {
		List<String> result = new ArrayList<String>();
		if (id.length() > 2) {
			for (int i = 1; i < id.length() - 1; i++) {
				result.add(id.substring(i));
			}
		}
		return result;
	}

	public static void getFileInput() {
		String file = "src/error.log";
		String value = stream2String(file, "utf-8");
		System.out.println("---The log content is---");
		System.out.println(value);
		URL fullPath = TestThreadPool.class.getResource("");
		URL rootPath = TestThreadPool.class.getResource("/");
		System.out.println(fullPath.getPath());
		System.out.println(rootPath.getPath());
	}

	public static String stream2String(String filePath, String charset) {
		StringBuffer sb = new StringBuffer();
		try {
			File file = new File(filePath);
			InputStream in = new FileInputStream(file);
			Reader r = new InputStreamReader(in, charset);
			int length = 0;
			for (char[] c = new char[1024]; (length = r.read(c)) != -1;) {
				sb.append(c, 0, length);
			}
			r.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
