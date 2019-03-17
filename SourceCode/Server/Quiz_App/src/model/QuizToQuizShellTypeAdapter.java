package model;

import java.io.IOException;
import java.util.List;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class QuizToQuizShellTypeAdapter extends TypeAdapter<Quiz> {

	@Override
	public Quiz read(JsonReader reader) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(JsonWriter writer, Quiz quiz) throws IOException {
		
		writer.beginObject();
		
		writer.name("quizid").value(quiz.getQuizid());
		writer.name("name").value(quiz.getName());
		
		writer.endObject();
	}

}
