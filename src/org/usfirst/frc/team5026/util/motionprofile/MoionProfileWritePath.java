package org.usfirst.frc.team5026.util.motionprofile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MoionProfileWritePath {
	public static void writeFile(MotionProfilePath p, String filename) {
		// filename ex: /home/lvuser/Path.mpp
		File f = new File(filename);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			for (MotionProfileSegment m : p.points) {
				bw.write(m.toString());
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
