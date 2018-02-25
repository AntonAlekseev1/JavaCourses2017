package com.ui.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public interface IConnection {

	public BufferedReader getReader() throws IOException;

	public BufferedWriter getWriter() throws IOException;

	public String getResponseFromServer(String value);

	public void close();

}
