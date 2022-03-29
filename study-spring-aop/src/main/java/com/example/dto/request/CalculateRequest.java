package com.example.dto.request;

public class CalculateRequest {
	private Operation operation;
	private double left;
	private double right;

	public CalculateRequest() {
	}

	public Operation getOperation() {
		return operation;
	}

//	public void setOperation(Operation operation) {
//		System.err.println("CalculateRequest::setOperation");
//		this.operation = operation;
//	}

	public double getLeft() {
		return left;
	}

	public void setLeft(double left) {
		System.err.println("CalculateRequest::setLeft");
		this.left = left;
	}

	public double getRight() {
		return right;
	}

	public void setRight(double right) {
		System.err.println("CalculateRequest::setRight");
		this.right = right;
	}

}
