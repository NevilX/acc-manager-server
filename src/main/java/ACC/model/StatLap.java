package ACC.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatLap {
	public int 		lapNo = 0;
	public boolean 	fromPit = false;
	public boolean 	toPit = false;
	public int 		lapTime = 0;
	public float 	distanceTraveled = 0;
	
	public List<Integer> splitTimes = new ArrayList<>();
	
	public float fuelAdded = 0;
	public float fuelUsed = 0;
	public float fuelLeftOnStart = 0;
	public float fuelLeftOnEnd = 0;
	public float fuelAVGPerMinute = 0;
	public Map<Integer,Integer> maps = new HashMap<>();
	public int rainTyres;	//Are rain tyres equipped
	public boolean isValidLap = true;
}