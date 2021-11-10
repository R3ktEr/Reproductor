package model.intefaces;

import model.Comment;

public interface IComment {
	public boolean addComment();
	public Comment getCommentById(int id);
	public java.util.List<Comment> getAllUserComments(int user);
	public java.util.List<Comment> getAllListComments(int list);
	public boolean updateComment();
	public boolean deleteComment();
}
