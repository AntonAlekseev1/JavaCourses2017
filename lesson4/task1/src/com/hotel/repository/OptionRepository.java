package com.hotel.repository;

import com.hotel.been.Guest;
import com.hotel.been.Option;

public class OptionRepository {
	private Option[] options;
	
	public OptionRepository(Integer size) {
		options=new Option[size];
	}

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
	
	public Option getOptionById(Integer id) {
		Option option=null;
		for(int i=0;i<options.length;i++) {
			if(options[i]!=null) {
			if(options[i].getId()==id) {
				option=options[i];
				 break;
			}
			}
		}
		return option;
	}

	public void removeOption(Option option) {
		for (int i = 0; i < options.length; i++) {
			if (options[i] == option) {
				options[i] = null;
				break;
			}
		}
	}

}
