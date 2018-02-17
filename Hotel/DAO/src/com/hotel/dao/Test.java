package com.hotel.dao;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sql = "SELECT Rooms.id,number, copacity, stars, price,status, is_free FROM Rooms, History WHERE Rooms.id = History.id_room AND is_free = false  AND \r\n" + 
				"\"2007.07.22\"  NOT BETWEEN date_of_arival and evict_date\r\n" + 
				"        UNION SELECT rooms.id,number, copacity, stars, price,status, is_free FROM rooms WHERE is_free = true;";
		System.out.println(sql);
	}

}
