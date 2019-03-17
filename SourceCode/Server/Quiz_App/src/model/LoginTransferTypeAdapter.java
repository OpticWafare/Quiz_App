package model;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LoginTransferTypeAdapter extends TypeAdapter<LoginTransfer> {

	@Override
	public LoginTransfer read(JsonReader arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(JsonWriter out, LoginTransfer loginTransfer) throws IOException {
		
		out.beginObject();
		out.name("user");
		
		User user = loginTransfer.getUser();
		
		if(user == null) {
			out.nullValue();
		}
		else {
			out.beginObject();
			
			out.name("userid").value(user.getUserid());
			out.name("username").value(user.getUsername());
			out.name("email").value(user.getEmail());
			out.name("fcmtoken").value(user.getFcmtoken());
			
			out.name("createdQuizzes").beginArray();
			for(int i = 0; i < user.getCreatedQuizzes().size(); i++) {
				Quiz createdQuiz = user.getCreatedQuizzes().get(i);
				out.beginObject();
				out.name("quizid").value(createdQuiz.getQuizid());
				out.endObject();
			}
			out.endArray();
			
			//out.name("createdQuizzes").nullValue();

			out.name("chosenAnswers").beginArray();
			out.endArray();
			
			//out.name("chosenAnswers").nullValue();
			
			out.name("participatedQuizzes").beginArray();
			for(int i = 0; i < user.getParticipatedQuizzes().size(); i++) {
				QuizForUser participatedQuiz = user.getParticipatedQuizzes().get(i);
				out.beginObject();
				out.name("quiz").beginObject();
				out.name("quizid").value(participatedQuiz.getQuiz().getQuizid());
				out.endObject();
				out.name("answeredtime");
				if(participatedQuiz.getAnsweredtime() == null) {
					out.nullValue();
				}
				else {
					out.value(participatedQuiz.getAnsweredtime().getTime());
				}

				out.endObject();
			}
			out.endArray();
		
			//out.name("participatedQuizzes").nullValue();	
			out.endObject();
		}
		
		out.name("error").value(loginTransfer.getError());
		out.endObject();
	}

}
