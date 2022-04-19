package app.model.layers.integration.line;

import app.model.business.line.DTOLine;

public interface LineDAO {
	public DTOLine findLine(String id);
	public void saveLine(DTOLine line);
	public DTOLine createLine(DTOLine line);
}
