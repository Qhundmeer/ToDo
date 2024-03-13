package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.TodoTask;
//import dto.TodoTask;
import dto.TodoUser;

public class TodoDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Todo");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();

	public void saveUser(TodoUser user) {
		et.begin();
		em.persist(user);
		et.commit();
	}

	public List<TodoUser> findEmailById(String email) {

		return em.createQuery("select x from TodoUser x where email=?1").setParameter(1, email).getResultList();
	}

	public void saveTask(TodoTask task) {
		et.begin();
		em.persist(task);
		et.commit();
	}

	public List<TodoTask> fetchTaskByUser(int id) {
		return em.createQuery("select x from TodoTask x where task_id=?1").setParameter(1, id).getResultList();
	}

	public void completedTask(int id) {
		TodoTask task= em.find(TodoTask.class, id);
		task.setStatus(true);
		et.begin();
		em.merge(task);
		et.commit();
	}
	public void deleteTaskById(int id) {
		TodoTask task=em.find(TodoTask.class, id);
		et.begin();
		em.remove(task);
		et.commit();	
	}
	public void editTask(String id,String taskName,String description) {
		TodoTask task =em.find(TodoTask.class, Integer.parseInt(id));
		task.setTaskName(taskName);
		task.setTaskDescription(description);
		et.begin();
		em.merge(task);
		et.commit();	
	}
}
