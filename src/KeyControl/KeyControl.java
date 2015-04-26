package KeyControl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

import GameEvents.*;

public class KeyControl extends KeyAdapter
{
	GameEvents gameEvents;
	private BitSet keyBits= new BitSet(256);

	public KeyControl(GameEvents gameEvents)
	{
		this.gameEvents = gameEvents;
	}

	public void keyPressed(KeyEvent e)
	{
		int keyCode=e.getKeyCode();
		keyBits.set(keyCode);
		gameEvents.setValue(e);
	}
	public void keyReleased(KeyEvent e) 
	{
        int keyCode = e.getKeyCode();
        keyBits.clear(keyCode);
    }
	
	 public boolean isKeyPressed(int keyCode)
	 {
	        return keyBits.get(keyCode);
	  }
}
