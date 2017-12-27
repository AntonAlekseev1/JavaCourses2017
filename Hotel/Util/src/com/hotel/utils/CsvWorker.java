package com.hotel.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.hotel.api.been.IEntity;

public class CsvWorker {
	public static class Writer {
		private String path;

		public Writer(String path) {
			this.path = path;
		}

		public void write(IEntity entity) {
			try (FileWriter fileWriter = new FileWriter(path, true)) {
				fileWriter.write(entity.toString() + "\n");
				fileWriter.flush();
			} catch (IOException e) {

			}
		}

		public void comment(String comment) {
			try (FileWriter fileWriter = new FileWriter(path)) {
				fileWriter.write(comment + "\n");
				fileWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static class Reader {
		String path;

		public Reader(String path) {
			this.path = path;
		}

		public List<String> read() {
			List<String> entity = new ArrayList<>();
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					entity.add(line);
				}
				entity.remove(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return entity;
		}
	}
}
