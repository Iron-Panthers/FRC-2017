package org.usfirst.frc.team5026.util.motionprofile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
			PrintWriter pw = new PrintWriter(f);
			StringBuilder sb = new StringBuilder();
			sb.append("position");
			sb.append(",");
			sb.append("velocity");
			sb.append(",");
			sb.append("time");
			sb.append(",");
			sb.append("last");
			sb.append(" ");
			
			for (int i = 0; i < lefts.size(); i++) {
				writeToSb(lefts.get(i), sb);
				writeToSb(rights.get(i), sb);
			}
			pw.write(sb.toString());
			System.out.println(sb.toString());
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void writeToSb(TrajectoryPoint p, StringBuilder sb) {
		sb.append(p.position);
		sb.append(",");
		sb.append(p.velocity);
		sb.append(",");
		sb.append(p.timeDurMs);
		sb.append(",");
		sb.append(p.isLastPoint);
		sb.append(" ");
	}
	public static ArrayList<ArrayList<TrajectoryPoint>> readFile(String filename) {
		ArrayList<TrajectoryPoint> lefts = new ArrayList<TrajectoryPoint>();
		ArrayList<TrajectoryPoint> rights = new ArrayList<TrajectoryPoint>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			String fLine = reader.readLine();
			String[] lines = fLine.split(" ");
			int lNum = 1;
//			while ((line = reader.readLine()) != null) {
			for (int i=1; i < lines.length; i++) {
//				System.out.print(line);
				line = lines[i];
				String[] strFormat = line.split(",");
				TrajectoryPoint p = new TrajectoryPoint();
				p.position = Double.parseDouble(strFormat[0]); // pos
				p.velocity = Double.parseDouble(strFormat[1]);
				p.profileSlotSelect = 0;
				p.timeDurMs = Integer.parseInt(strFormat[2]);
				p.isLastPoint = Boolean.parseBoolean(strFormat[3]);
				if (lNum % 2 == 1) {
					lefts.add(p);
				} else {
					rights.add(p);
				}
				lNum+=2;
			}
			reader.close();
			
			ArrayList<ArrayList<TrajectoryPoint>> out = new ArrayList<ArrayList<TrajectoryPoint>>();
			System.out.println(lefts); // Print everything to check format...
			out.add(lefts);
			out.add(rights);
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
