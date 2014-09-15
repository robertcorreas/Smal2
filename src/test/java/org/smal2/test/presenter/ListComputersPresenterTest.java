package org.smal2.test.presenter;

import org.smal2.domain.entity.Computer;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.Position;
import org.smal2.presentation.presenter.ListComputersPresenter;
import org.smal2.presentation.view.IListComputersView;
import org.smal2.service.computer.ListComputersResponse;
import org.smal2.service.computer.ListComputersResponseItem;
import org.smal2.test.presenter.mock.ListComputersViewMock;
import org.smal2.test.testutil.AComputerTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListComputersPresenterTest extends AComputerTest {

	@Before
	public void before() {
		{
			Laboratory lab = new Laboratory("lab01");
			laboratoryRepository.insert(lab);
			{
				Position pos01 = new Position(1, 1, lab);
				positionRepository.insert(pos01);

				computerRepository.insert(new Computer("asset01", pos01));
			}
			{
				Position pos02 = new Position(1, 2, lab);
				positionRepository.insert(pos02);

				computerRepository.insert(new Computer("asset02", pos02));
			}
		}
		{
			Laboratory lab02 = new Laboratory("lab02");
			laboratoryRepository.insert(lab02);
			{
				Position pos04 = new Position(1, 1, lab02);
				positionRepository.insert(pos04);

				computerRepository.insert(new Computer("asset04", pos04));
			}
		}
	}

	@Test
	public void listComputersOnExistentLaboratoryNameMustReturnAllComputers() {
		// Arrange
		IListComputersView view = new ListComputersViewMock();

		// Act
		new ListComputersPresenter(view, computerService);
		view.setRequest("lab01");
		view.getCommand().execute();

		// Assert
		ListComputersResponse response = view.getResponse();
		Assert.assertEquals(2, response.size());

		ListComputersResponseItem r1, r2;

		if (response.get(0).getAssetCode() == "asset01") {
			r1 = response.get(0);
			r2 = response.get(1);
		} else {
			r1 = response.get(1);
			r2 = response.get(0);
		}

		Assert.assertEquals("asset01", r1.getAssetCode());
		Assert.assertEquals(1, r1.getRowNum());
		Assert.assertEquals(1, r1.getColumnNum());
		Assert.assertEquals("asset02", r2.getAssetCode());
		Assert.assertEquals(1, r2.getRowNum());
		Assert.assertEquals(2, r2.getColumnNum());
	}
}