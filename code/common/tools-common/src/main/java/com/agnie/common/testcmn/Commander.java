package com.agnie.common.testcmn;

import java.util.HashMap;
import java.util.Map;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.MissingCommandException;
import com.beust.jcommander.ParameterException;

public class Commander {

	private Map<String, CommandProcessor>	cmdProcessors	= new HashMap<String, CommandProcessor>();

	private JCommander						commander;

	private MainArgs						mainArgs;

	public Commander(MainArgs mainArgs) {
		this.mainArgs = mainArgs;
		commander = new JCommander(mainArgs);
	}

	public void addCommand(String command, CommandProcessor processor) {
		processor.setCommand(command);
		processor.setCommander(commander);
		commander.addCommand(command, processor);
		cmdProcessors.put(command, processor);
	}

	public void processArguments(String... args) throws Exception {
		String command = null;
		try {
			commander.parse(args);
			if (mainArgs.isHelp()) {
				commander.usage();
				return;
			}
			command = commander.getParsedCommand();
		} catch (MissingCommandException e) {
			System.out.println("Invalid option -'" + e.getMessage() + "'");
		} catch (ParameterException ex) {
			System.out.println(ex.getMessage());
		}

		if (command == null || command.isEmpty())
			commander.usage();
		else {
			CommandProcessor processor = cmdProcessors.get(command);
			processor.processCommand();
		}
	}

}
