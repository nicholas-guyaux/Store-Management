package model;

public class Employee {
	private int mId;
	private String mName;
	private String mUsername;
	private String mPassword;
	private boolean mIsManager;
	
	public Employee(int id, String userName, String name, String password, boolean isManage) {
		mId = id;
		mName = name;
		mUsername = userName;
		mPassword = password;
		mIsManager = isManage;
				
	}
	
	public int getId() {
		return mId;
	}

	public String getName() {
		return mName;
	}
	
	public String getUsername() {
		return mUsername;
	}
	
	public String getPassword() {
		return mPassword;
	}
	
	public boolean isManager() {
		return mIsManager;
	}
	
	public void setName(String name) {
		mName = name;
	}
	
	public void setUsername(String username) {
		mUsername = username;
	}
	
	public void setPassword(String password) {
		mPassword = password;
	}
	
	public void setIsManager(boolean isManager) {
		mIsManager = isManager;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Employee){
			Employee otherEmployee = (Employee) other;
			return otherEmployee.mId == mId;
		}else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return new Integer(mId).hashCode();
	}
	@Override
	public String toString() {
		return getName() + "        " + getUsername() + "        " + mId ;
	}



}
