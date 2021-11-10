package model.intefaces;

import java.util.List;

import model.User;

public interface IUser {
	public boolean addUser();
	public User getUserById(int id);
	public List<User> getAllUsers();
	public boolean updateUser();
	public boolean deleteUser();
}