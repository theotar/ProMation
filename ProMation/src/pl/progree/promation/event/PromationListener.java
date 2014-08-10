/**
 * 
 */
package pl.progree.promation.event;

import java.util.EventListener;

/**
 * @author Wojciech Pierzchalski, Progree
 *
 */
public interface PromationListener extends EventListener {
	public void projectAdded(PromationeEvent e);
	public void szafaSystemowaAdded(PromationeEvent e);
	public void modulAdded(PromationeEvent e);
	
}
