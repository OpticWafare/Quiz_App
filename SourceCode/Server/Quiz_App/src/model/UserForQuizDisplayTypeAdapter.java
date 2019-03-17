package model;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class UserForQuizDisplayTypeAdapter extends TypeAdapter<User> {

	@Override
	public User read(JsonReader reader) throws IOException {
		// TODO 
		return null;
	}

	@Override
	public void write(JsonWriter writer, User user) throws IOException {
		
		writer.beginObject();
		
		writer.name("userid").value(user.getUserid());
		writer.name("username").value(user.getUsername());
		writer.name("email").value(user.getEmail());
		writer.name("fcmtoken").value(user.getFcmtoken());
		
		writer.name("chosenAnswers").beginArray();
		List<Answer> chosenAnswers = user.getChosenAnswers();
		for(int i = 0; i < chosenAnswers.size(); i++) {
			writer.beginObject();
			writer.name("answerid").value(chosenAnswers.get(i).getAnswerid());
			writer.endObject();
		}
		writer.endArray();
		
		writer.name("participatedQuizzes").beginArray();
		writer.endArray();
		
		writer.endObject();
	}

}
