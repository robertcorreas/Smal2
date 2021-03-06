package org.smal2.infrastructure.presentation.view.http;

import org.smal2.infrastructure.presentation.view.http.util.OperationResponse;
import org.smal2.presentation.presenter.ListTroublesPresenter;
import org.smal2.presentation.view.IListTroublesView;
import org.smal2.presentation.view.basic.ListTroublesViewMock;
import org.smal2.service.trouble.ListTroublesResponse;
import org.smal2.service.trouble.TroubleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/trouble")
public class ListTroublesViewJSON {
	@Autowired
	private TroubleService troubleService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public OperationResponse<ListTroublesResponse> listTroubles() {
		OperationResponse<ListTroublesResponse> response = new OperationResponse<ListTroublesResponse>();

		// TODO [CMP] verify request.getSessionId() permission

		try {
			// [CMP] spring controllers are singleton (as common servlet)
			// so we can't implements IView because his properties are shared
			IListTroublesView view = new ListTroublesViewMock();

			new ListTroublesPresenter(view, troubleService);
			view.getCommand().execute();

			if (view.getError() != null) {
				response.setError(view.getError());
			} else {
				response.setResponse(view.getResponse());
			}
		} catch (Exception ex) {
			response.setError("Unexpected error:\n" + ex.getMessage());
		}

		return response;
	}
}
