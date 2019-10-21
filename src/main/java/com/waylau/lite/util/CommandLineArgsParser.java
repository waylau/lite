/**
 * Welcome to https://waylau.com
 */
package com.waylau.lite.util;

/**
 * CommandLineArgsParser.
 * 
 * @see CommandLineArgs
 * @since 1.0.0 2018年8月18日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
public class CommandLineArgsParser {

	/**
	 * Parse the given {@code String} array based on the rules described
	 * {@linkplain CommandLineArgsParser above}, returning a fully-populated
	 * {@link CommandLineArgs} object.
	 * 
	 * @param args command line arguments, typically from a {@code main()} method
	 * @return commandLineArgs
	 */
	public static CommandLineArgs parse(String... args) {
		CommandLineArgs commandLineArgs = new CommandLineArgs();
		for (String arg : args) {
			if (arg.startsWith("--")) {
				String argText = arg.substring(2, arg.length());
				String argName;
				String argValue = null;
				if (argText.contains("=")) {
					argName = argText.substring(0, argText.indexOf("="));
					argValue = argText.substring(argText.indexOf("=") + 1, 
							argText.length());
				} else {
					argName = argText;
				}
				if (argName.isEmpty() 
						|| (argValue != null && argValue.isEmpty())) {
					throw new IllegalArgumentException("Invalid argument syntax: " + arg);
				}
				commandLineArgs.addArg(argName, argValue);
			}

		}
		return commandLineArgs;
	}

}
