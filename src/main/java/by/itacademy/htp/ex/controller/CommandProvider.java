package by.itacademy.htp.ex.controller;

import java.util.HashMap;
import java.util.Map;

import by.itacademy.htp.ex.controller.impl.DoRegistration;
import by.itacademy.htp.ex.controller.impl.DoSignIn;
import by.itacademy.htp.ex.controller.impl.DoSignOut;
import by.itacademy.htp.ex.controller.impl.DoUpdateUser;
import by.itacademy.htp.ex.controller.impl.GoToAddNews;
import by.itacademy.htp.ex.controller.impl.GoToBasePage;
import by.itacademy.htp.ex.controller.impl.GoToEditNews;
import by.itacademy.htp.ex.controller.impl.GoToNewsList;
import by.itacademy.htp.ex.controller.impl.GoToRegistrationPage;
import by.itacademy.htp.ex.controller.impl.GoToUserDetail;
import by.itacademy.htp.ex.controller.impl.GoToUsersList;
import by.itacademy.htp.ex.controller.impl.GoToViewNews;
import by.itacademy.htp.ex.controller.impl.NoCommand;
import by.itacademy.htp.ex.controller.impl.ChangeLocale;
import by.itacademy.htp.ex.controller.impl.DoAddNews;
import by.itacademy.htp.ex.controller.impl.DoDeleteNews;
import by.itacademy.htp.ex.controller.impl.DoEditNews;

public class CommandProvider {

	private static final Map<CommandName, Command> commands = new HashMap<>();
	private static final CommandProvider instance = new CommandProvider();

	private CommandProvider() {
		commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
		commands.put(CommandName.DO_SIGN_IN, new DoSignIn());
		commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());
		commands.put(CommandName.GO_TO_NEWS_LIST, new GoToNewsList());
		commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
		commands.put(CommandName.GO_TO_EDIT_NEWS, new GoToEditNews());
		commands.put(CommandName.GO_TO_ADD_NEWS, new GoToAddNews());
		commands.put(CommandName.GO_TO_USERS_LIST, new GoToUsersList());
		commands.put(CommandName.GO_TO_USER_DETAIL, new GoToUserDetail());
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
		commands.put(CommandName.DO_ADD_NEWS, new DoAddNews());
		commands.put(CommandName.DO_EDIT_NEWS, new DoEditNews());
		commands.put(CommandName.DO_DELETE_NEWS, new DoDeleteNews());
		commands.put(CommandName.DO_UPDATE_USER, new DoUpdateUser());
		commands.put(CommandName.NO_COMMAND, new NoCommand());

	}

	public Command getCommand(String name) {
		

		CommandName commandName;

		try {

			commandName = CommandName.valueOf(name.toUpperCase());

		} catch (IllegalArgumentException | NullPointerException  e) {
			
		
			commandName = CommandName.NO_COMMAND;

		}


		return commands.get(commandName);

	}

	public static CommandProvider getInstance() {

		return instance;

	}

}
