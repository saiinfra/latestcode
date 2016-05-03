package com.services.component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import com.domain.MetaBean;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.future.ListObjectsFromSourceFutureTask;
import com.util.SFoAuthHandle;

public class FDGetComponentsTypesCompService {

	public FDGetComponentsTypesCompService() {
		super();
	}

	public List<MetaBean> listMetadataObjects(String metadataLogId,String contenType, 
			SFoAuthHandle sfHandle) throws SFException {
		// MetaBean[] objlist = null;
		List<MetaBean> list = new ArrayList<MetaBean>();
		MetaBean bean = null;

		ListObjectsFromSourceFutureTask callable1 = new ListObjectsFromSourceFutureTask(
				metadataLogId, contenType, sfHandle);
		FutureTask<List<MetaBean>> listMetadataObjectFutureTask = new FutureTask<List<MetaBean>>(
				callable1);
		try {
			ExecutorService executor = Executors.newFixedThreadPool(1);
			executor.execute(listMetadataObjectFutureTask);
			while (true) {
				if (listMetadataObjectFutureTask.isDone()) {
					System.out.println("listMetadataObjectFutureTask is Done");
					// shut down executor service
					executor.shutdown();
					list = listMetadataObjectFutureTask.get();
					return list;
				} else if (!listMetadataObjectFutureTask.isDone()) {
					// wait indefinitely for future task to complete
					System.out.println("Waiting on gettting List of objects to complete.....");
					list = listMetadataObjectFutureTask.get();
				}
			}
		} catch (Exception ce) {
			throw new SFException(ce.toString(),
					SFErrorCodes.SF_ListObject_Error);
		}
	}
}
