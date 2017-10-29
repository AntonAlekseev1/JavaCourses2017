package com.project.rep;

import com.project.been.Option;

public class OptionRepository {
	private Option[] options;

	public Option[] getOption() {
		return options;
	}

	public void setOption(Option[] option) {
		this.options = option;
	}

	public void addOption(Option option) {
		for (int i = 0; i < options.length; i++) {
			if (options[i] == null) {
				options[i] = option;
				break;
			}
		}
	}

	public void removeService(Option option) {
		for (int i = 0; i < options.length; i++) {
			if (options[i] == option) {
				options[i] = null;
				break;
			}
		}
	}

}
