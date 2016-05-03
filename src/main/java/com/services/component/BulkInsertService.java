package com.services.component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import com.domain.MetaBean;
import com.exception.SFErrorCodes;
import com.exception.SFException;
import com.future.BulkInsertFutureTask;
import com.util.SFoAuthHandle;

public class BulkInsertService {

	public List<MetaBean> insertInto(String sobjectType,
			String sampleFileName, SFoAuthHandle sfBaseHandle) throws SFException {
		List<MetaBean> list = new ArrayList<MetaBean>();
		Boolean returnFlag = new Boolean(false);

		BulkInsertFutureTask callable1 = new BulkInsertFutureTask(sobjectType, sampleFileName, sfBaseHandle);
		FutureTask<Boolean> bulkInsertFutureTask = new FutureTask<Boolean>(
				callable1);
		try {
			ExecutorService executor = Executors.newFixedThreadPool(1);
			executor.execute(bulkInsertFutureTask);
			while (true) {
				if (bulkInsertFutureTask.isDone()) {
					System.out.println("BulkInsertFutureTask is Done");
					// shut down executor service
					executor.shutdown();
					returnFlag = bulkInsertFutureTask.get();
					return list;
				} else if (!bulkInsertFutureTask.isDone()) {
					// wait indefinitely for future task to complete
					System.out.println("Waiting on bulk insert to complete.....");
					returnFlag = bulkInsertFutureTask.get();
				}
			}
		} catch (Exception ce) {
			throw new SFException(ce.toString(),
					SFErrorCodes.SF_BulkInsert_Error);
		}
	}
}
