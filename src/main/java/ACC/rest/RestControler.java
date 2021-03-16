package ACC.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ACC.model.OutputMessage;
import ACC.model.PageFileStatistics;
import ACC.sharedmemory.ACCSharedMemoryService;

@RestController
public class RestControler {
	
	@Autowired
	ACCSharedMemoryService accSharedMemoryService; 
	
	@GetMapping("/SPageFileStatic")
	public String getStaticJson() {
		return accSharedMemoryService.getPageFile("static").toJSON();
	}
	
	@GetMapping("/SPageFilePhysics")
	public String getPhysicsJson() {
		return accSharedMemoryService.getPageFile("physics").toJSON();
	}
	
	@GetMapping("/SPageFileGraphics")
	public String getGraphicsJson() {
		return accSharedMemoryService.getPageFile("graphics").toJSON();
	}
	
	@GetMapping("/save")
	public String saveSessions() {
		List<String> fieldsStatistics = new ArrayList<String>();
		OutputMessage om = accSharedMemoryService.getPageFileMessage("statistics", fieldsStatistics);
		PageFileStatistics statistics = (PageFileStatistics) om.page;
		statistics.saveToXLSX();
		return statistics.toJSON();
	}
	
	@GetMapping("/getSession")
	public String getSessions(@RequestParam Map<String, String> allParams) {
		List<String> fieldsStatistics = new ArrayList<String>();
		OutputMessage om = accSharedMemoryService.getPageFileMessage("statistics", fieldsStatistics);
		if (allParams.containsKey("range")) {
			Gson gson = new Gson();
			PageFileStatistics s = (PageFileStatistics) om.page;
			switch (allParams.get("range")) {
			case "all" : 
				return om.content;
			case "lastLap":
				return gson.toJson(s.currentSession.lastLap);
			case "currentSession":
				return gson.toJson(s.currentSession);
			default:
				return om.content;	
			}
		}
			else {
				System.out.println(om.content);
				return om.content;
			}
	}
	

}
