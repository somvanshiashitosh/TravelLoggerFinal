package com.diebold.travellogger.util;

public class dataFormat {

	public static String toUpperFirstInitial(String msg) {
		msg = msg.trim();
		// System.out.println(msg);
		if (msg.length() > 0) {
			String sequence[] = msg.split(" ");
			for (int i = 0; i < sequence.length; i++) {
				sequence[i] = sequence[i].trim();
				if (sequence[i].length() > 0) {
					sequence[i] = sequence[i].substring(0, 1).toUpperCase() + sequence[i].substring(1).toLowerCase();
				}
			}
			StringBuilder b = new StringBuilder();
			for (int i = 0; i < sequence.length; i++) {
				if (sequence[i].length() > 0) {
					b.append(" " + sequence[i]);
				}
			}
			String complete = b.toString().trim();
			// System.out.println(complete);
			return complete;
		} else {
			return null;
		}
	}

}
