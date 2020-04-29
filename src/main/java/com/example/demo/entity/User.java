package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="users")
public class User implements Serializable { 
//Java provides a mechanism, called object serialization where an object can be represented as a sequence 
//of bytes that includes the object's data as well as information about the object's type and the types of data stored in the object.
//After a serialized object has been written into a file, it can be read from the file and deserialized that is, the type 
//information and bytes that represent the object and its data can be used to recreate the object in memory.

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "userid_generator")
	@SequenceGenerator(name="userid_generator", sequenceName = "userId_seq", allocationSize=50)
	@Column(name="user_id")
	private long userId;
	
	@NonNull
	@NotEmpty(message="Username is empty")
	@Size(min=3, max=30, message="Username must be 3 to 30 characters long")
	@Column(name="username")
	private String username;
	
	@NonNull
	@NotEmpty(message="Password is empty")
	@Size(min=3, max=30, message="Password must be 8 to 30 characters long")
	@Column(name="password")
	private String password;
	
	@NonNull
	@NotEmpty(message="Email is empty")
	@Column(name="email")
	@Email(message="Wrong email pattern")
	private String email;


	
}
