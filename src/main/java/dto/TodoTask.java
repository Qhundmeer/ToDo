package dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.type.descriptor.java.LocalDateTimeJavaDescriptor;

import lombok.Data;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

@Entity
@Data
public class TodoTask {
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	int id;
	String taskName;
	String taskDescription;
	@CreationTimestamp
	LocalDateTime date;
	boolean status;
	@ManyToOne
	TodoUser task;	
}
