package rederfiles;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetJsonData {

	public List<HashMap<String, String>> getJasonDatas() throws IOException {
		String path = "C:\\Users\\kgouthamsankar\\eclipse-workspace\\demoParactice\\src\\test\\resources\\getJson.json";

		String jsoncontent = FileUtils.readFileToString(new File(path), "UTF-8");

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsoncontent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

}
/*
 * String path = "\\src\\test\\java\\jsonReaderfile\\getData.json"; String
 * jsonpath = System.getProperty("user.dir")+ path;
 */