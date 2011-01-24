package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.jquery.form.DOBaseForm;

public class DOSelectTree extends DOBaseForm {


	@Override
	public String getHtmlCode(DOIModel dm) {
		
		DOFormModel aFm = (DOFormModel) dm;
		

		DOService parentFolder = aFm.getLinkService();

		DOService subFolder = aFm.getSecondService();

		BOInstance data = aFm.getData();

		StringBuffer buffer = new StringBuffer();

		buffer.append("<select  ");
		buffer.append("  style='width:200px;' ");

		buffer.append(" id=\"").append(aFm.getFullColID()).append("\" ");

		buffer.append(" name=\"").append(aFm.getFullColName()).append(
				"\" ");

		buffer.append(" title='").append(aFm.getL10n().trim()).append("'");

		if (isReadOnly(aFm)) {
			buffer.append(" disabled=\"disabled\" ");
		}

		buffer.append(this.appendValidateConfig(aFm));

		buffer.append(getDecoration(aFm));

		buffer.append(" >\n");
		List parentFolders = parentFolder.invokeSelect();
		buffer.append("<option/>\n");

		for (Iterator itParent = parentFolders.iterator(); itParent.hasNext();) {
			BOInstance biParent = (BOInstance) itParent.next();
			buffer.append("<option value='").append(biParent.getUid()).append(
					"'");
			if (aFm.getValue() != null && aFm.getValue().equals(biParent.getUid())) {
				buffer.append(" selected ");
			}
			buffer.append(">");

			buffer.append(biParent.getName()).append("</option>");
			int layer = 1;
			List subFolders = subFolder.invokeSelect(biParent.getUid());

			if (subFolders != null && subFolders.size() > 0) {
				create(buffer, biParent, subFolders, layer,aFm.getValue(),subFolder);
			}
		}
		buffer.append("</select>");

		System.out.println(buffer);
		return buffer.toString();

	}

	private void create(StringBuffer buffer, BOInstance biParent,
			List subFolders, int layer, String value,DOService subFolder) {

		for (Iterator itSub = subFolders.iterator(); itSub.hasNext();) {
			BOInstance biSub = (BOInstance) itSub.next();
			buffer.append("<option value='").append(biSub.getUid()).append("'");

			if (value != null && value.equals(biSub.getUid())) {
				buffer.append(" selected ");
			}
			buffer.append(">");

			for (int i = 1; i < layer; i++) {
				buffer.append("&nbsp;&nbsp;│");
			}

			buffer.append("&nbsp;&nbsp;");
			if (itSub.hasNext()) {
				buffer.append("├");
			} else {
				buffer.append("└");
			}
			buffer.append("&nbsp;");
			buffer.append(biSub.getName()).append("</option>");
			List subsubFolders = subFolder.invokeSelect(biSub.getUid());
			if (subsubFolders != null && subsubFolders.size() > 0) {
				create(buffer, biSub, subsubFolders, layer + 1, value,subFolder);
			}
		}
	}

	public static void main(String[] args) {

		DOSelectTree fd = new DOSelectTree();
		DOFormModel dm = DOFormModel
				.getFormModelByID("ac6e1e6249014464984cb9ccaa23b413");
		fd.getHtmlCode(dm);

	}

}
