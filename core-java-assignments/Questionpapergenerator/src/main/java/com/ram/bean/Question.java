package com.ram.bean;

public class Question  {
	private int srno;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String correctAns;
	private  Category Category;
	private Complexity complexity;

	public Question() {
	}

	
	public int getSrno() {
		return srno;
	}

	public void setSrno(int srno) {
		this.srno = srno;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getCorrectAns() {
		return correctAns;
	}

	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}

	public  Category getCategory() {
		return Category;
	}

	public void setType(Category type) {
		this.Category = type;
	}

	public Complexity getComplexity() {
		return complexity;
	}

	public void setComplexity(Complexity complexity) {
		this.complexity = complexity;
	}


	@Override
	public String toString() {
		return "Question [srno=" + srno + ", question=" + question
				+ ", option1=" + option1 + ", option2=" + option2
				+ ", option3=" + option3 + ", option4=" + option4
				+ ", correctAns=" + correctAns + ", Category=" + Category
				+ ", complexity=" + complexity + "]";
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Question)) return false;

		Question question1 = (Question) o;

		if (srno != question1.srno) return false;
		return question1.getQuestion().equals(question);
		//return Objects.equals(question, question1.question);
	}

	@Override
	public int hashCode() {
		int result = srno;
		result = 31 * result + (question != null ? question.hashCode() : 0);
		return result;
	}
}
