package edu.mum.ezstore.config.rest;

import java.io.Serializable;
import java.net.URI;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;



//@JsonInclude(Include.NON_EMPTY) //for Jackson 2.x
public class RestError implements Serializable {
	/**
	 * An absolute URI that identifies the problem type. When dereferenced, it
	 * SHOULD provide human-readable documentation for the problem type (e.g.,
	 * using HTML). When this member is not present, its value is assumed to be
	 * "about:blank".
	 */
	private URI type;

	/**
	 * The HTTP status code generated by the origin server for this occurrence
	 * of the problem.
	 */
	private int httpCode;
	/**
	 * A short, human-readable summary of the problem type. It SHOULD NOT change
	 * from occurrence to occurrence of the problem, except for purposes of
	 * localization.
	 */
	private String title;

	private Integer errorCode;
	private String message;
	private String diagnostic;
	@JsonIgnore
	private String messageOriginal;
	@JsonIgnore
	private String diagnosticOriginal;

	public URI getType() {
		return type;
	}

	public void setType(URI type) {
		this.type = type;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}
	public void setHttpCode(HttpStatus httpCode) {
		this.httpCode = httpCode.value();
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public String getMessageOriginal() {
		return messageOriginal;
	}

	public void setMessageOriginal(String messageOriginal) {
		this.messageOriginal = messageOriginal;
	}

	public String getDiagnosticOriginal() {
		return diagnosticOriginal;
	}

	public void setDiagnosticOriginal(String diagnosticOriginal) {
		this.diagnosticOriginal = diagnosticOriginal;
	}

	@Override
	public String toString() {
		return String.format("HTTP %d {errorCode:%d, message:%s, diagnostic:%s}", httpCode, errorCode,
				message, diagnosticOriginal);
	}
}