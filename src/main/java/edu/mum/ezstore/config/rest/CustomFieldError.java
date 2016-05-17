package edu.mum.ezstore.config.rest;

import java.io.Serializable;

public class CustomFieldError implements Serializable{
        private String field;
        private Object rejected;
        private String message;
		public String getField() {
			return field;
		}
		public void setField(String field) {
			this.field = field;
		}
		public Object getRejected() {
			return rejected;
		}
		public void setRejected(Object rejected) {
			this.rejected = rejected;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public CustomFieldError(String field, Object rejected, String message) {
			this.field = field;
			this.rejected = rejected;
			this.message = message;
		}
		public CustomFieldError() {
			// TODO Auto-generated constructor stub
		}
        
}
