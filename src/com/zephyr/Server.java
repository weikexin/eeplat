package com.zephyr;

import com.zephyr.erpscan.framework.util.Global;

public class Server {
	public static void main(String[] args) {
		System.out.println("server start ...");
		
		int status = 0;
		Ice.Communicator ic = null;
		try {
			String ip = Global.getERPScanProperties().getProperty("ice.server.ip");
			String port = Global.getERPScanProperties().getProperty("ice.server.port");
			
			ic = Ice.Util.initialize(args);
			Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints(
					"ScanSrv", "default -p " + port + " -h " + ip);
			Ice.Object object = new ScanSrvI();
			adapter.add(object, Ice.Util.stringToIdentity("ScanSrv"));
			adapter.activate();
			ic.waitForShutdown();
		} catch (Ice.LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			status = 1;
		} finally {
			if (ic != null)
				ic.destroy();
		}
		System.exit(status);
	}
}
