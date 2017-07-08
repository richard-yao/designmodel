package com.tvunetworks.richardyao.threadpool;

import java.util.List;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Dec 15, 2016 2:22:00 PM
 */
public class BookmarkItem {
	
	private String id;
	private String name;
	private List<String> idLists;
	private List<BookmarkItem> childrenItems;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getIdLists() {
		return idLists;
	}
	public void setIdLists(List<String> idLists) {
		this.idLists = idLists;
	}
	public List<BookmarkItem> getChildrenItems() {
		return childrenItems;
	}
	public void setChildrenItems(List<BookmarkItem> childrenItems) {
		this.childrenItems = childrenItems;
	}

}
