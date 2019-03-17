package model;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class ShowAnswerTypeAdapter extends TypeAdapter<Answer> {

	@Override
	public Answer read(JsonReader arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(JsonWriter writer, Answer answer) throws IOException {
		
		writer.beginObject();
		
		writer.name("answerid").value(answer.getAnswerid());
		writer.name("text").value(answer.getText());
		writer.name("points").value(answer.getPoints());
		writer.name("correct").value(answer.isCorrect());
		
		writer.endObject();
	}

}
