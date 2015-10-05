package com.simpleweb.beans;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable {
	private long id;
	private String name;
	private Date birth;
	
	public void setId(long id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setBirth(Date bir) { this.birth = bir; }

	public long getId() { return id; }
	public String getName() { return name; }
	public Date getBirth() { return birth; }

	public boolean equals(Object other)
	{
		return (other instanceof User) && id == ((User)other).id;
	}
	public String toString()
	{
		return String.format("User[id=%d,name=%s,birth=%d]", id, name, birth);
	}
}
