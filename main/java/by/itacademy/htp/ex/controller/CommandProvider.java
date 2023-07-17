package by.itacademy.htp.ex.controller;

import java.util.HashMap;
import java.util.Map;

import by.itacademy.htp.ex.controller.impl.DoRegistration;
import by.itacademy.htp.ex.controller.impl.DoSignIn;
import by.itacademy.htp.ex.controller.impl.DoSignOut;
import by.itacademy.htp.ex.controller.impl.GoToAddNews;
import by.itacademy.htp.ex.controller.impl.GoToBasePage;
import by.itacademy.htp.ex.controller.impl.GoToEditNews;
import by.itacademy.htp.ex.controller.impl.GoToNewsList;
import by.itacademy.htp.ex.controller.impl.GoToRegistrationPage;
import by.itacademy.htp.ex.controller.impl.GoToViewNews;
import by.itacademy.htp.ex.controller.impl.ChangeLocale;
import by.itacademy.htp.ex.controller.impl.DoAddNews;
import by.itacademy.htp.ex.controller.impl.DoEditNews;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
		commands.put(CommandName.DO_SIGN_IN, new DoSignIn());
		commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());
		commands.put(CommandName.GO_TO_NEWS_LIST, new GoToNewsList());
		commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
		commands.put(CommandName.GO_TO_EDIT_NEWS, new GoToEditNews());
		commands.put(CommandName.GO_TO_ADD_NEWS, new GoToAddNews());
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
		commands.put(CommandName.DO_ADD_NEWS, new DoAddNews());
		commands.put(CommandName.DO_EDIT_NEWS, new DoEditNews());
		
	}
	
	
	public Command getCommand(String name) {
		CommandName  commandName = CommandName.valueOf(name.toUpperCase());
		Command command = commands.get(commandName);
		return command;
	}

}
