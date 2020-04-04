package de.morigm.howfluffybot;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class Creater 
{
	
	public static AnswerInlineQuery createAnswerInlineQuery(Update update, InlineQueryResultArticle ... results)
	{
		AnswerInlineQuery answer = new AnswerInlineQuery();
		answer.setInlineQueryId(update.getInlineQuery().getId());
		answer.setCacheTime(10);
		
		answer.setResults(results);
		
		return answer;
	}
	
	public static InlineQueryResultArticle createInlineQueryResultArticle( String title, String url, String id, String descriptoin, String result)
	{
		InlineQueryResultArticle how = new InlineQueryResultArticle();
		how.setHideUrl(true);
		how.setTitle(title);
		how.setThumbUrl(url);
		how.setDescription(descriptoin);
		how.setId(id);
		
		
		InputTextMessageContent text = new InputTextMessageContent();
		text.setMessageText(result);
		how.setInputMessageContent(text);
		
		how.setReplyMarkup(createInlineKeyboardMarkup(createInlineKeyboardButtonWithSwitch("Share how Fluffy you are")));
		return how; 
	}
	
	public static InlineKeyboardButton createInlineKeyboardButtonWithSwitch(String text)
	{
		InlineKeyboardButton button = new InlineKeyboardButton();
		button.setText(text);
		button.setSwitchInlineQuery("");
		return button;
	}
	
	public static InlineKeyboardMarkup createInlineKeyboardMarkup(InlineKeyboardButton ... buttons)
	{
		InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
		
		List<List<InlineKeyboardButton>> main_list = new ArrayList<>();
		List<InlineKeyboardButton> list = new ArrayList<>();
		
		for (InlineKeyboardButton b : buttons)
			list.add(b);
		
		main_list.add(list);
		markup.setKeyboard(main_list);
		
		return markup;
	}
	
	public static String createCalculateFluffyText(User user)
	{
		String text = "You are %fluffy% Fluffy";
		String fluffy = createCalculateFluffyNumber(user.getId(), user.getUserName());
		text = text.replace("%fluffy%", fluffy);
		
		return text;
	}
	
	public static String createCheckFluffyText()
	{
		String text = "You are %fluffy% Fluffy";
		String fluffy = createCheckFluffyNumber();
		text = text.replace("%fluffy%", fluffy);
		
		return text;
	}
	
	public static SendMessage createHelpMessage(long chatid)
	{
		SendMessage msg = new SendMessage();
		
		msg.setChatId(chatid);
		msg.setReplyMarkup(Creater.createInlineKeyboardMarkup(Creater.createInlineKeyboardButtonWithSwitch("Share how Fluffy you are")));
		msg.setText("Hello, this Bot can check how Fluffy you are.\nYou only need to type @HowFluffyBot.\nSo have Fun with the bot");
		
		return msg;
	}
	
	public static String createCalculateFluffyNumber(int userid, String username)
	{
		if (username == null) username = "";
		
		return Calculator.calculateFluffy(userid, username) + "%";
	}

	public static String createCheckFluffyNumber() 
	{
		return Calculator.checkFluffy() + "%";
	}

}
