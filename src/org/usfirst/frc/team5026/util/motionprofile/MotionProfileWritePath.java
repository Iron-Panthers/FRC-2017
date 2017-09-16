package org.usfirst.frc.team5026.util.motionprofile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ctre.CANTalon.TrajectoryPoint;

public class MotionProfileWritePath {
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
	public static void writeFile(List<TrajectoryPoint> lefts, List<TrajectoryPoint> rights, String filename) {
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
			for (int i = 0; i < lefts.size(); i++) {
				bw.write("p:"+lefts.get(i).position+":v:"+lefts.get(i).velocity+":t:"+lefts.get(i).timeDurMs+":last:"+lefts.get(i).isLastPoint+"\n");
				bw.write("p:"+rights.get(i).position+":v:"+rights.get(i).velocity+":t:"+rights.get(i).timeDurMs+":last:"+rights.get(i).isLastPoint+"\n");
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static TrajectoryPoint[][] readFile(String filename) {
		List<TrajectoryPoint> lefts = new ArrayList<TrajectoryPoint>();
		List<TrajectoryPoint> rights = new ArrayList<TrajectoryPoint>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			int lNum = 0;
			while ((line = reader.readLine()) != null) {
//				System.out.print(line);
				String[] strFormat = line.split(":");
				TrajectoryPoint p = new TrajectoryPoint();
				p.position = Double.parseDouble(strFormat[1]); // pos
				p.velocity = Double.parseDouble(strFormat[3]);
				p.profileSlotSelect = 0;
				p.timeDurMs = Integer.parseInt(strFormat[5]);
				p.isLastPoint = Boolean.parseBoolean(strFormat[7]);
				if (lNum % 2 == 0) {
					lefts.add(p);
				} else {
					rights.add(p);
				}
				lNum+=2;
			}
			reader.close();
			TrajectoryPoint[] a = null;
			TrajectoryPoint[] b = null;
			lefts.toArray(a);
			rights.toArray(b);
			
			TrajectoryPoint[][] out = {a,b};
			return out;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
