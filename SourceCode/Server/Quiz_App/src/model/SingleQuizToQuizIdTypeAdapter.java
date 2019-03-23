package model;

import java.io.IOException;
import java.util.List;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class SingleQuizToQuizIdTypeAdapter extends TypeAdapter<Quiz> {

	@Override
	public Quiz read(JsonReader arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(JsonWriter out, Quiz quiz) throws IOException {
		
		out.beginObject(); 					// {
		out.name("quizid"); 				// "quizid": 
		out.value(quiz.getQuizid()); 		// 5
		out.endObject(); 					// }
	}

}
