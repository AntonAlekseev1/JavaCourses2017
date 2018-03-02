package com.hotel.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.hotel.Analyzer;
import com.hotel.been.Entity;

public class CsvWorker {
	public static class Writer {
		private String path;

		public Writer(String path) {
			this.path = path;
		}

		public void write(Entity entity) {
			String separator = Analyzer.getValueSeparator("Guest");
			try (FileWriter fileWriter = new FileWriter(path, true)) {
				fileWriter.write(entity.toString().replaceAll(" ", separator) + "\n");
				fileWriter.flush();
			} catch (IOException e) {

			}
		}

		public void comment(String comment) {
			try (FileWriter fileWriter = new FileWriter(path)) {
				fileWriter.write(comment + "\n");
				fileWriter.flush();
			} catch (IOException e) {

			}
		}
	}

	public static class Reader {
		String path;
		String separator = Analyzer.getValueSeparator("Guest");

		public Reader(String path) {
			this.path = path;
		}

		public List<String> read() {
			List<String> entity = new ArrayList<>();
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					entity.add(line.replaceAll(separator, " "));
				}
				entity.remove(0);
			} catch (IOException e) {

			}
			return entity;
		}
	}
}
