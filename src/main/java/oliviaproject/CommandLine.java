package oliviaproject;

public class CommandLine {
String question, reponse;

public CommandLine(String question, String questionreponse) {
this.question=question;
this.reponse=questionreponse;
}

public String getQuestion() {
	return question;
}

public void setQuestion(String question) {
	this.question = question;
}

public String getReponse() {
	return reponse;
}

public void setReponse(String reponse) {
	this.reponse = reponse;
}

}
