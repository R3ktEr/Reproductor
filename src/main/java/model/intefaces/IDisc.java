package model.intefaces;

import java.util.List;

import model.Disc;

public interface IDisc {
	public boolean addDisc();
	public List<Disc> getAllDiscs();
	public Disc getDiscById(int id);
	public boolean updateDisc();
	public boolean deleteDisc();
}
