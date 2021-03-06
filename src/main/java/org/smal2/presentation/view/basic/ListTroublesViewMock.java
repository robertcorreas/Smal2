package org.smal2.presentation.view.basic;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IListTroublesView;
import org.smal2.service.trouble.ListTroublesResponse;

public class ListTroublesViewMock implements IListTroublesView {

	private ICommand command;
	private String error;
	private ListTroublesResponse response;

	@Override
	public ICommand getCommand() {
		return command;
	}

	@Override
	public void setCommand(ICommand command) {
		this.command = command;
	}

	@Override
	public String getError() {
		return error;
	}

	@Override
	public void setError(String error) {
		this.error = error;
	}

	@Override
	public ListTroublesResponse getResponse() {
		return response;
	}

	@Override
	public void setResponse(ListTroublesResponse response) {
		this.response = response;
	}
}
