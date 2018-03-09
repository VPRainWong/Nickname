package com.vp.plugin.sample.swapnickname.actions;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.diagram.IDiagramUIModel;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.model.IProject;

public class SwapNicknameActionControl implements VPActionController {

	@Override
	public void performAction(VPAction arg0) {
		// Obtain project from ProjectManager
		IProject project = ApplicationManager.instance().getProjectManager().getProject();		
		
		IDiagramUIModel[] diagrams = project.toDiagramArray();
		if (diagrams != null && diagrams.length > 0) {
			for (int i = 0; i < diagrams.length; i++) {
				doSwap(diagrams[i]);				
			}
		}		

		IModelElement[] models = project.toModelElementArray();
		if (models != null && models.length > 0) {
			for (int i = 0; i < models.length; i++) {
				IModelElement model = models[i];
				doSwap(model);
			}
		}		
		
	}
	
	private void doSwap(IDiagramUIModel diagram) {
		// retrieve name property from original name and nickname
		String origName = diagram.getName();
		String nickName = diagram.getNickname();
		
		// swap the original name and nickname
		diagram.setName(nickName);
		diagram.setNickname(origName);
		
	    // retrieve the original and nickname plain text description
		String origDescTxt = diagram.getDocumentationWithReferenceModels();
		String nickDescTxt = diagram.getHtmlDocumentationWithReferenceModels();
		
		// swap the original plain text description with nickname plain text description
		diagram.setDocumentation(nickDescTxt);
		diagram.setNickDocumentation(origDescTxt);
		
		// retrieve the original and nickname HTML description
		String origDescHtml = diagram.getHtmlDocumentationWithReferenceModels();
		String nickDescHtml = diagram.getNickHtmlDocumentationWithReferenceModels();
		
		// swap the original HTML description with nickname HTML description
		diagram.setHtmlDocumentation(nickDescHtml);
		diagram.setNickHtmlDocumentation(origDescHtml);
	}
	
	private void doSwap(IModelElement model) {
		try {
			String origName = model.getName();
			String nickName = model.getNickname();
			
			model.setName(nickName);
			model.setNickname(origName);
		} catch (Exception e) {
		}

		try {
			String origDescTxt = model.getDescriptionWithReferenceModels();
			String nickDescTxt = model.getNickDescriptionWithReferenceModels();
							
			model.setDescription(nickDescTxt);
			model.setNickDescription(origDescTxt);
		} catch (Exception e) {
		}
		
		try {
			String origDescHtml = model.getHTMLDescriptionWithReferenceModels();
			String nickDescHtml = model.getNickHTMLDescriptionWithReferenceModels();
							
			model.setHTMLDescription(nickDescHtml);
			model.setNickHTMLDescription(origDescHtml);
		} catch (Exception e) {
		}
		
		IModelElement[] children = model.toChildArray();
		if (children != null && children.length > 0) {
			for (int i = 0; i < children.length; i++) {
				doSwap(children[i]);
			}
		}
	}

	@Override
	public void update(VPAction arg0) {
		// TODO Auto-generated method stub
		
	}

}
