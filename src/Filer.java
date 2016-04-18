import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Filer {
	
	int id;
	
	public Filer(int id) {
		this.set(id);
	}
	public void set(int id){
		this.id = id;
	}
	public String loadFile(String key){
		String result = "";
		File file = new File(key+".txt");
		try {
			 
            Scanner scanner = new Scanner(file);
 
            while (scanner.hasNextLine()) {
                result = scanner.nextLine();
                //System.out.println(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		Map map = new Map(result,key);
		return result;
	}
	public void saveFile(String level, String levelName){
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter(new FileWriter(levelName+".txt"));
			writer.write(level);
		}
		catch (IOException e)
		{
		}
		finally {
				try {
					if (writer != null)
						writer.close();
				}
				catch (IOException e){
				}
			}
		}
	public String returnExistingMaps(){
		String result = "";
		for (Map m : MazeController.AllMaps){
			result += m.levelName + " " + m.level.toString() + "\n";
		}
		return result;
	}
}
