package it.luca.chessgame.model;

public abstract class AbstractConfiguration implements Configuration {

	@Override
	public boolean equals(Object other){
		if(other instanceof Configuration){
			Configuration otherAsConfiguration = (Configuration) other;
			for(int y = 0; y < 8; y++)
				for(int x = 0; x < 8; x++)
					if(at(x,y) != otherAsConfiguration.at(x, y))
						return false;
			return true;
		}
		else
			return false;
	}
}
