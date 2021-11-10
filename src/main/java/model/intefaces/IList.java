package model.intefaces;

import model.List;

public interface IList {
	public boolean addList();
	public List getListById(int id);
	public java.util.List<List> getAllLists();
	public boolean updateList();
	public boolean deleteList();
}
