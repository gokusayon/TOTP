package com.bits.ns.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
 * User entity
 * 
 * @author Vasu
 *
 */
@Entity
@Table(name = "users",uniqueConstraints = @UniqueConstraint(columnNames = {"username", "app_id"}))
public class User implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	long id;

	@Column(name = "username", nullable = false)
	String username;

	@Column(name = "seed", nullable = false)
	String seed;


	@Column(name = "T0", nullable = false)
	long T0;

	@Column(name = "X", nullable = false)
	long X;
	
	@Column(name = "count_since_last_test")
	int count;
	
	@Column(name = "last_test_time")
	Date lastTestTime;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getLastTestTime() {
		return lastTestTime;
	}

	public void setLastTestTime(Date lastTestTime) {
		this.lastTestTime = lastTestTime;
	}

	@ManyToOne
	@JoinColumn(name = "app_id", referencedColumnName = "id", nullable = false)
	Application application;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

	public long getT0() {
		return T0;
	}

	public void setT0(long t0) {
		T0 = t0;
	}

	public long getX() {
		return X;
	}

	public void setX(long x) {
		X = x;
	}

	public long getTestTime() {
		return System.currentTimeMillis() / 1000L;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public String toString() {
		return "User [userName=" + username + ", application=" + application.getAppName()  + "]";
	}


}
