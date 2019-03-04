package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class QuizToQuizIdTypeAdapter extends TypeAdapter<List<Quiz>> {

	@Override
	public List<Quiz> read(JsonReader in) throws IOException {
		
		DBManager dbManager = DBManager.getInstance();
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		while(in.hasNext()) {
			
			int quizId = in.nextInt();
			Quiz quiz = dbManager.getQuizById(quizId);
			quizzes.add(quiz);
		}
		
		return quizzes;
	}

	@Override
	public void write(JsonWriter out, List<Quiz> quizzes) throws IOException {
		
		
		
		out.beginArray();
		for(int i = 0; i < quizzes.size(); i++) {
			int quizId = quizzes.get(i).getQuizid();
			out.value(quizId);
		}
		out.endArray();
	}

}
