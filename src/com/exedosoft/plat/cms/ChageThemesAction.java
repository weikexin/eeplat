package com.exedosoft.plat.cms;

import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class ChageThemesAction extends DOAbstractAction{

	private static final String DEFAULT_THEMES_KEY = "themes_dir" ;
	private static final String DEFAULT_THEMES_VALUE = "default" ;

	public String excute() throws ExedoException {

		String param = DOGlobals.getInstance().getSessoinContext().getFormInstance().getValue("theme_dir");
		DOService service = DOService.getService("cms_options_browse_by_key");
		BOInstance bo = new BOInstance();
		bo.putValue("opt_key", DEFAULT_THEMES_KEY);
		List l = service.invokeSelect(bo);
		if (l.isEmpty()){
			service = DOService.getService("cms_options_insert");
			bo.putValue("opt_key", DEFAULT_THEMES_KEY);
			bo.putValue("opt_value", DEFAULT_THEMES_VALUE);
			service.invokeAll(bo);
			return DEFAULT_FORWARD;
		}else {
			BOInstance boi = (BOInstance) l.get(0);
			service = DOService.getService("cms_options_update");
			bo.putValue("opt_key", DEFAULT_THEMES_KEY) ;
			bo.putValue("opt_value", param) ;
			bo.putValue("cms_options_currunt", boi.getValue("opt_id")) ;
			service.invokeAll(bo);
			this.setInstance(bo);
			return DEFAULT_FORWARD;

		}
	}

}
