package de.morigm.howfluffybot;

import java.io.File;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main extends TelegramLongPollingBot
{
	public static Config config;
	
    public static void main(String[] args)
    {
    	Main.config = new Config();
		try 
		{
			String path = new File(".").getCanonicalPath();
			System.load(path + "/libhowfluffybot.so");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
    	ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        
        try
        {
        	telegramBotsApi.registerBot(new Main());
        }
        catch (Exception error)
        {
        	error.printStackTrace();
        }
    }
    
	@Override
	public void onUpdateReceived(Update update) 
	{
		if (update.getMessage() != null && update.getMessage().hasText())
		{
			try 
			{
				execute(Creater.createHelpMessage(update.getMessage().getChatId()));
			}
			catch (Exception e) {e.printStackTrace();}
		}
		if (update.getInlineQuery() != null)
		{
			try 
			{
				handleInlineQuery(update);
			}
			catch (Exception e) {e.printStackTrace();}
		}
		System.out.println(update);
	}

	private void handleInlineQuery(Update update) throws TelegramApiException 
	{
		InlineQueryResultArticle calc = Creater.createInlineQueryResultArticle("Calculate how Fluffy you are!", "http://morigm.tk/bot/howfluffybot/hair.png", "1", "Send how Fluffy you are", Creater.createCalculateFluffyText(update.getInlineQuery().getFrom()));
		InlineQueryResultArticle check = Creater.createInlineQueryResultArticle("Check how Fluffy you are!", "http://morigm.tk/bot/howfluffybot/hair.png", "2", "Send how Fluffy you are", Creater.createCheckFluffyText());

		AnswerInlineQuery answer = Creater.createAnswerInlineQuery(update, calc, check);
		execute(answer);
	}

	@Override
	public String getBotUsername() 
	{
		return Main.config.getProperty("bot.name");
	}

	@Override
	public String getBotToken() 
	{
		return Main.config.getProperty("bot.key");
	}
}
