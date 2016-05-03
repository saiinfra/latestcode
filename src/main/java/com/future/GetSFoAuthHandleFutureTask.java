package com.future;

import java.util.concurrent.Callable;

import com.domain.SFoAuthHandleDO;
import com.util.SFoAuthHandle;

/**
 * 
 * @author GetSFoAuthHandleFutureTask Used For Providing Connection From
 *         BackGround
 *
 */

public class GetSFoAuthHandleFutureTask implements Callable<SFoAuthHandle> {

	SFoAuthHandleDO sfoAuthHandleDO;

	public GetSFoAuthHandleFutureTask(SFoAuthHandleDO sfoAuthHandleDO) {
		this.sfoAuthHandleDO = sfoAuthHandleDO;
	}

	@Override
	public SFoAuthHandle call() throws Exception {
		SFoAuthHandle sfHandle = null;
		sfHandle = new SFoAuthHandle(sfoAuthHandleDO.getOrgId(),
				sfoAuthHandleDO.getToken(), sfoAuthHandleDO.getServerURL(),
				sfoAuthHandleDO.getRefreshtoken(), sfoAuthHandleDO.getOrgType());
		return sfHandle;
	}
}
