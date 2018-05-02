package com.rkouchoo.fm.actions;

import com.rkocuhoo.fm.interfaces.ActionManagerInterface;
import com.rkouchoo.fm.FileManager;

public class ActionManager implements ActionManagerInterface {

	private FileManager manager;
	private boolean hasConstructed;
	
	private RenameFileAction rename;
	private DeleteFileAction delete;
	private NewFileAction newFile;
	
	public ActionManager(FileManager man) {
		this.manager = man;

		rename = new RenameFileAction(this);
		delete = new DeleteFileAction(this);
		newFile = new NewFileAction(this);
		
		hasConstructed = true;
	}
	
	@Override
	public void renameFile() {
		rename.run();
	}

	@Override
	public void deleteFile() {
		delete.run();
	}

	@Override
	public void newFile() {
		newFile.run();
	}

	@Override
	public FileManager getManager() {
		if (hasConstructed) {
			return manager;
		} else {
			return null;
			// Should return mock manager! which needs to be created.
		}
	}

}
