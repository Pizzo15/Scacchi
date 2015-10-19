package it.luca.chessgame.model;

import it.luca.chessgame.view.View;

public class TilesModel implements Model {
	private Configuration configuration;
	private View view;

	public TilesModel(Configuration configuration){
		this.configuration = configuration;
	}
	
	public Pezzo at(int x, int y){
		return configuration.at(x, y);
	}
	
	@Override
	public Configuration getConfiguration() {
		return configuration;
	}

	@Override
	public void setConfiguration(Configuration configuration) {
		if(this.configuration != configuration){
			this.configuration = configuration;
			if(view != null)
				view.onConfigurationChange();
		}
	}
	
	@Override
	public void setView(View view){
		this.view = view;
	}
}
