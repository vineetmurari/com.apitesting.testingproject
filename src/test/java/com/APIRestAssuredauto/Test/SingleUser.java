package com.APIRestAssuredauto.Test;

public class SingleUser {
	
	private data Data;
	private support Support;
	
	public data getData() {
		return Data;
	}

	public void setData(data data) {
		Data = data;
	}

	public support getSupport() {
		return Support;
	}

	public void setSupport(support support) {
		Support = support;
	}

	
	
	public static class data {
	    private int id;
	    private String email;
	    private String first_name;
	    private String last_name;
	    private String avatar;
	    
	    
	    public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getFirst_name() {
			return first_name;
		}
		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}
		public String getLast_name() {
			return last_name;
		}
		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}
		public String getAvatar() {
			return avatar;
		}
		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}
		
	}
	
	public static class support {
		
		private String url;
	    private String text;
		
	    public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		
	   
	}
	
	
	
}

